package com.example.stripe.service;

import com.example.stripe.api.model.order.OrderItemModel;
import com.example.stripe.api.model.order.OrderItemType;
import com.example.stripe.api.model.order.OrderModel;
import com.example.stripe.api.model.order.OrderStatus;
import com.example.stripe.api.model.product.ProductModel;
import com.example.stripe.api.model.product.ProductType;
import com.example.stripe.api.model.shipping.AddressModel;
import com.example.stripe.api.model.shipping.PackageDimensionsModel;
import com.example.stripe.api.model.shipping.ShippingDetailsModel;
import com.example.stripe.api.model.shipping.ShippingMethodModel;
import com.example.stripe.api.model.sku.SKUModel;
import com.example.stripe.api.service.OrderService;
import com.example.stripe.api.service.ProductService;
import com.example.stripe.api.service.SKUService;
import com.example.stripe.impl.exception.FieldRequiredException;
import com.example.stripe.impl.exception.UnsupportedParamException;
import com.example.stripe.impl.model.StripeAuthProvider;
import com.example.stripe.impl.model.StripeAuthProviderImpl;
import com.example.stripe.impl.model.order.OrderItemModelImpl;
import com.example.stripe.impl.model.order.OrderModelImpl;
import com.example.stripe.impl.model.product.ProductModelImpl;
import com.example.stripe.impl.model.shipping.AddressModelImpl;
import com.example.stripe.impl.model.shipping.PackageDimensionsModelImpl;
import com.example.stripe.impl.model.shipping.ShippingDetailsModelImpl;
import com.example.stripe.impl.model.sku.SKUModelImpl;
import com.example.stripe.impl.model.sku.inventory.InventoryModel;
import com.example.stripe.impl.model.sku.inventory.InventoryType;
import com.example.stripe.impl.model.sku.inventory.InventoryValue;
import com.example.stripe.stripe_depended.service.OrderServiceImpl;
import com.example.stripe.stripe_depended.service.ProductServiceImpl;
import com.example.stripe.stripe_depended.service.SKUServiceImpl;
import com.example.stripe.TestTokens;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class OrderServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);

    private static ProductService productService;
    private static SKUService skuService;
    private static OrderService orderService;

    private static StripeAuthProvider authProvider;

    @BeforeClass
    public static void init() {
        productService = new ProductServiceImpl();
        skuService = new SKUServiceImpl();
        orderService = new OrderServiceImpl();

        authProvider = new StripeAuthProviderImpl(TestTokens.MAIN_ACC_TEST_API_SECRET_KEY);
    }

    @Test(expected = FieldRequiredException.class)
    public void testCreateOrderItemsRequiredValidation() throws Exception {
        OrderModel model = new OrderModelImpl();
        model.setCurrency("usd");
        orderService.createOrder(model, authProvider);
    }

    @Test(expected = UnsupportedParamException.class)
    public void testAllowedParamValidation() throws Exception {
        OrderModel model = new OrderModelImpl();
        model.setCurrency("usd");
        List<OrderItemModel> items = new LinkedList<>();
        model.setItems(items);
        OrderItemModel item = new OrderItemModelImpl();
        items.add(item);
        model.setApplicationFee(6);
        orderService.createOrder(model, authProvider);
    }

    @Test
    public void testCreateOrderThenChangeShippingMethodThenPay_MainAcc() throws Exception {
        testCreateOrderThenChangeShippingMethodThenPay(authProvider);
    }

    @Test
    public void testCreateOrderThenChangeShippingMethodThenPay_ConnectedStripeAcc() throws Exception {
        testCreateOrderThenChangeShippingMethodThenPay(new StripeAuthProviderImpl(authProvider.getApiKey(), TestTokens.PROMOTER_ONE_ACC_CONNECTED_STRIPE_USER_ID));
    }

    public void testCreateOrderThenChangeShippingMethodThenPay(StripeAuthProvider authProvider) throws Exception {
        //create order model
        OrderModel model = new OrderModelImpl();
        //currency
        model.setCurrency("usd");
        //shipping details
        model.setShipping(instantiateAndFillCustomerShippingAddress());

        //order items

        Integer item_1_price = 104;
        Integer item_1_quantity = 3;
        addItem(model, item_1_price, item_1_quantity, true, authProvider);

        Integer item_2_price = 206;
        Integer item_2_quantity = 2;
        addItem(model, item_2_price, item_2_quantity, true, authProvider);

        Integer item_3_price = 307;
        Integer item_3_quantity = 2;
        addItem(model, item_3_price, item_3_quantity, false, authProvider);

        Integer itemsCost = (item_1_price * item_1_quantity) + (item_2_price * item_2_quantity) + (item_3_price * item_3_quantity);

        Integer discount = -300;
        OrderItemModel discountItem = createOrderItemOfTypeDiscount(discount);
        model.getItems().add(discountItem);

        itemsCost += discount;

        //4. create order in stripe
        model = orderService.createOrder(model, authProvider);
        Assert.assertNotNull(model);

        //find selected shipping method by id
        ShippingMethodModel selectedShippingMethodModel = findShippingMethodModelById(model.getSelectedShippingMethodId(), model.getShippingMethods());
        Assert.assertNotNull(selectedShippingMethodModel);
        logger.info("selectedShippingMethodModel=" + selectedShippingMethodModel);

        Integer oldShippingAmount = selectedShippingMethodModel.getAmount();
        //calculate expected order amount with shipping cost
        Integer expectedOrderAmountWithShippingCost = itemsCost + selectedShippingMethodModel.getAmount();

        //check if order amount equals to expected value
        Assert.assertEquals(expectedOrderAmountWithShippingCost, model.getAmount());

        logger.info("createdOrderModel.getShippingMethods().size()=" + model.getShippingMethods().size());
        //change shipping method makes sense only if there are more methods than one
        if (model.getShippingMethods().size() > 1) {
            //change shipping method
            ShippingMethodModel newShippingMethodModel = null;
            for (ShippingMethodModel shippingMethodModel : model.getShippingMethods()) {
                if (!shippingMethodModel.getId().equals(selectedShippingMethodModel.getId())) {
                    newShippingMethodModel = shippingMethodModel;
                    break;
                }
            }
            Assert.assertNotNull(newShippingMethodModel);
            Assert.assertNotEquals(oldShippingAmount, newShippingMethodModel.getAmount());

            //update order with other selected shipping method
            OrderModel modelForUpdate = new OrderModelImpl();
            modelForUpdate.setId(model.getId());
            modelForUpdate.setSelectedShippingMethodId(newShippingMethodModel.getId());
            model = orderService.updateOrder(modelForUpdate, authProvider);

            //check if order amount changed according to new shipping cost
            expectedOrderAmountWithShippingCost = itemsCost + newShippingMethodModel.getAmount();
            Assert.assertEquals(expectedOrderAmountWithShippingCost, model.getAmount());
            model = orderService.retrieve(model.getId(), authProvider);
            Assert.assertEquals(expectedOrderAmountWithShippingCost, model.getAmount());
        }
        //check if status is "created", before pay
        Assert.assertEquals(OrderStatus.CREATED, model.getStatus());

        //pay order
        model = orderService.pay(model.getId(), "tok_visa", "atrotsenko@uventex.com", authProvider, null);
        //check if status is "paid", after pay
        Assert.assertEquals(OrderStatus.PAID, model.getStatus());
    }

    @Test(expected = IllegalStateException.class)
    public void testCreateOrderEmptyItemsValidation() throws Exception {
        OrderModel model = new OrderModelImpl();
        model.setCurrency("usd");
        model.setItems(new LinkedList<>());
        orderService.createOrder(model, authProvider);
    }

    private SKUModel createSkuAndProduct(ProductType productType, boolean shippable, Integer price, PackageDimensionsModel packageDimensionsModel, StripeAuthProvider authProvider) throws Exception{
        //1. create product
        ProductModel productModel = new ProductModelImpl("Product for order test ");
        productModel.setType(productType);
        if (productType.equals(ProductType.GOOD)) {
            productModel.setDescription("Product desc for order test ");
        }
        productModel.setShippable(shippable);

        ProductModel createdProductModel = productService.create(productModel, authProvider);

        //1.1. check product created
        Assert.assertNotNull(createdProductModel.getId());
        ProductServiceTest.assertEqualsModels(productModel, createdProductModel);

        //2. create skus
        SKUModel skuModel = new SKUModelImpl();
        skuModel.setProductId(createdProductModel.getId());
        skuModel.setCurrency("usd");
        InventoryModel inventory = new InventoryModel();
        inventory.setType(InventoryType.BUCKET);
        inventory.setValue(InventoryValue.IN_STOCK);
        skuModel.setInventory(inventory);
        skuModel.setPrice(price);

        if (packageDimensionsModel != null) {
            skuModel.setPackageDimensions(packageDimensionsModel);
        }

        SKUModel createdSkuModel = skuService.create(skuModel, authProvider);

        //2.1 check sku created
        Assert.assertNotNull(createdSkuModel.getId());
        SKUServiceTest.assertEqualsModels(skuModel, createdSkuModel);
        return createdSkuModel;
    }

    private OrderItemModel createOrderItemOfTypeSku(String skuId, Integer quantity) {
        //3. create order item, based on sku
        OrderItemModel item = new OrderItemModelImpl();
        item.setType(OrderItemType.SKU);
        item.setQuantity(quantity);
        item.setDescription("Order item for order test ");
        item.setParentId(skuId);
        return item;
    }

    private OrderItemModel createOrderItemOfTypeDiscount(Integer amount) {
        OrderItemModel item = new OrderItemModelImpl();
        item.setType(OrderItemType.DISCOUNT);
        item.setAmount(amount);
        item.setCurrency("usd");
        item.setDescription("Discount for " + amount/100 + "$");
        return item;
    }

    private ShippingDetailsModel instantiateAndFillCustomerShippingAddress() {
        ShippingDetailsModel shippingDetailsModel = new ShippingDetailsModelImpl();
        shippingDetailsModel.setName("Test client shipping details");
        AddressModel addressModel = new AddressModelImpl();
        addressModel.setLine1("1234 Main Street");
        addressModel.setCity("San Francisco");
        addressModel.setState("CA");
        addressModel.setPostalCode("94111");
        addressModel.setCountry("US");
        shippingDetailsModel.setAddress(addressModel);
        return shippingDetailsModel;
    }

    private void addItem(OrderModel model, Integer itemPrice, Integer itemQuantity, boolean shippable, StripeAuthProvider authProvider) throws Exception {
        List<OrderItemModel> items = model.getItems();
        if (items == null) {
            items = new LinkedList<>();
            model.setItems(items);
        }
        PackageDimensionsModel packageDimensions = null;
        if (shippable) {
            packageDimensions = new PackageDimensionsModelImpl(1d, 2d, 3d, 4d);
        }
        SKUModel skuModel = createSkuAndProduct(ProductType.GOOD, shippable, itemPrice, packageDimensions, authProvider);
        OrderItemModel item = createOrderItemOfTypeSku(skuModel.getId(), itemQuantity);
        items.add(item);
    }

    private ShippingMethodModel findShippingMethodModelById(String id, List<ShippingMethodModel> list) {
        for (ShippingMethodModel shippingMethodModel : list) {
            if (shippingMethodModel.getId().equalsIgnoreCase(id)) {
                return shippingMethodModel;
            }
        }
        throw new IllegalArgumentException("There is no shipping method with such id in the list");
    }
}
