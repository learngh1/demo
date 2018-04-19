package com.example.stripe.converter;

import com.stripe.model.Address;
import com.stripe.model.Order;
import com.stripe.model.OrderItem;
import com.stripe.model.ShippingDetails;
import com.example.stripe.api.model.order.OrderItemModel;
import com.example.stripe.api.model.order.OrderItemType;
import com.example.stripe.api.model.order.OrderModel;
import com.example.stripe.api.model.order.OrderStatus;
import com.example.stripe.api.model.shipping.ShippingDetailsModel;
import com.example.stripe.impl.model.order.OrderItemModelImpl;
import com.example.stripe.impl.model.order.OrderModelImpl;
import com.example.stripe.impl.model.shipping.ShippingDetailsFields;
import com.example.stripe.impl.model.sku.SKUFields;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.order.OrderConverter;
import com.example.stripe.stripe_depended.converter.order.OrderFields;
import com.example.stripe.stripe_depended.converter.shipping.ShippingDetailsConverter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderConverterTest {
    private Converter<OrderModel, Order> converter = new OrderConverter();
    private Converter<ShippingDetailsModel, ShippingDetails> shippingDetailsConverter = new ShippingDetailsConverter();

    public static OrderItemModel generateOrderItemModel(int i) {
        OrderItemModelImpl item = new OrderItemModelImpl();
        item.setType(OrderItemType.SKU);
        item.setQuantity(i*100);
        item.setAmount(i*10);
        item.setDescription("item desc " + i);
        item.setParentId("pid " + i);
        return item;
    }

    @Test
    public void testConvertModelToParamsWithOnlyCurrencyAndItems() {
        OrderModel model = new OrderModelImpl();
        model.setCurrency("usd");
        List<OrderItemModel> items = new LinkedList<>();
        items.add(new OrderItemModelImpl());
        model.setItems(items);
        Map<String, Object> params = converter.convertModelToParams(model);
        Assert.assertEquals("usd", params.get(OrderFields.CURRENCY));
    }

    @Test
    public void testConvertModelToParams() {
        String email = "q@q.q";
        String currency = "usd";
        Integer amount = 30;
        Integer applicationFee = 40;
        String chargeId = "chargeId1";
        Long created = System.currentTimeMillis();
        String selectedShippingMethodId = "ssmi1";
        OrderStatus status = OrderStatus.CREATED;

        Map<String, Object> addressParams = new HashMap<>();
        addressParams.put("city", "San Fancisco");
        addressParams.put("state", "CA");
        addressParams.put("country", "US");

        Map<String, Object> shippingParams = new HashMap<>();
        shippingParams.put(ShippingDetailsFields.ADDRESS, addressParams);

        Map<String, String> metadata = new HashMap<>();
        metadata.put("metadata_key", "metadata_value");

        OrderModel model = new OrderModelImpl();
        model.setCurrency(currency);
        model.setEmail(email);
        model.setAmount(amount);
        model.setApplicationFee(applicationFee);
        model.setChargeId(chargeId);
        model.setCreated(created);
        model.setSelectedShippingMethodId(selectedShippingMethodId);
        model.setStatus(status);
        model.setShipping(shippingDetailsConverter.convertParamsToModel(shippingParams));
        model.setMetadata(metadata);
        List<OrderItemModel> items = new LinkedList<>();

        items.add(generateOrderItemModel(1));
        items.add(generateOrderItemModel(2));

        model.setItems(items);

        Map<String, Object> params = converter.convertModelToParams(model);

        Assert.assertEquals(email, params.get(OrderFields.EMAIL));
        Assert.assertEquals(currency.toLowerCase(), params.get(OrderFields.CURRENCY));
        List<Object> itemsFromParams = (List<Object>) params.get(OrderFields.ITEMS);
        Assert.assertEquals(2, itemsFromParams.size());
        Map<String, Object> shippingParams2 = (Map<String, Object>) params.get(OrderFields.SHIPPING);
        Map<String, Object> addressParams2 = (Map<String, Object>) shippingParams2.get(ShippingDetailsFields.ADDRESS);
        Assert.assertEquals(3, addressParams2.size());

        Assert.assertEquals(amount, params.get(OrderFields.AMOUNT));
        Assert.assertEquals(applicationFee, params.get(OrderFields.APPLICATION_FEE));
        Assert.assertEquals(chargeId, params.get(OrderFields.CHARGE));
        Assert.assertEquals(created, params.get(OrderFields.CREATED));
        Assert.assertEquals(selectedShippingMethodId, params.get(OrderFields.SELECTED_SHIPPING_METHOD));
        Assert.assertEquals(status.name().toLowerCase(), params.get(OrderFields.STATUS));

        Assert.assertEquals(model.getMetadata().size(), ((Map<String, String>) params.get(SKUFields.METADATA)).size());
        Assert.assertEquals(model.getMetadata().get("metadata_key"), ((Map<String, String>) params.get(SKUFields.METADATA)).get("metadata_key"));
    }

    @Test
    public void testConvertEmptyParamsToModel() {
        Map<String, Object> params = new HashMap<>();
        OrderModel model = converter.convertParamsToModel(params);
        Assert.assertNotNull(model);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testConvertParamsToModelUnsupportedShippingMethodException() {
        Map<String, Object> params = new HashMap<>();
        params.put(OrderFields.SHIPPING_METHODS, new HashMap<>());
        converter.convertParamsToModel(params);
    }

    @Test
    public void testConvertParamsToModel() {
        Map<String, Object> params = new HashMap<>();

        String id_sample = "id sample";
        String email = "email";
        String usd = "usd";
        Integer amount = 21;
        Integer applicationFee = 31;
        String chargeId = "chargeId";
        Long created = System.currentTimeMillis();
        String selectedShippingMethodId = "ssmi2";
        OrderStatus status = OrderStatus.FULFILLED;

        String city = "LA";
        String country = "US";
        HashMap<String, Object> addressParams = new HashMap<>();
        addressParams.put("city", city);
        addressParams.put("country", country);
        Map<String, Object> shippingParams = new HashMap<>();
        shippingParams.put(ShippingDetailsFields.ADDRESS, addressParams);

        LinkedList<Object> items = new LinkedList<>();
        Map<String, Object> itemParams = new HashMap<>();
        itemParams.put("type", "sku");
        items.add(itemParams);

        Map<String, String> metadata = new HashMap<>();
        metadata.put("metadata_key", "metadata_val");

        params.put(OrderFields.ID, id_sample);
        params.put(OrderFields.EMAIL, email);
        params.put(OrderFields.CURRENCY, usd);
        params.put(OrderFields.SHIPPING, shippingParams);
        params.put(OrderFields.ITEMS, items);
        params.put(OrderFields.AMOUNT, amount);
        params.put(OrderFields.APPLICATION_FEE, applicationFee);
        params.put(OrderFields.CHARGE, chargeId);
        params.put(OrderFields.CREATED, created);
        params.put(OrderFields.SELECTED_SHIPPING_METHOD, selectedShippingMethodId);
        params.put(OrderFields.STATUS, status.name().toLowerCase());
        params.put(OrderFields.METADATA, metadata);

        OrderModel model = converter.convertParamsToModel(params);

        Assert.assertEquals(id_sample, model.getId());
        Assert.assertEquals(email, model.getEmail());
        Assert.assertEquals(usd, model.getCurrency());
        Assert.assertEquals(city, model.getShipping().getAddress().getCity());
        Assert.assertEquals(country, model.getShipping().getAddress().getCountry());
        Assert.assertEquals("sku", model.getItems().get(0).getType().name().toLowerCase());

        Assert.assertEquals(amount, model.getAmount());
        Assert.assertEquals(applicationFee, model.getApplicationFee());
        Assert.assertEquals(chargeId, model.getChargeId());
        Assert.assertEquals(created, model.getCreated());
        Assert.assertEquals(selectedShippingMethodId, model.getSelectedShippingMethodId());
        Assert.assertEquals(status, model.getStatus());

        Assert.assertEquals(metadata.size(), model.getMetadata().size());
        Assert.assertEquals(metadata.get("metadata_key"), model.getMetadata().get("metadata_key"));
    }

    @Test
    public void testConvertEmptyEntityToModel() {
        Order entity = new Order();
        OrderModel model = converter.convertEntityToModel(entity);
        Assert.assertNotNull(model);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testConvertModelToParamsUnsupportedShippingMethodException() {
        OrderModel model = new OrderModelImpl();
        model.setCurrency("usd");
        List<OrderItemModel> items = new LinkedList<>();
        items.add(new OrderItemModelImpl());
        model.setItems(items);
        model.setShippingMethods(new ArrayList<>());
        converter.convertModelToParams(model);
    }

    @Test
    public void testConvertEntityToModel() {
        String id_sample = "id sample";
        String email = "email";
        String usd = "usd";
        Integer amount = 21;
        Integer applicationFee = 31;
        String chargeId = "chargeId";
        Long created = System.currentTimeMillis();
        String selectedShippingMethodId = "ssmi2";
        OrderStatus status = OrderStatus.FULFILLED;

        Map<String, String> metadata = new HashMap<>();
        metadata.put("metadata_key", "metadata_value");

        String city = "LA";
        String country = "US";

        Order entity = new Order();
        entity.setId(id_sample);
        entity.setCurrency(usd);
        entity.setEmail(email);
        entity.setAmount(amount.longValue());
        entity.setApplicationFee(Long.valueOf(applicationFee));
        entity.setCharge(chargeId);
        entity.setCreated(created);
        entity.setMetadata(metadata);
        entity.setStatus(status.name().toLowerCase());
        entity.setSelectedShippingMethod(selectedShippingMethodId);

        Address address = new Address();
        address.setCity(city);
        address.setCountry(country);

        ShippingDetails sd = new ShippingDetails();
        sd.setAddress(address);
        entity.setShipping(sd);

        List<OrderItem> items = new LinkedList<>();
        OrderItem item = new OrderItem();

        item.setType("sku");
        item.setQuantity(10);
        items.add(item);

        item = new OrderItem();
        item.setType("tax");
        item.setQuantity(20);
        items.add(item);

        entity.setItems(items);

        OrderModel model = converter.convertEntityToModel(entity);

        Assert.assertEquals(id_sample, model.getId());
        Assert.assertEquals(usd, model.getCurrency());
        Assert.assertEquals(email, model.getEmail());
        Assert.assertEquals(city, model.getShipping().getAddress().getCity());
        Assert.assertEquals(country, model.getShipping().getAddress().getCountry());
        Assert.assertEquals(2, model.getItems().size());
        Assert.assertEquals(amount, model.getAmount());
        Assert.assertEquals(applicationFee, model.getApplicationFee());
        Assert.assertEquals(chargeId, model.getChargeId());
        Assert.assertEquals(created, model.getCreated());
        Assert.assertEquals(selectedShippingMethodId, model.getSelectedShippingMethodId());
        Assert.assertEquals(status, model.getStatus());
        Assert.assertEquals(metadata.size(), model.getMetadata().size());
        Assert.assertEquals(metadata.get("metadata_key"), model.getMetadata().get("metadata_key"));
    }
}
