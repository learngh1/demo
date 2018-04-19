package com.example.stripe.stripe_depended.converter.product;

import com.stripe.model.PackageDimensions;
import com.stripe.model.Product;
import com.example.stripe.api.model.product.ProductModel;
import com.example.stripe.api.model.product.ProductType;
import com.example.stripe.api.model.shipping.PackageDimensionsModel;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.shipping.PackageDimensionsConverter;
import com.example.stripe.impl.exception.InvalidProductTypeException;
import com.example.stripe.impl.model.product.ProductFields;
import com.example.stripe.impl.model.product.ProductModelImpl;
import com.example.stripe.impl.util.ValidationUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductConverter implements Converter<ProductModel, Product> {

    private Converter<PackageDimensionsModel, PackageDimensions> packageDimensionsConverter = new PackageDimensionsConverter();

    @Override
    public Map<String, Object> convertModelToParams(ProductModel model) {
        validate(model);
        Map<String, Object> params = new HashMap<>();
        params.put(ProductFields.NAME, model.getName());
        if (model.getType() != null) {
            params.put(ProductFields.TYPE, model.getType().getValue());
        }
        if (model.getDescription() != null) {
            params.put(ProductFields.DESCRIPTION, model.getDescription());
        }
        if (model.getAttributes() != null) {
            params.put(ProductFields.ATTRIBUTES, model.getAttributes());
        }
        if (model.getPackageDimensions() != null) {
            params.put(ProductFields.PACKAGE_DIMENSIONS, packageDimensionsConverter.convertModelToParams(model.getPackageDimensions()));
        }
        if (model.getType() != null && model.getType().equals(ProductType.GOOD)) {
            params.put(ProductFields.SHIPPABLE, model.isShippable());
        }
        return params;
    }

    @Override
    public ProductModel convertParamsToModel(Map<String, Object> params) {
        ValidationUtil.validateRequired(ProductFields.NAME, params.get(ProductFields.NAME));
        ProductModel model = new ProductModelImpl((String) params.get(ProductFields.NAME));
        if (params.get(ProductFields.ID) != null) {
            model.setId((String) params.get(ProductFields.ID));
        }
        if (params.get(ProductFields.TYPE) != null) {
            model.setType(getType((String) params.get(ProductFields.TYPE)));
        }
        if (params.get(ProductFields.DESCRIPTION) != null) {
            model.setDescription((String) params.get(ProductFields.DESCRIPTION));
        }
        if (params.get(ProductFields.ATTRIBUTES) != null) {
            model.setAttributes((List<String>) params.get(ProductFields.ATTRIBUTES));
        }
        if (params.get(ProductFields.SHIPPABLE) != null && params.get(ProductFields.SHIPPABLE).toString().equalsIgnoreCase("true")) {
            model.setShippable(true);
        }
        if (params.get(ProductFields.PACKAGE_DIMENSIONS) != null) {
            model.setPackageDimensions(packageDimensionsConverter.convertParamsToModel((Map<String, Object>) params.get(ProductFields.PACKAGE_DIMENSIONS)));
        }

        return model;
    }

    @Override
    public ProductModel convertEntityToModel(Product product) {
        Map<String, Object> params = new HashMap<>();
        params.put(ProductFields.ID, product.getId());
        params.put(ProductFields.NAME, product.getName());
        params.put(ProductFields.DESCRIPTION, product.getDescription());
        params.put(ProductFields.ATTRIBUTES, product.getAttributes());
        params.put(ProductFields.SHIPPABLE, product.getShippable());
        ProductModel model = convertParamsToModel(params);
        if (product.getPackageDimensions() != null) {
            model.setPackageDimensions(packageDimensionsConverter.convertEntityToModel(product.getPackageDimensions()));
        }
        return model;
    }

    private ProductType getType(String type) {
        if (type.equals("good")) {
            return ProductType.GOOD;
        } else if (type.equals("service")) {
            return ProductType.SERVICE;
        }
        throw new InvalidProductTypeException(type);
    }

    private void validate(ProductModel model) {
        ValidationUtil.validateRequired(ProductFields.NAME, model.getName());

        if (model.getType() != null) {
            String type = model.getType().getValue();
            if (!type.equals(ProductType.SERVICE.getValue()) && !type.equals(ProductType.GOOD.getValue())) {
                throw new InvalidProductTypeException("Unsupported type: " + type);
            }
        }
    }


}
