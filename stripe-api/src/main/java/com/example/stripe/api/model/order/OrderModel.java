package com.example.stripe.api.model.order;

import com.example.stripe.api.model.BaseModel;
import com.example.stripe.api.model.shipping.ShippingDetailsModel;
import com.example.stripe.api.model.shipping.ShippingMethodModel;

import java.util.List;
import java.util.Map;

public interface OrderModel extends BaseModel {
    String getCurrency();
    void setCurrency(String currency);

    String getEmail();
    void setEmail(String email);

    List<OrderItemModel> getItems();
    void setItems(List<OrderItemModel> items);

    Integer getAmount();
    void setAmount(Integer amount);

    Integer getApplicationFee();
    void setApplicationFee(Integer applicationFee);

    String getChargeId();
    void setChargeId(String chargeId);

    Long getCreated();
    void setCreated(Long created);

    Map<String, String> getMetadata();
    void setMetadata(Map<String, String> metadata);

    String getSelectedShippingMethodId();
    void setSelectedShippingMethodId(String selectedShippingMethodId);

    ShippingDetailsModel getShipping();
    void setShipping(ShippingDetailsModel shipping);

    List<ShippingMethodModel> getShippingMethods();
    void setShippingMethods(List<ShippingMethodModel> shippingMethods);

    OrderStatus getStatus();
    void setStatus(OrderStatus status);
}
