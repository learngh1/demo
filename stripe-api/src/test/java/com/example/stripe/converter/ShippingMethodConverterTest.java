package com.example.stripe.converter;

import com.stripe.model.ShippingMethod;
import com.example.stripe.api.model.shipping.ShippingMethodModel;
import com.example.stripe.impl.model.shipping.ShippingMethodFields;
import com.example.stripe.impl.model.shipping.ShippingMethodModelImpl;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.shipping.ShippingMethodConverter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ShippingMethodConverterTest {
    private Converter<ShippingMethodModel, ShippingMethod> converter = new ShippingMethodConverter();

    @Test
    public void testConvertModelToParams() {
        Integer amount = 1;
        String description = "desc";
        String currency = "usd";
        String id = "id1";

        ShippingMethodModel model = new ShippingMethodModelImpl();
        model.setAmount(amount);
        model.setDescription(description);
        model.setCurrency(currency);
        model.setId(id);

        Map<String, Object> params = converter.convertModelToParams(model);

        Assert.assertEquals(amount, params.get(ShippingMethodFields.AMOUNT));
        Assert.assertEquals(description, params.get(ShippingMethodFields.DESCRIPTION));
        Assert.assertEquals(currency, params.get(ShippingMethodFields.CURRENCY));
        Assert.assertEquals(id, params.get(ShippingMethodFields.ID));
    }

    @Test
    public void testConvertEmptyModelToParams() {
        ShippingMethodModel model = new ShippingMethodModelImpl();
        Map<String, Object> params = converter.convertModelToParams(model);
        Assert.assertNotNull(params);
    }

    @Test
    public void testConvertParamsToModel() {
        Integer amount = 1;
        String description = "desc";
        String currency = "usd";
        String id = "id1";

        Map<String, Object> params = new HashMap<>();
        params.put(ShippingMethodFields.AMOUNT, amount);
        params.put(ShippingMethodFields.DESCRIPTION, description);
        params.put(ShippingMethodFields.CURRENCY, currency);
        params.put(ShippingMethodFields.ID, id);


        ShippingMethodModel model = converter.convertParamsToModel(params);
        Assert.assertEquals(amount, model.getAmount());
        Assert.assertEquals(description, model.getDescription());
        Assert.assertEquals(currency, model.getCurrency());
        Assert.assertEquals(id, model.getId());
    }

    @Test
    public void testConvertEmptyParamsToModel() {
        Map<String, Object> params = new HashMap<>();
        ShippingMethodModel model = converter.convertParamsToModel(params);
        Assert.assertNotNull(model);
    }

    @Test
    public void testConvertEntityToModel() {
        Integer amount = 1;
        String description = "desc";
        String currency = "usd";
        String id = "id1";

        ShippingMethod entity = new ShippingMethod();
        entity.setAmount(amount.longValue());
        entity.setDescription(description);
        entity.setCurrency(currency);
        entity.setId(id);

        ShippingMethodModel model = converter.convertEntityToModel(entity);
        Assert.assertEquals(amount, model.getAmount());
        Assert.assertEquals(description, model.getDescription());
        Assert.assertEquals(currency, model.getCurrency());
        Assert.assertEquals(id, model.getId());
    }

    @Test
    public void testConvertEmptyEntityToModel() {
        ShippingMethod entity = new ShippingMethod();
        ShippingMethodModel model = converter.convertEntityToModel(entity);
        Assert.assertNotNull(model);
    }
}
