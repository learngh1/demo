package com.example.stripe.converter;

import com.stripe.model.Inventory;
import com.stripe.model.PackageDimensions;
import com.stripe.model.SKU;
import com.example.stripe.api.model.shipping.PackageDimensionsModel;
import com.example.stripe.api.model.sku.SKUModel;
import com.example.stripe.impl.exception.FieldRequiredException;
import com.example.stripe.impl.exception.InvalidInventoryValueException;
import com.example.stripe.impl.model.shipping.PackageDimensionsFields;
import com.example.stripe.impl.model.shipping.PackageDimensionsModelImpl;
import com.example.stripe.impl.model.sku.SKUFields;
import com.example.stripe.impl.model.sku.SKUModelImpl;
import com.example.stripe.impl.model.sku.inventory.InventoryFields;
import com.example.stripe.impl.model.sku.inventory.InventoryModel;
import com.example.stripe.impl.model.sku.inventory.InventoryType;
import com.example.stripe.impl.model.sku.inventory.InventoryValue;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.sku.SKUConverter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SKUConverterTest {
    Converter<SKUModel, SKU> converter = new SKUConverter();

    private SKUModel instantiateModelAndFillRequiredFields() {
        SKUModel model = new SKUModelImpl();
        model.setCurrency("usd");
        model.setInventory(new InventoryModel());
        model.setPrice(1);
        model.setProductId("product id 1");
        return model;
    }

    @Test (expected = FieldRequiredException.class)
    public void testRequiredCurrencyValidation() {
        SKUModel model = instantiateModelAndFillRequiredFields();
        model.setCurrency(null);
        converter.convertModelToParams(model);
    }

    @Test (expected = FieldRequiredException.class)
    public void testRequiredInventoryValidation() {
        SKUModel model = instantiateModelAndFillRequiredFields();
        model.setInventory(null);
        converter.convertModelToParams(model);
    }

    @Test (expected = FieldRequiredException.class)
    public void testRequiredPriceValidation() {
        SKUModel model = instantiateModelAndFillRequiredFields();
        model.setPrice(null);
        converter.convertModelToParams(model);
    }

    @Test (expected = FieldRequiredException.class)
    public void testRequiredProductValidation() {
        SKUModel model = instantiateModelAndFillRequiredFields();
        model.setProductId(null);
        converter.convertModelToParams(model);
    }

    @Test (expected = FieldRequiredException.class)
    public void testRequiredQuantityWhenTypeIsFiniteValidation() {
        SKUModel model = instantiateModelAndFillRequiredFields();
        InventoryModel inventory = model.getInventory();
        inventory.setType(InventoryType.FINITE);
        converter.convertModelToParams(model);
    }

    @Test (expected = InvalidInventoryValueException.class)
    public void testRequiredInventoryTypeWhenInventoryValueIsSetValidation() {
        SKUModel model = instantiateModelAndFillRequiredFields();
        InventoryModel inventory = model.getInventory();
        inventory.setValue(InventoryValue.IN_STOCK);
        converter.convertModelToParams(model);
    }

    @Test (expected = InvalidInventoryValueException.class)
    public void testInventoryTypeFiniteInvalidWhenInventoryValueIsSet() {
        SKUModel model = instantiateModelAndFillRequiredFields();
        InventoryModel inventory = model.getInventory();
        inventory.setQuantity(9);
        inventory.setValue(InventoryValue.IN_STOCK);
        inventory.setType(InventoryType.FINITE);
        converter.convertModelToParams(model);
    }

    @Test (expected = InvalidInventoryValueException.class)
    public void testInventoryTypeInfiniteInvalidWhenInventoryValueIsSet() {
        SKUModel model = instantiateModelAndFillRequiredFields();
        InventoryModel inventory = model.getInventory();
        inventory.setValue(InventoryValue.IN_STOCK);
        inventory.setType(InventoryType.INFINITE);
        converter.convertModelToParams(model);
    }

    @Test
    public void testConvertParamsToModel() {
        String currencyString = "usd";
        String productIdString = "product id";
        String priceString = "123";
        String idString = "skuid";
        String inventoryQuantityStirng = "345";
        String inventoryTypeString = InventoryType.BUCKET.name().toLowerCase();
        String inventoryValueString = InventoryValue.OUT_OF_STOCK.name().toLowerCase();
        Map<String, String> attributesMap = new HashMap<>();
        attributesMap.put("size", "big");
        Map<String, String> metadata = new HashMap<>();
        metadata.put("metadata_key", "metadata_val");

        Map<String, Object> params = new HashMap<>();
        params.put(SKUFields.CURRENCY, currencyString);
        params.put(SKUFields.PRODUCT, productIdString);
        params.put(SKUFields.PRICE, priceString);
        params.put(SKUFields.ID, idString);
        params.put(SKUFields.ATTRIBUTES, attributesMap);
        params.put(SKUFields.METADATA, metadata);
        Map<String, Object> inventoryParams = new HashMap<>();
        params.put(SKUFields.INVENTORY, inventoryParams);
        inventoryParams.put(InventoryFields.QUANTITY, inventoryQuantityStirng);
        inventoryParams.put(InventoryFields.TYPE, inventoryTypeString);
        inventoryParams.put(InventoryFields.VALUE, inventoryValueString);

        Map<String, Object> packageDimensionsParams = new HashMap<>();
        params.put(SKUFields.PACKAGE_DIMENSIONS, packageDimensionsParams);
        packageDimensionsParams.put(PackageDimensionsFields.HEIGHT, 1d);
        packageDimensionsParams.put(PackageDimensionsFields.LENGTH, 2d);
        packageDimensionsParams.put(PackageDimensionsFields.WIDTH, 3d);
        packageDimensionsParams.put(PackageDimensionsFields.WEIGHT, 4d);

        SKUModel model = converter.convertParamsToModel(params);
        Assert.assertEquals(currencyString, model.getCurrency());
        Assert.assertEquals(productIdString, model.getProductId());
        Assert.assertEquals(Integer.valueOf(priceString), model.getPrice());
        Assert.assertEquals(idString, model.getId());
        Assert.assertEquals(attributesMap.size(), model.getAttributes().size());
        Assert.assertEquals(attributesMap.get("size"), model.getAttributes().get("size"));

        Assert.assertEquals(metadata.size(), model.getMetadata().size());
        Assert.assertEquals(metadata.get("metadata_key"), model.getMetadata().get("metadata_key"));

        Assert.assertNotNull(model.getInventory());
        Assert.assertEquals(Integer.valueOf(inventoryQuantityStirng), model.getInventory().getQuantity());
        Assert.assertEquals(inventoryTypeString, model.getInventory().getType().name().toLowerCase());
        Assert.assertEquals(inventoryValueString, model.getInventory().getValue().name().toLowerCase());

        PackageDimensionsModel packageDimensionsModel = model.getPackageDimensions();
        Assert.assertEquals(Double.valueOf(1d), packageDimensionsModel.getHeight());
        Assert.assertEquals(Double.valueOf(2d), packageDimensionsModel.getLength());
        Assert.assertEquals(Double.valueOf(3d), packageDimensionsModel.getWidth());
        Assert.assertEquals(Double.valueOf(4d), packageDimensionsModel.getWeight());
    }

    @Test(expected = FieldRequiredException.class)
    public void testConvertParamsToModelCurrencyRequiredValidation() {
        Map<String, Object> params = new HashMap<>();
        //params.put(SKUFields.CURRENCY, "usd");
        params.put(SKUFields.PRODUCT, "product id");
        params.put(SKUFields.PRICE, 2);
        params.put(SKUFields.INVENTORY, new HashMap<String, Object>());
        converter.convertParamsToModel(params);
    }

    @Test(expected = FieldRequiredException.class)
    public void testConvertParamsToModelProductRequiredValidation() {
        Map<String, Object> params = new HashMap<>();
        params.put(SKUFields.CURRENCY, "usd");
        //params.put(SKUFields.PRODUCT, "product id");
        params.put(SKUFields.PRICE, 2);
        params.put(SKUFields.INVENTORY, new HashMap<String, Object>());
        converter.convertParamsToModel(params);
    }

    @Test(expected = FieldRequiredException.class)
    public void testConvertParamsToModelPriceRequiredValidation() {
        Map<String, Object> params = new HashMap<>();
        params.put(SKUFields.CURRENCY, "usd");
        params.put(SKUFields.PRODUCT, "product id");
        //params.put(SKUFields.PRICE, 2);
        params.put(SKUFields.INVENTORY, new HashMap<String, Object>());
        converter.convertParamsToModel(params);
    }

    @Test(expected = FieldRequiredException.class)
    public void testConvertParamsToModelInventoryRequiredValidation() {
        Map<String, Object> params = new HashMap<>();
        params.put(SKUFields.CURRENCY, "usd");
        params.put(SKUFields.PRODUCT, "product id");
        params.put(SKUFields.PRICE, 2);
        //params.put(SKUFields.INVENTORY, new HashMap<String, Object>());
        converter.convertParamsToModel(params);
    }

    @Test
    public void testConvertParamsToModelWithoutIdAndAttributes() {
        Map<String, Object> params = new HashMap<>();
        params.put(SKUFields.CURRENCY, "usd");
        params.put(SKUFields.PRODUCT, "product id");
        params.put(SKUFields.PRICE, 2);
        params.put(SKUFields.INVENTORY, new HashMap<String, Object>());
        SKUModel model = converter.convertParamsToModel(params);
        Assert.assertEquals(params.get(SKUFields.CURRENCY), model.getCurrency());
        Assert.assertEquals(params.get(SKUFields.PRODUCT), model.getProductId());
        Assert.assertEquals(params.get(SKUFields.PRICE), model.getPrice());
        Assert.assertNotNull(model.getInventory());
    }

    @Test
    public void testInventoryTypeInfinite() {
        Map<String, Object> params = new HashMap<>();
        params.put(SKUFields.CURRENCY, "usd");
        params.put(SKUFields.PRODUCT, "product id");
        params.put(SKUFields.PRICE, 2);
        Map<String, Object> inventoryMap = new HashMap<>();
        inventoryMap.put(InventoryFields.TYPE, InventoryType.INFINITE.name().toLowerCase());
        params.put(SKUFields.INVENTORY, inventoryMap);
        SKUModel model = converter.convertParamsToModel(params);
        Assert.assertEquals(InventoryType.INFINITE, model.getInventory().getType());
    }

    @Test
    public void testInventoryValueLimited() {
        Map<String, Object> params = new HashMap<>();
        params.put(SKUFields.CURRENCY, "usd");
        params.put(SKUFields.PRODUCT, "product id");
        params.put(SKUFields.PRICE, 2);
        Map<String, Object> inventoryMap = new HashMap<>();
        inventoryMap.put(InventoryFields.TYPE, InventoryType.BUCKET.name().toLowerCase());
        inventoryMap.put(InventoryFields.VALUE, InventoryValue.LIMITED.name().toLowerCase());
        params.put(SKUFields.INVENTORY, inventoryMap);
        SKUModel model = converter.convertParamsToModel(params);
        Assert.assertEquals(InventoryValue.LIMITED, model.getInventory().getValue());
    }

    @Test(expected = InvalidInventoryValueException.class)
    public void testUnsupportedInventoryValueException() {
        Map<String, Object> params = new HashMap<>();
        params.put(SKUFields.CURRENCY, "usd");
        params.put(SKUFields.PRODUCT, "product id");
        params.put(SKUFields.PRICE, 2);
        Map<String, Object> inventoryMap = new HashMap<>();
        inventoryMap.put(InventoryFields.TYPE, InventoryType.BUCKET.name().toLowerCase());
        inventoryMap.put(InventoryFields.VALUE, "invalid inventory value example");
        params.put(SKUFields.INVENTORY, inventoryMap);
        converter.convertParamsToModel(params);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnsupportedInventoryTypeValiation() {
        Map<String, Object> params = new HashMap<>();
        params.put(SKUFields.CURRENCY, "usd");
        params.put(SKUFields.PRODUCT, "product id");
        params.put(SKUFields.PRICE, 2);
        Map<String, Object> inventoryMap = new HashMap<>();
        inventoryMap.put(InventoryFields.TYPE, "unsupported example");

        params.put(SKUFields.INVENTORY, inventoryMap);
        converter.convertParamsToModel(params);
    }

    @Test
    public void testConvertModelToParamsWithoutAttributesAndWithEmptyIntentory() {
        String id = "sku id";
        Integer price = 1;
        String productId = "product id 1";
        String currency = "usd";

        SKUModel model = new SKUModelImpl();
        model.setId(id);
        model.setCurrency(currency);
        model.setInventory(new InventoryModel());
        model.setPrice(price);
        model.setProductId(productId);

        Map<String, Object> params = converter.convertModelToParams(model);

        Assert.assertEquals(id, params.get(SKUFields.ID));
        Assert.assertEquals(currency, params.get(SKUFields.CURRENCY));
        Assert.assertEquals(price, params.get(SKUFields.PRICE));
        Assert.assertEquals(productId, params.get(SKUFields.PRODUCT));

        Map<String, Object> inventoryParams = (Map<String, Object>) params.get(SKUFields.INVENTORY);
        Assert.assertNotNull(inventoryParams);
    }

    @Test
    public void testConvertModelToParams() {
        String id = "sku id";
        Integer price = 1;
        String productId = "product id 1";
        String currency = "usd";
        Map<String, String> attributesMap = new HashMap<>();
        attributesMap.put("size", "big");
        Integer inventoryQuantity = 9;
        InventoryValue inventoryValue = InventoryValue.IN_STOCK;
        InventoryType inventoryType = InventoryType.BUCKET;

        SKUModel model = new SKUModelImpl();
        model.setId(id);
        model.setCurrency(currency);
        model.setInventory(new InventoryModel());
        model.setPrice(price);
        model.setProductId(productId);
        model.setAttributes(attributesMap);

        Map<String, String> metadata = new HashMap<>();
        metadata.put("metadata_key", "metadata_value");
        model.setMetadata(metadata);

        InventoryModel inventory = model.getInventory();
        inventory.setQuantity(inventoryQuantity);
        inventory.setValue(inventoryValue);
        inventory.setType(inventoryType);

        model.setPackageDimensions(new PackageDimensionsModelImpl(1d, 2d, 3d, 4d));

        Map<String, Object> params = converter.convertModelToParams(model);
        Assert.assertEquals(id, params.get(SKUFields.ID));
        Assert.assertEquals(currency, params.get(SKUFields.CURRENCY));
        Assert.assertEquals(price, params.get(SKUFields.PRICE));
        Assert.assertEquals(productId, params.get(SKUFields.PRODUCT));
        Assert.assertEquals(attributesMap.size(), ((Map<String, String>) params.get(SKUFields.ATTRIBUTES)).size());
        Assert.assertEquals(attributesMap.get("size"), ((Map<String, String>) params.get(SKUFields.ATTRIBUTES)).get("size"));

        Assert.assertEquals(model.getMetadata().size(), ((Map<String, String>) params.get(SKUFields.METADATA)).size());
        Assert.assertEquals(model.getMetadata().get("metadata_key"), ((Map<String, String>) params.get(SKUFields.METADATA)).get("metadata_key"));

        Map<String, Object> inventoryParams = (Map<String, Object>) params.get(SKUFields.INVENTORY);
        Assert.assertEquals(inventoryQuantity, inventoryParams.get(InventoryFields.QUANTITY));
        Assert.assertEquals(inventoryType.name().toLowerCase(), inventoryParams.get(InventoryFields.TYPE));
        Assert.assertEquals(inventoryValue.name().toLowerCase(), inventoryParams.get(InventoryFields.VALUE));

        Map<String, Object> packageDimensionsParams = (Map<String, Object>) params.get(SKUFields.PACKAGE_DIMENSIONS);
        Assert.assertEquals(Double.valueOf(1d), packageDimensionsParams.get(PackageDimensionsFields.HEIGHT));
        Assert.assertEquals(Double.valueOf(2d), packageDimensionsParams.get(PackageDimensionsFields.LENGTH));
        Assert.assertEquals(Double.valueOf(3d), packageDimensionsParams.get(PackageDimensionsFields.WIDTH));
        Assert.assertEquals(Double.valueOf(4d), packageDimensionsParams.get(PackageDimensionsFields.WEIGHT));
    }

    @Test
    public void testConvertEntityToModel() {
        String id = "sku id";
        Integer price = 1;
        String productId = "product id 1";
        String currency = "usd";
        Integer inventoryQuantity = 9;
        InventoryValue inventoryValue = InventoryValue.IN_STOCK;
        InventoryType inventoryType = InventoryType.BUCKET;
        Map<String, String> attributesMap = new HashMap<>();
        attributesMap.put("size", "big");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("metadata_key", "metadata_value");

        SKU sku = new SKU();
        sku.setId(id);
        sku.setPrice(price);
        sku.setProduct(productId);
        sku.setCurrency(currency);
        sku.setAttributes(attributesMap);
        sku.setMetadata(metadata);
        Inventory inventory = new Inventory();
        sku.setInventory(inventory);
        inventory.setQuantity(inventoryQuantity);
        inventory.setType(inventoryType.name().toLowerCase());
        inventory.setValue(inventoryValue.name().toLowerCase());

        PackageDimensions packageDimensions = new PackageDimensions();
        packageDimensions.setHeight(1d);
        packageDimensions.setLength(2d);
        packageDimensions.setWidth(3d);
        packageDimensions.setWeight(4d);
        sku.setPackageDimensions(packageDimensions);

        SKUModel model = converter.convertEntityToModel(sku);

        Assert.assertEquals(id, model.getId());
        Assert.assertEquals(price, model.getPrice());
        Assert.assertEquals(productId, model.getProductId());
        Assert.assertEquals(currency, model.getCurrency());
        Assert.assertEquals(attributesMap.size(), model.getAttributes().size());
        Assert.assertEquals(attributesMap.get("size"), model.getAttributes().get("size"));

        Assert.assertEquals(metadata.size(), model.getMetadata().size());
        Assert.assertEquals(metadata.get("metadata_key"), model.getMetadata().get("metadata_key"));

        Assert.assertEquals(inventoryQuantity, model.getInventory().getQuantity());
        Assert.assertEquals(inventoryType, model.getInventory().getType());
        Assert.assertEquals(inventoryValue, model.getInventory().getValue());

        PackageDimensionsModel packageDimensionsModel = model.getPackageDimensions();
        Assert.assertEquals(Double.valueOf(1d), packageDimensionsModel.getHeight());
        Assert.assertEquals(Double.valueOf(2d), packageDimensionsModel.getLength());
        Assert.assertEquals(Double.valueOf(3d), packageDimensionsModel.getWidth());
        Assert.assertEquals(Double.valueOf(4d), packageDimensionsModel.getWeight());
    }
}
