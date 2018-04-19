package com.example.stripe.service;

import com.example.stripe.api.model.product.ProductModel;
import com.example.stripe.api.model.product.ProductType;
import com.example.stripe.api.model.sku.SKUModel;
import com.example.stripe.api.service.ProductService;
import com.example.stripe.api.service.SKUService;
import com.example.stripe.impl.exception.FieldRequiredException;
import com.example.stripe.impl.model.StripeAuthProvider;
import com.example.stripe.impl.model.StripeAuthProviderImpl;
import com.example.stripe.impl.model.product.ProductModelImpl;
import com.example.stripe.impl.model.sku.SKUModelImpl;
import com.example.stripe.impl.model.sku.inventory.InventoryModel;
import com.example.stripe.impl.model.sku.inventory.InventoryType;
import com.example.stripe.impl.model.sku.inventory.InventoryValue;
import com.example.stripe.stripe_depended.service.ProductServiceImpl;
import com.example.stripe.stripe_depended.service.SKUServiceImpl;
import com.example.stripe.TestTokens;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SKUServiceTest {

    private static SKUService skuService;
    private static ProductService productService;
    private static StripeAuthProvider authProvider;

    @BeforeClass
    public static void init() {
        skuService = new SKUServiceImpl();
        productService = new ProductServiceImpl();
        authProvider = new StripeAuthProviderImpl(TestTokens.MAIN_ACC_TEST_API_SECRET_KEY);
    }

    @Test(expected = FieldRequiredException.class)
    public void testCurrencyRequiredValidation() throws Exception {
        SKUModel skuModel = instantiateAndFillModel(authProvider);
        skuModel.setCurrency(null);
        skuService.create(skuModel, authProvider);
    }

    @Test(expected = FieldRequiredException.class)
    public void testInventoryRequiredValidation() throws Exception {
        SKUModel skuModel = instantiateAndFillModel(authProvider);
        skuModel.setInventory(null);
        skuService.create(skuModel, authProvider);
    }

    @Test(expected = FieldRequiredException.class)
    public void testPriceRequiredValidation() throws Exception {
        SKUModel skuModel = instantiateAndFillModel(authProvider);
        skuModel.setPrice(null);
        skuService.create(skuModel, authProvider);
    }

    @Test(expected = FieldRequiredException.class)
    public void testProductRequiredValidation() throws Exception {
        SKUModel skuModel = instantiateAndFillModel(authProvider);
        skuModel.setProductId(null);
        skuService.create(skuModel, authProvider);
    }

    @Test
    public void testCreateSKU() throws Exception{
        testCreateSKU(authProvider);
    }

    @Test
    public void testCreateSKUUsingStripeAccountId() throws Exception{
        testCreateSKU(new StripeAuthProviderImpl(authProvider.getApiKey(), TestTokens.PROMOTER_ONE_ACC_CONNECTED_STRIPE_USER_ID));
    }

    private void testCreateSKU(StripeAuthProvider authProvider) throws Exception{
        SKUModel skuModel = instantiateAndFillModel(authProvider);
        SKUModel createdSkuModel = skuService.create(skuModel, authProvider);

        Assert.assertNotNull(createdSkuModel.getId());
        assertEqualsModels(skuModel, createdSkuModel);
    }

    @Test
    public void testRetrieveSKU() throws Exception {
        SKUModel skuModel = instantiateAndFillModel(authProvider);
        SKUModel createdSkuModel = skuService.create(skuModel, authProvider);

        String id = createdSkuModel.getId();
        Assert.assertNotNull(id);

        SKUModel retrievedModel = skuService.retrieve(id, authProvider);
        assertEqualsModels(skuModel, retrievedModel);
    }

    @Test
    public void testUpdateSKU() throws Exception {
        SKUModel skuModel = instantiateAndFillModel(authProvider);
        SKUModel createdSkuModel = skuService.create(skuModel, authProvider);
        assertEqualsModels(skuModel, createdSkuModel);
        createdSkuModel.setPrice(999);
        InventoryModel inventoryModel = createdSkuModel.getInventory();
        inventoryModel.setType(InventoryType.BUCKET);
        inventoryModel.setValue(InventoryValue.IN_STOCK);
        inventoryModel.setQuantity(null);
        Map<String, String> attributes = createdSkuModel.getAttributes();
        attributes.put("size", "XXL");
        attributes.put("color", "orange");
        SKUModel updatedModel = skuService.update(createdSkuModel, authProvider);

        Assert.assertEquals(Integer.valueOf(999), updatedModel.getPrice());
        Assert.assertEquals(InventoryType.BUCKET, updatedModel.getInventory().getType());
        Assert.assertEquals(InventoryValue.IN_STOCK, updatedModel.getInventory().getValue());
        Assert.assertEquals("XXL", updatedModel.getAttributes().get("size"));
        Assert.assertEquals("orange", updatedModel.getAttributes().get("color"));
    }

    public static void assertEqualsModels(SKUModel expected, SKUModel actual) {
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getProductId(), actual.getProductId());
        Assert.assertEquals(expected.getInventory().getType(), actual.getInventory().getType());
        Assert.assertEquals(expected.getInventory().getQuantity(), actual.getInventory().getQuantity());

        if (expected.getAttributes() != null) {
            Map<String, String> createdAttributesMap = actual.getAttributes();
            Assert.assertEquals(expected.getAttributes().get("size"), createdAttributesMap.get("size"));
            Assert.assertEquals(expected.getAttributes().get("color"), createdAttributesMap.get("color"));
        }
    }

    private SKUModel instantiateAndFillModel(StripeAuthProvider authProvider) throws Exception {
        //1. create product
        int i = 102;
        ProductModel productModel = new ProductModelImpl("name sample " + i);
        productModel.setType(ProductType.GOOD);
        productModel.setDescription("desc sample " + i);
        productModel.setAttributes(new ArrayList<>(Arrays.asList(new String[]{"size", "color"})));
        ProductModel createdProductModel = productService.create(productModel, authProvider);
        Assert.assertNotNull(createdProductModel.getId());

        //2. create SKU
        SKUModel skuModel = new SKUModelImpl();
        skuModel.setProductId(createdProductModel.getId());
        skuModel.setCurrency("usd");
        InventoryModel inventory = new InventoryModel();
        inventory.setType(InventoryType.FINITE);
        inventory.setQuantity(3);
        skuModel.setInventory(inventory);
        skuModel.setPrice(1001);
        Map<String, String> attributesMap = new HashMap<>();
        attributesMap.put("size", "big");
        attributesMap.put("color", "white");
        skuModel.setAttributes(attributesMap);
        return skuModel;
    }
}
