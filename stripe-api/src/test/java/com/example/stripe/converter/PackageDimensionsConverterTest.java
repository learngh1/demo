package com.example.stripe.converter;

import com.stripe.model.PackageDimensions;
import com.example.stripe.api.model.shipping.PackageDimensionsModel;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.shipping.PackageDimensionsConverter;
import com.example.stripe.impl.model.shipping.PackageDimensionsFields;
import com.example.stripe.impl.model.shipping.PackageDimensionsModelImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PackageDimensionsConverterTest {

    private Converter<PackageDimensionsModel, PackageDimensions> converter = new PackageDimensionsConverter();

    @Test
    public void testConvertModelToParams() {
        PackageDimensionsModel model = new PackageDimensionsModelImpl();
        model.setHeight(1d);
        model.setLength(2d);
        model.setWidth(3d);
        model.setWeight(4d);

        Map<String, Object> params = converter.convertModelToParams(model);
        Assert.assertEquals(1d, params.get(PackageDimensionsFields.HEIGHT));
        Assert.assertEquals(2d, params.get(PackageDimensionsFields.LENGTH));
        Assert.assertEquals(3d, params.get(PackageDimensionsFields.WIDTH));
        Assert.assertEquals(4d, params.get(PackageDimensionsFields.WEIGHT));
    }

    @Test
    public void testConvertEmptyModelToParams() {
        PackageDimensionsModel model = new PackageDimensionsModelImpl();
        Map<String, Object> params = converter.convertModelToParams(model);
        Assert.assertNotNull(params);
    }

    @Test
    public void testConvertParamsToModel() {
        Map<String, Object> params = new HashMap<>();
        params.put(PackageDimensionsFields.HEIGHT, 1d);
        params.put(PackageDimensionsFields.LENGTH, 2d);
        params.put(PackageDimensionsFields.WIDTH, 3d);
        params.put(PackageDimensionsFields.WEIGHT, 4d);

        PackageDimensionsModel model = converter.convertParamsToModel(params);
        Assert.assertEquals((Double) 1d, model.getHeight());
        Assert.assertEquals((Double) 2d, model.getLength());
        Assert.assertEquals((Double) 3d, model.getWidth());
        Assert.assertEquals((Double) 4d, model.getWeight());
    }

    @Test
    public void testConvertEmptyParamsToModel() {
        Map<String, Object> params = new HashMap<>();
        PackageDimensionsModel model = converter.convertParamsToModel(params);
        Assert.assertNotNull(model);
    }

    @Test
    public void testConvertEntityToModel() {
        PackageDimensions entity = new PackageDimensions();
        entity.setHeight(1d);
        entity.setLength(2d);
        entity.setWidth(3d);
        entity.setWeight(4d);

        PackageDimensionsModel model = converter.convertEntityToModel(entity);
        Assert.assertEquals((Double) 1d, model.getHeight());
        Assert.assertEquals((Double) 2d, model.getLength());
        Assert.assertEquals((Double) 3d, model.getWidth());
        Assert.assertEquals((Double) 4d, model.getWeight());
    }

    @Test
    public void testConvertEmptyEntityToModel() {
        PackageDimensions entity = new PackageDimensions();
        PackageDimensionsModel model = converter.convertEntityToModel(entity);
        Assert.assertNotNull(model);
    }
}
