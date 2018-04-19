package com.example.stripe.converter;

import com.stripe.model.Address;
import com.stripe.model.ShippingDetails;
import com.example.stripe.api.model.shipping.AddressModel;
import com.example.stripe.api.model.shipping.ShippingDetailsModel;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.shipping.AddressConverter;
import com.example.stripe.stripe_depended.converter.shipping.ShippingDetailsConverter;
import com.example.stripe.impl.exception.FieldRequiredException;
import com.example.stripe.impl.model.shipping.AddressModelImpl;
import com.example.stripe.impl.model.shipping.ShippingDetailsFields;
import com.example.stripe.impl.model.shipping.ShippingDetailsModelImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ShippingDetailsConverterTest {
    private Converter<ShippingDetailsModel, ShippingDetails> converter = new ShippingDetailsConverter();
    private Converter<AddressModel, Address> addressConverter = new AddressConverter();

    @Test (expected = FieldRequiredException.class)
    public void testConvertModelToParamsAddressRequiredValidation() {
        ShippingDetailsModel model = new ShippingDetailsModelImpl();
        model.setName("name");
        model.setPhone("phone");
        converter.convertModelToParams(model);
    }

    @Test
    public void testConvertModelToParams() {
        ShippingDetailsModel model = new ShippingDetailsModelImpl();
        model.setName("name1");
        model.setPhone("phone1");
        model.setAddress(instantiateAndFillAddressModel());
        Map<String, Object> params = converter.convertModelToParams(model);
        Assert.assertEquals(params.get(ShippingDetailsFields.NAME), "name1");
        Assert.assertEquals(params.get(ShippingDetailsFields.PHONE), "phone1");
        Assert.assertEquals(6, ((Map<String, Object>) params.get(ShippingDetailsFields.ADDRESS)).size());
    }

    @Test
    public void testConvertModelToParamsWithOnlyAddress() {
        ShippingDetailsModel model = new ShippingDetailsModelImpl();
        model.setAddress(instantiateAndFillAddressModel());
        Map<String, Object> params = converter.convertModelToParams(model);
        Assert.assertEquals(6, ((Map<String, Object>) params.get(ShippingDetailsFields.ADDRESS)).size());
    }

    @Test
    public void testConvertParamsToModel() {
        AddressModel addressModel = instantiateAndFillAddressModel();
        Map<String, Object> addressParams = addressConverter.convertModelToParams(addressModel);

        Map<String, Object> params = new HashMap<>();
        params.put(ShippingDetailsFields.NAME, "name2");
        params.put(ShippingDetailsFields.PHONE, "phone2");
        params.put(ShippingDetailsFields.ADDRESS, addressParams);

        ShippingDetailsModel model = converter.convertParamsToModel(params);

        Assert.assertEquals("name2", model.getName());
        Assert.assertEquals("phone2", model.getPhone());
        AddressConverterTest.assertEqualsModels(addressModel, model.getAddress());
    }

    @Test
    public void testConvertEmptyParamsToModel() {
        Map<String, Object> params = new HashMap<>();
        ShippingDetailsModel model = converter.convertParamsToModel(params);
        Assert.assertNotNull(model);
    }

    @Test
    public void testConvertEntityToModel() {
        ShippingDetails entity = new ShippingDetails();
        entity.setName("name3");
        entity.setPhone("phone3");
        Address addressEntity = instantiateAndFillAddressEntity();
        entity.setAddress(addressEntity);

        ShippingDetailsModel model = converter.convertEntityToModel(entity);
        Assert.assertEquals("name3", model.getName());
        Assert.assertEquals("phone3", model.getPhone());

        AddressModel addressModel = addressConverter.convertEntityToModel(addressEntity);
        AddressConverterTest.assertEqualsModels(addressModel, model.getAddress());
    }

    @Test
    public void testConvertEntityToModelWithOnlyAddress() {
        ShippingDetails entity = new ShippingDetails();
        Address addressEntity = instantiateAndFillAddressEntity();
        entity.setAddress(addressEntity);

        ShippingDetailsModel model = converter.convertEntityToModel(entity);

        AddressModel addressModel = addressConverter.convertEntityToModel(addressEntity);
        AddressConverterTest.assertEqualsModels(addressModel, model.getAddress());
    }

    private Address instantiateAndFillAddressEntity() {
        String country = "US";
        String state = "ST";
        String city = "LA";
        String line1 = "line1";
        String line2 = "line2";
        String postalCode = "49857";

        Address addressEntity = new Address();
        addressEntity.setCountry(country);
        addressEntity.setState(state);
        addressEntity.setCity(city);
        addressEntity.setLine1(line1);
        addressEntity.setLine2(line2);
        addressEntity.setPostalCode(postalCode);
        return addressEntity;
    }

    private AddressModel instantiateAndFillAddressModel() {
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
        return model;
    }
}
