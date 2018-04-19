package com.example.stripe.converter;

import com.stripe.model.OrderItem;
import com.example.stripe.api.model.order.OrderItemModel;
import com.example.stripe.api.model.order.OrderItemType;
import com.example.stripe.impl.model.order.OrderItemModelImpl;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.order.OrderItemConverter;
import com.example.stripe.stripe_depended.converter.order.OrderItemFields;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class OrderItemConverterTest {

    private Converter<OrderItemModel, OrderItem> converter = new OrderItemConverter();

    @Test (expected = IllegalStateException.class)
    public void testConvertModelToParamsQuantityValidationWhenTypeIsNull() {
        OrderItemModel model = new OrderItemModelImpl();
        model.setQuantity(1);
        converter.convertModelToParams(model);
    }

    @Test (expected = IllegalStateException.class)
    public void testConvertModelToParamsQuantityValidationWhenTypeIsIncorrect() {
        OrderItemModel model = new OrderItemModelImpl();
        model.setQuantity(1);
        model.setType(OrderItemType.DISCOUNT);
        converter.convertModelToParams(model);
    }

    @Test
    public void testConvertEmptyModelToParams() {
        OrderItemModel model = new OrderItemModelImpl();
        Map<String, Object> params = converter.convertModelToParams(model);
        Assert.assertNotNull(params);
    }

    @Test
    public void testConvertModelToParams() {
        OrderItemModel model = new OrderItemModelImpl();
        model.setQuantity(1);
        model.setType(OrderItemType.SKU);

        model.setAmount(2);
        model.setCurrency("usd");
        model.setDescription("desc sample");
        model.setParentId("parent id sample");

        Map<String, Object> params = converter.convertModelToParams(model);

        Assert.assertEquals(model.getQuantity().toString(), params.get(OrderItemFields.QUANTITY));
        Assert.assertEquals(model.getType().name().toLowerCase(), params.get(OrderItemFields.TYPE));
        Assert.assertEquals(model.getAmount().toString(), params.get(OrderItemFields.AMOUNT).toString());
        Assert.assertEquals(model.getCurrency(), params.get(OrderItemFields.CURRENCY));
        Assert.assertEquals(model.getDescription(), params.get(OrderItemFields.DESCRIPTION));
        Assert.assertEquals(model.getParentId(), params.get(OrderItemFields.PARENT));
    }

    @Test
    public void testConvertParamsToModel() {
        Integer amount = 100;
        String currency = "usd";
        String description = "desc sample";
        String type = "sku";
        Integer quantity = 23;
        String parentId = "pid";

        Map<String, Object> params = new HashMap<>();
        params.put(OrderItemFields.AMOUNT, amount.toString());
        params.put(OrderItemFields.CURRENCY, currency);
        params.put(OrderItemFields.DESCRIPTION, description);
        params.put(OrderItemFields.TYPE, type);
        params.put(OrderItemFields.QUANTITY, quantity);
        params.put(OrderItemFields.PARENT, parentId);

        OrderItemModel model = converter.convertParamsToModel(params);

        Assert.assertEquals(amount, model.getAmount());
        Assert.assertEquals("usd", model.getCurrency());
        Assert.assertEquals(description, model.getDescription());
        Assert.assertEquals(OrderItemType.SKU, model.getType());
        Assert.assertEquals((Integer) 23, model.getQuantity());
        Assert.assertEquals(parentId, model.getParentId());
    }

    @Test
    public void convertEmptyParamsToModel() {
        Map<String, Object> params = new HashMap<>();
        OrderItemModel model = converter.convertParamsToModel(params);
        Assert.assertNotNull(model);
    }

    @Test
    public void convertEmptyEntityToModelTest() {
        OrderItem entity = new OrderItem();
        OrderItemModel model = converter.convertEntityToModel(entity);
        Assert.assertNotNull(model);
    }

    @Test
    public void convertEntityToModelTest() {
        String type = OrderItemType.SKU.name().toLowerCase();
        Integer quantity = 1;
        Integer amount = 2;
        String currency = "usd";
        String description = "desc sample";
        String parent = "pid";

        OrderItem entity = new OrderItem();
        entity.setType(type);
        entity.setQuantity(quantity);
        entity.setAmount(amount.longValue());
        entity.setCurrency(currency);
        entity.setDescription(description);
        entity.setParent(parent);

        OrderItemModel model = converter.convertEntityToModel(entity);

        Assert.assertEquals(OrderItemType.SKU, model.getType());
        Assert.assertEquals(quantity, model.getQuantity());
        Assert.assertEquals(amount, model.getAmount());
        Assert.assertEquals(currency, model.getCurrency());
        Assert.assertEquals(description, model.getDescription());
        Assert.assertEquals(parent, model.getParentId());
    }
}
