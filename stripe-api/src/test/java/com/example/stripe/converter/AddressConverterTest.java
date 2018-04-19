package com.example.stripe.converter;

import com.stripe.model.Address;
import com.example.stripe.api.model.shipping.AddressModel;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.shipping.AddressConverter;
import com.example.stripe.impl.model.shipping.AddressFields;
import com.example.stripe.impl.model.shipping.AddressModelImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AddressConverterTest {

    private Converter<AddressModel, Address> converter = new AddressConverter();

    @Test
    public void testConvertEmptyModelToParams() {
        AddressModel model = new AddressModelImpl();
        Map<String, Object> params = converter.convertModelToParams(model);
        Assert.assertNotNull(params);
    }

    @Test
    public void testConvertModelToParams() {
        String country = "US";
        String state = "ST";
        String city = "LA";
        String line1 = "line1";
        String line2 = "line2";
        String postalCode = "49857";

        AddressModel model = new AddressModelImpl();

        model.setCountry(country);
        model.setState(state);
        model.setCity(city);
        model.setLine1(line1);
        model.setLine2(line2);
        model.setPostalCode(postalCode);

        Map<String, Object> params = converter.convertModelToParams(model);

        Assert.assertEquals(country, params.get(AddressFields.COUNTRY));
        Assert.assertEquals(state, params.get(AddressFields.STATE));
        Assert.assertEquals(city, params.get(AddressFields.CITY));
        Assert.assertEquals(line1, params.get(AddressFields.LINE1));
        Assert.assertEquals(line2, params.get(AddressFields.LINE2));
        Assert.assertEquals(postalCode, params.get(AddressFields.POSTAL_CODE));
    }

    public static void assertEqualsModels(AddressModel expected, AddressModel actual) {
        Assert.assertEquals(expected.getCountry(), actual.getCountry());
        Assert.assertEquals(expected.getState(), actual.getState());
        Assert.assertEquals(expected.getCity(), actual.getCity());
        Assert.assertEquals(expected.getLine1(), actual.getLine1());
        Assert.assertEquals(expected.getLine2(), actual.getLine2());
        Assert.assertEquals(expected.getPostalCode(), actual.getPostalCode());
    }

    @Test
    public void testConvertParamsToModel() {
        String country = "US";
        String state = "ST";
        String city = "LA";
        String line1 = "line1";
        String line2 = "line2";
        String postalCode = "49857";

        Map<String, Object> params = new HashMap<>();
        params.put(AddressFields.COUNTRY, country);
        params.put(AddressFields.STATE, state);
        params.put(AddressFields.CITY, city);
        params.put(AddressFields.LINE1, line1);
        params.put(AddressFields.LINE2, line2);
        params.put(AddressFields.POSTAL_CODE, postalCode);

        AddressModel model = converter.convertParamsToModel(params);

        Assert.assertEquals(country, model.getCountry());
        Assert.assertEquals(state, model.getState());
        Assert.assertEquals(city, model.getCity());
        Assert.assertEquals(line1, model.getLine1());
        Assert.assertEquals(line2, model.getLine2());
        Assert.assertEquals(postalCode, model.getPostalCode());
    }

    @Test
    public void testConvertEmptyParamsToModel() {
        Map<String, Object> params = new HashMap<>();
        AddressModel model = converter.convertParamsToModel(params);
        Assert.assertNotNull(model);
    }

    @Test
    public void testConvertEntityToModel() {
        String country = "US";
        String state = "ST";
        String city = "LA";
        String line1 = "line1";
        String line2 = "line2";
        String postalCode = "49857";

        Address entity = new Address();
        entity.setCountry(country);
        entity.setState(state);
        entity.setCity(city);
        entity.setLine1(line1);
        entity.setLine2(line2);
        entity.setPostalCode(postalCode);

        AddressModel model = converter.convertEntityToModel(entity);

        Assert.assertEquals(country, model.getCountry());
        Assert.assertEquals(state, model.getState());
        Assert.assertEquals(city, model.getCity());
        Assert.assertEquals(line1, model.getLine1());
        Assert.assertEquals(line2, model.getLine2());
        Assert.assertEquals(postalCode, model.getPostalCode());
    }

    @Test
    public void testConvertEmptyEntityToModel() {
        Address entity = new Address();
        AddressModel model = converter.convertEntityToModel(entity);
        Assert.assertNotNull(model);
    }
}
