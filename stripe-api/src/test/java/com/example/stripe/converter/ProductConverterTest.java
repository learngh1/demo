package com.example.stripe.converter;

import com.stripe.model.PackageDimensions;
import com.stripe.model.Product;
import com.example.stripe.api.model.shipping.PackageDimensionsModel;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.product.ProductConverter;
import com.example.stripe.impl.exception.FieldRequiredException;
import com.example.stripe.impl.exception.InvalidProductTypeException;
import com.example.stripe.impl.model.product.ProductFields;
import com.example.stripe.api.model.product.ProductModel;
import com.example.stripe.api.model.product.ProductType;
import com.example.stripe.impl.model.product.ProductModelImpl;
import com.example.stripe.impl.model.shipping.PackageDimensionsFields;
import com.example.stripe.impl.model.shipping.PackageDimensionsModelImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductConverterTest {
    Converter<ProductModel, Product> converter = new ProductConverter();

    @Test(expected = FieldRequiredException.class)
    public void testRequiredNameValidation() {
        converter.convertModelToParams(new ProductModelImpl(null));
    }

    @Test(expected = InvalidProductTypeException.class)
    public void testProductTypeValidation() {
        ProductModel model = new ProductModelImpl("name sample");
        model.setType(ProductType.UNSUPPORTED_TYPE_EXAMPLE_FOR_TEST);
        converter.convertModelToParams(model);
    }

    @Test
    public void testDescriptionPresentInResultParams() {
        ProductModel model = new ProductModelImpl("name sample");
        String fieldValue = "Description sample";
        model.setDescription(fieldValue);
        Map<String, Object> params = converter.convertModelToParams(model);
        String fieldName = ProductFields.DESCRIPTION;
        Assert.assertNotNull(String.format("%s field value is null", fieldName), params.get(fieldName));
        Assert.assertEquals(String.format("%s field values arent equal", fieldName), fieldValue, params.get(fieldName));
    }

    @Test
    public void testAttributesPresentInResultParams() {
        ProductModel model = new ProductModelImpl("name sample");
        List<String> attrs = new ArrayList<>(Arrays.asList(new String[]{"attr 1", "attr 2"}));
        model.setAttributes(attrs);
        Map<String, Object> params = converter.convertModelToParams(model);
        String fieldName = ProductFields.ATTRIBUTES;
        Assert.assertNotNull(String.format("%s field value is null", fieldName), params.get(fieldName));
        Assert.assertEquals(String.format("%s sizes", fieldName), attrs.size(), ((List<String>) params.get(fieldName)).size());
    }

    @Test(expected = FieldRequiredException.class)
    public void testConvertParamsToModelWithoutName() {
        Map<String, Object> params = new HashMap<>();
        converter.convertParamsToModel(params);
    }

    @Test
    public void testConvertFromModelToParams() {
        ProductModel model = new ProductModelImpl("name sample");
        List<String> attrs = new ArrayList<>(Arrays.asList(new String[]{"attr 1", "attr 2"}));
        String description = "Description sample";
        model.setDescription(description);
        model.setAttributes(attrs);
        model.setType(ProductType.SERVICE);
        model.setShippable(true);
        model.setPackageDimensions(new PackageDimensionsModelImpl(1d, 2d, 3d, 4d));

        Map<String, Object> params = converter.convertModelToParams(model);
        Assert.assertEquals("names arent equal", model.getName(), params.get(ProductFields.NAME));
        Assert.assertEquals("types arent equal", "service", params.get(ProductFields.TYPE));
        Assert.assertEquals("descriptions arent equal", description, params.get(ProductFields.DESCRIPTION));
        Assert.assertEquals("attributes sizes arent equal", attrs.size(), ((List<String>) params.get(ProductFields.ATTRIBUTES)).size());
        Assert.assertNull(params.get(ProductFields.SHIPPABLE));
        Map<String, Object> packageDimensionsParams = (Map<String, Object>) params.get(ProductFields.PACKAGE_DIMENSIONS);
        Assert.assertEquals(Double.valueOf(1d), packageDimensionsParams.get(PackageDimensionsFields.HEIGHT));
        Assert.assertEquals(Double.valueOf(2d), packageDimensionsParams.get(PackageDimensionsFields.LENGTH));
        Assert.assertEquals(Double.valueOf(3d), packageDimensionsParams.get(PackageDimensionsFields.WIDTH));
        Assert.assertEquals(Double.valueOf(4d), packageDimensionsParams.get(PackageDimensionsFields.WEIGHT));
    }

    @Test
    public void testConvertFromStripeProductToModel() {
        String idValue = "2";
        String nameValue = "name 1";
        String descValue = "desc 1";
        List<String> attrsValue = new ArrayList<>(Arrays.asList(new String[]{"size", "color"}));

        Product product = new Product();
        product.setId(idValue);
        product.setName(nameValue);
        product.setDescription(descValue);
        product.setAttributes(attrsValue);
        PackageDimensions packageDimensions = new PackageDimensions();
        packageDimensions.setHeight(1d);
        packageDimensions.setLength(2d);
        packageDimensions.setWidth(3d);
        packageDimensions.setWeight(4d);
        product.setPackageDimensions(packageDimensions);

        ProductModel model = converter.convertEntityToModel(product);
        Assert.assertEquals(idValue, model.getId());
        Assert.assertEquals(nameValue, model.getName());
        Assert.assertEquals(descValue, model.getDescription());
        Assert.assertEquals(attrsValue.size(), model.getAttributes().size());
        PackageDimensionsModel packageDimensionsModel = model.getPackageDimensions();
        Assert.assertEquals(Double.valueOf(1d), packageDimensionsModel.getHeight());
        Assert.assertEquals(Double.valueOf(2d), packageDimensionsModel.getLength());
        Assert.assertEquals(Double.valueOf(3d), packageDimensionsModel.getWidth());
        Assert.assertEquals(Double.valueOf(4d), packageDimensionsModel.getWeight());
    }

    @Test
    public void testConvertEmptyEntityToModel() {
        Product product = new Product();
        product.setName("name");
        ProductModel model = converter.convertEntityToModel(product);
        Assert.assertNotNull(model);
    }

    @Test
    public void testConvertFromParamsToModelWithouDescriptionAndAttributes() {
        String idValue = "2";
        String nameValue = "name 1";
        String typeValue = "good";
        Map<String, Object> params = new HashMap<>();
        params.put(ProductFields.ID, idValue);
        params.put(ProductFields.NAME, nameValue);
        params.put(ProductFields.TYPE, typeValue);
        ProductModel model = converter.convertParamsToModel(params);
        Assert.assertEquals(idValue, model.getId());
        Assert.assertEquals(nameValue, model.getName());
        Assert.assertEquals(typeValue, model.getType().getValue());
    }

    @Test
    public void testConvertFromParamsToModel() {
        String idValue = "2";
        String nameValue = "name 1";
        String typeValue = "service";
        String descValue = "desc 1";
        List<String> attrsValue = new ArrayList<>(Arrays.asList(new String[]{"size", "color"}));
        Boolean shippable = Boolean.TRUE;

        Map<String, Object> params = new HashMap<>();

        params.put(ProductFields.ID, idValue);
        params.put(ProductFields.NAME, nameValue);
        params.put(ProductFields.TYPE, typeValue);
        params.put(ProductFields.DESCRIPTION, descValue);
        params.put(ProductFields.ATTRIBUTES, attrsValue);
        params.put(ProductFields.SHIPPABLE, shippable);
        Map<String, Object> packageDimensionsParams = new HashMap<>();
        params.put(ProductFields.PACKAGE_DIMENSIONS, packageDimensionsParams);
        packageDimensionsParams.put(PackageDimensionsFields.HEIGHT, 1d);
        packageDimensionsParams.put(PackageDimensionsFields.LENGTH, 2d);
        packageDimensionsParams.put(PackageDimensionsFields.WIDTH, 3d);
        packageDimensionsParams.put(PackageDimensionsFields.WEIGHT, 4d);

        ProductModel model = converter.convertParamsToModel(params);
        Assert.assertEquals(idValue, model.getId());
        Assert.assertEquals(nameValue, model.getName());
        Assert.assertEquals(typeValue, model.getType().getValue());
        Assert.assertEquals(descValue, model.getDescription());
        Assert.assertEquals(attrsValue.size(), model.getAttributes().size());
        Assert.assertEquals(shippable, model.isShippable());
        PackageDimensionsModel packageDimensionsModel = model.getPackageDimensions();
        Assert.assertEquals(Double.valueOf(1d), packageDimensionsModel.getHeight());
        Assert.assertEquals(Double.valueOf(2d), packageDimensionsModel.getLength());
        Assert.assertEquals(Double.valueOf(3d), packageDimensionsModel.getWidth());
        Assert.assertEquals(Double.valueOf(4d), packageDimensionsModel.getWeight());
    }

    @Test(expected = InvalidProductTypeException.class)
    public void testOneMoreProductTypeValidation() {
        Map<String, Object> params = new HashMap<>();
        params.put(ProductFields.NAME, "sample name");
        params.put(ProductFields.TYPE, ProductType.UNSUPPORTED_TYPE_EXAMPLE_FOR_TEST.getValue());
        converter.convertParamsToModel(params);
    }
}
