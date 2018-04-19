package com.example.stripe.stripe_depended.converter.sku;

import com.stripe.model.Inventory;
import com.stripe.model.PackageDimensions;
import com.stripe.model.SKU;
import com.example.stripe.api.model.shipping.PackageDimensionsModel;
import com.example.stripe.api.model.sku.SKUModel;
import com.example.stripe.impl.exception.FieldRequiredException;
import com.example.stripe.impl.exception.InvalidInventoryValueException;
import com.example.stripe.impl.model.sku.SKUFields;
import com.example.stripe.impl.model.sku.SKUModelImpl;
import com.example.stripe.impl.model.sku.inventory.InventoryFields;
import com.example.stripe.impl.model.sku.inventory.InventoryModel;
import com.example.stripe.impl.model.sku.inventory.InventoryType;
import com.example.stripe.impl.model.sku.inventory.InventoryValue;
import com.example.stripe.impl.util.ValidationUtil;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.shipping.PackageDimensionsConverter;

import java.util.HashMap;
import java.util.Map;

public class SKUConverter implements Converter<SKUModel, SKU> {

    private Converter<PackageDimensionsModel, PackageDimensions> packageDimensionsConverter = new PackageDimensionsConverter();

    @Override
    public Map<String, Object> convertModelToParams(SKUModel model) {
        validate(model);
        Map<String, Object> params = new HashMap<>();

        if (model.getId() != null) {
            params.put(SKUFields.ID, model.getId());
        }

        params.put(SKUFields.CURRENCY, model.getCurrency());
        params.put(SKUFields.PRICE, model.getPrice());
        params.put(SKUFields.PRODUCT, model.getProductId());
        if (model.getMetadata() != null && !model.getMetadata().isEmpty()) {
            params.put(SKUFields.METADATA, model.getMetadata());
        }

        if (model.getAttributes() != null) {
            params.put(SKUFields.ATTRIBUTES, model.getAttributes());
        }

        Map<String, Object> inventoryParams = new HashMap<>();

        InventoryModel inventory = model.getInventory();
        if (inventory.getQuantity() != null) {
            inventoryParams.put(InventoryFields.QUANTITY, inventory.getQuantity());
        }
        if (inventory.getType() != null) {
            inventoryParams.put(InventoryFields.TYPE, inventory.getType().name().toLowerCase());
        }
        if (inventory.getValue() != null) {
            inventoryParams.put(InventoryFields.VALUE, inventory.getValue().name().toLowerCase());
        }
        if (model.getPackageDimensions() != null) {
            params.put(SKUFields.PACKAGE_DIMENSIONS, packageDimensionsConverter.convertModelToParams(model.getPackageDimensions()));
        }

        params.put(SKUFields.INVENTORY, inventoryParams);

        return params;
    }

