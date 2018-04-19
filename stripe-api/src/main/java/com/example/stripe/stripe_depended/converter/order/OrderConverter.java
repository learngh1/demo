package com.example.stripe.stripe_depended.converter.order;

import com.stripe.model.Order;
import com.stripe.model.OrderItem;
import com.stripe.model.ShippingDetails;
import com.stripe.model.ShippingMethod;
import com.example.stripe.api.model.order.OrderItemModel;
import com.example.stripe.api.model.order.OrderModel;
import com.example.stripe.api.model.order.OrderStatus;
import com.example.stripe.api.model.shipping.ShippingDetailsModel;
import com.example.stripe.api.model.shipping.ShippingMethodModel;
import com.example.stripe.impl.model.order.OrderModelImpl;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.shipping.ShippingDetailsConverter;
import com.example.stripe.stripe_depended.converter.shipping.ShippingMethodConverter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderConverter implements Converter<OrderModel, Order> {

    private Converter<OrderItemModel, OrderItem> orderItemConverter = new OrderItemConverter();
    private Converter<ShippingDetailsModel, ShippingDetails> shippingDetailsConverter = new ShippingDetailsConverter();
    private Converter<ShippingMethodModel, ShippingMethod> shippingMethodConverter = new ShippingMethodConverter();

    @Override
    public Map<String, Object> convertModelToParams(OrderModel model) {
        Map<String, Object> params = new HashMap<>();
        if (model.getCurrency() != null) {
            params.put(OrderFields.CURRENCY, model.getCurrency());
        }

        if (model.getEmail() != null) {
            params.put(OrderFields.EMAIL, model.getEmail());
        }
        if (model.getAmount() != null) {
            params.put(OrderFields.AMOUNT, model.getAmount());
        }
        if (model.getApplicationFee() != null) {
            params.put(OrderFields.APPLICATION_FEE, model.getApplicationFee());
        }
        if (model.getChargeId() != null) {
            params.put(OrderFields.CHARGE, model.getChargeId());
        }
        if (model.getCreated() != null) {
            params.put(OrderFields.CREATED, model.getCreated());
        }
        if (model.getShipping() != null) {
            params.put(OrderFields.SHIPPING, shippingDetailsConverter.convertModelToParams(model.getShipping()));
        }
        if (model.getSelectedShippingMethodId() != null) {
            params.put(OrderFields.SELECTED_SHIPPING_METHOD, model.getSelectedShippingMethodId());
        }
        if (model.getStatus() != null) {
            params.put(OrderFields.STATUS, model.getStatus().name().toLowerCase());
        }
        if (model.getShippingMethods() != null) {
            throw new UnsupportedOperationException();
        }

        if (model.getMetadata() != null && !model.getMetadata().isEmpty()) {
            params.put(OrderFields.METADATA, model.getMetadata());
        }

        if (model.getItems() != null) {
            List<Object> itemsParams = new LinkedList<>();
            for (OrderItemModel orderItemModel : model.getItems()) {
                itemsParams.add(orderItemConverter.convertModelToParams(orderItemModel));
            }
            params.put(OrderFields.ITEMS, itemsParams);
        }

        return params;
    }

    @Override
    public OrderModel convertParamsToModel(Map<String, Object> params) {
        OrderModel model = new OrderModelImpl();

        if (params.get(OrderFields.ID) != null) {
            model.setId((String) params.get(OrderFields.ID));
        }
        if (params.get(OrderFields.CURRENCY) != null) {
            model.setCurrency((String) params.get(OrderFields.CURRENCY));
        }
        if (params.get(OrderFields.EMAIL) != null) {
            model.setEmail((String) params.get(OrderFields.EMAIL));
        }

        if (params.get(OrderFields.ITEMS) != null) {
            List<OrderItemModel> items = new LinkedList<>();
            List<Map<String, Object>> itemParamsMap = (List<Map<String, Object>>) params.get(OrderFields.ITEMS);
            for (Map<String, Object> itemParams : itemParamsMap) {
                items.add(orderItemConverter.convertParamsToModel(itemParams));
            }
            model.setItems(items);
        }

        if (params.get(OrderFields.AMOUNT) != null) {
            model.setAmount(Integer.parseInt(params.get(OrderFields.AMOUNT).toString()));
        }
        if (params.get(OrderFields.APPLICATION_FEE) != null) {
            model.setApplicationFee(Integer.parseInt(params.get(OrderFields.APPLICATION_FEE).toString()));
        }
        if (params.get(OrderFields.CHARGE) != null) {
            model.setChargeId((String) params.get(OrderFields.CHARGE));
        }
        if (params.get(OrderFields.CREATED) != null) {
            model.setCreated(Long.parseLong(params.get(OrderFields.CREATED).toString()));
        }

        model.setMetadata((Map<String, String>) params.get(OrderFields.METADATA));

        if (params.get(OrderFields.SELECTED_SHIPPING_METHOD) != null) {
            model.setSelectedShippingMethodId((String) params.get(OrderFields.SELECTED_SHIPPING_METHOD));
        }
        if (params.get(OrderFields.SHIPPING) != null) {
            model.setShipping(shippingDetailsConverter.convertParamsToModel((Map<String, Object>) params.get(OrderFields.SHIPPING)));
        }
        if (params.get(OrderFields.STATUS) != null) {
            model.setStatus(OrderStatus.valueOf(params.get(OrderFields.STATUS).toString().toUpperCase()));
        }
        if (params.get(OrderFields.SHIPPING_METHODS) != null) {
            throw new UnsupportedOperationException();
        }

        return model;
    }

    @Override
    public OrderModel convertEntityToModel(Order entity) {
        Map<String, Object> params = new HashMap<>();

        if (entity.getId() != null) {
            params.put(OrderFields.ID, entity.getId());
        }
        if (entity.getCurrency() != null) {
            params.put(OrderFields.CURRENCY, entity.getCurrency());
        }
        if (entity.getEmail() != null) {
            params.put(OrderFields.EMAIL, entity.getEmail());
        }
        if (entity.getAmount() != null) {
            params.put(OrderFields.AMOUNT, entity.getAmount());
        }
        if (entity.getApplicationFee() != null) {
            params.put(OrderFields.APPLICATION_FEE, entity.getApplicationFee());
        }
        if (entity.getCharge() != null) {
            params.put(OrderFields.CHARGE, entity.getCharge());
        }
        if (entity.getCreated() != null) {
            params.put(OrderFields.CREATED, entity.getCreated());
        }

        params.put(OrderFields.METADATA, entity.getMetadata());

        if (entity.getSelectedShippingMethod() != null) {
            params.put(OrderFields.SELECTED_SHIPPING_METHOD, entity.getSelectedShippingMethod());
        }
        if (entity.getStatus() != null) {
            params.put(OrderFields.STATUS, entity.getStatus());
        }

        OrderModel model = convertParamsToModel(params);

        if (entity.getItems() != null) {
            List<OrderItemModel> items = new LinkedList<>();
            for (OrderItem orderItem : entity.getItems()) {
                items.add(orderItemConverter.convertEntityToModel(orderItem));
            }
            model.setItems(items);
        }

        if (entity.getShipping() != null) {
            ShippingDetailsModel shippingDetailsModel = shippingDetailsConverter.convertEntityToModel(entity.getShipping());
            model.setShipping(shippingDetailsModel);
        }

        if (entity.getShippingMethods() != null) {
            List<ShippingMethodModel> shippingMethods = new LinkedList<>();
            model.setShippingMethods(shippingMethods);
            for (ShippingMethod shippingMethodEntity : entity.getShippingMethods()) {
                ShippingMethodModel shippingMethodModel = shippingMethodConverter.convertEntityToModel(shippingMethodEntity);
                shippingMethods.add(shippingMethodModel);
            }
        }

        return model;
    }
}