    @Override
    public SKUModel convertParamsToModel(Map<String, Object> params) {
        ValidationUtil.validateRequired(SKUFields.CURRENCY, params.get(SKUFields.CURRENCY));
        ValidationUtil.validateRequired(SKUFields.INVENTORY, params.get(SKUFields.INVENTORY));
        ValidationUtil.validateRequired(SKUFields.PRICE, params.get(SKUFields.PRICE));
        ValidationUtil.validateRequired(SKUFields.PRODUCT, params.get(SKUFields.PRODUCT));
        
        SKUModel model = new SKUModelImpl();

        model.setProductId((String) params.get(SKUFields.PRODUCT));

        model.setPrice(Integer.valueOf(params.get(SKUFields.PRICE).toString()));
        if (params.get(SKUFields.ID) != null) {
            model.setId((String) params.get(SKUFields.ID));
        }

        if (params.get(SKUFields.ATTRIBUTES) != null) {
            model.setAttributes((Map<String, String>) params.get(SKUFields.ATTRIBUTES));
        }

        model.setMetadata((Map<String, String>) params.get(SKUFields.METADATA));

        String currencyString = (String) params.get(SKUFields.CURRENCY);
        model.setCurrency(currencyString);

        InventoryModel inventoryModel = new InventoryModel();
        model.setInventory(inventoryModel);

        Map<String, Object> inventoryParams = (Map<String, Object>) params.get(SKUFields.INVENTORY);
        if (inventoryParams.get(InventoryFields.QUANTITY) != null) {
            inventoryModel.setQuantity(Integer.valueOf(inventoryParams.get(InventoryFields.QUANTITY).toString()));
        }
        if (inventoryParams.get(InventoryFields.TYPE) != null) {
            InventoryType type;
            String typeStr = (String) inventoryParams.get(InventoryFields.TYPE);
            if (typeStr.equals(InventoryType.FINITE.name().toLowerCase())) {
                type = InventoryType.FINITE;
            } else if (typeStr.equals(InventoryType.INFINITE.name().toLowerCase())) {
                type = InventoryType.INFINITE;
            } else if (typeStr.equals(InventoryType.BUCKET.name().toLowerCase())) {
                type = InventoryType.BUCKET;
            } else {
                throw new IllegalArgumentException("unsupported inventory type: " + typeStr);
            }
            inventoryModel.setType(type);
        }
        if (inventoryParams.get(InventoryFields.VALUE) != null) {
            InventoryValue value;
            String valueStr = (String) inventoryParams.get(InventoryFields.VALUE);
            if (valueStr.equals(InventoryValue.IN_STOCK.name().toLowerCase())) {
                value = InventoryValue.IN_STOCK;
            } else if (valueStr.equals(InventoryValue.OUT_OF_STOCK.name().toLowerCase())) {
                value = InventoryValue.OUT_OF_STOCK;
            } else if (valueStr.equals(InventoryValue.LIMITED.name().toLowerCase())) {
                value = InventoryValue.LIMITED;
            } else {
                throw new InvalidInventoryValueException("Unsupported inventory value: " + valueStr);
            }
            inventoryModel.setValue(value);
        }

        if (params.get(SKUFields.PACKAGE_DIMENSIONS) != null) {
            model.setPackageDimensions(packageDimensionsConverter.convertParamsToModel((Map<String, Object>) params.get(SKUFields.PACKAGE_DIMENSIONS)));
        }
        
        return model;
    }

    @Override
    public SKUModel convertEntityToModel(SKU entity) {
        Map<String, Object> params = new HashMap<>();
        params.put(SKUFields.ID, entity.getId());
        params.put(SKUFields.PRODUCT, entity.getProduct());
        params.put(SKUFields.PRICE, entity.getPrice());
        params.put(SKUFields.CURRENCY, entity.getCurrency());
        params.put(SKUFields.ATTRIBUTES, entity.getAttributes());
        params.put(SKUFields.METADATA, entity.getMetadata());
        Map<String, Object> inventoryParams = new HashMap<>();

        Inventory inventory = entity.getInventory();
        inventoryParams.put(InventoryFields.QUANTITY, inventory.getQuantity());
        inventoryParams.put(InventoryFields.TYPE, inventory.getType());
        inventoryParams.put(InventoryFields.VALUE, inventory.getValue());

        params.put(SKUFields.INVENTORY, inventoryParams);

        SKUModel model = convertParamsToModel(params);

        if (entity.getPackageDimensions() != null) {
            model.setPackageDimensions(packageDimensionsConverter.convertEntityToModel(entity.getPackageDimensions()));
        }

        return model;
    }

    private void validate(SKUModel model) {
        ValidationUtil.validateRequired(SKUFields.CURRENCY, model.getCurrency());
        ValidationUtil.validateRequired(SKUFields.INVENTORY, model.getInventory());
        ValidationUtil.validateRequired(SKUFields.PRICE, model.getPrice());
        ValidationUtil.validateRequired(SKUFields.PRODUCT, model.getProductId());

        validateInventory(model.getInventory());
    }

    private void validateInventory(InventoryModel inventory) {
        InventoryType type = inventory.getType();

        if (type != null && type.equals(InventoryType.FINITE) && inventory.getQuantity() == null) {
            throw new FieldRequiredException(InventoryFields.QUANTITY, " quantity is required when type is " + InventoryType.FINITE.name());
        }
        if (inventory.getValue() != null) {
            if (type == null || !type.equals(InventoryType.BUCKET)) {
                throw new InvalidInventoryValueException("inventory value can be set if and only if type is " + InventoryType.BUCKET.name());
            }
        }
    }
}
