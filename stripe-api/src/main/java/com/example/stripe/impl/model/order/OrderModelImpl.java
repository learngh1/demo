package com.example.stripe.impl.model.order;

import com.example.stripe.api.model.order.OrderItemModel;
import com.example.stripe.api.model.order.OrderModel;
import com.example.stripe.api.model.order.OrderStatus;
import com.example.stripe.api.model.shipping.ShippingDetailsModel;
import com.example.stripe.api.model.shipping.ShippingMethodModel;
import com.example.stripe.impl.model.BaseModelImpl;
import com.example.stripe.impl.util.ValidationUtil;

import java.util.List;
import java.util.Map;

public class OrderModelImpl extends BaseModelImpl implements OrderModel {
    private String currency;
    private String email;
    private List<OrderItemModel> items;
    private Integer amount;
    private Integer applicationFee;
    private String chargeId;
    private Long created;
    private Map<String, String> metadata;
    private String selectedShippingMethodId;
    private ShippingDetailsModel shipping;
    private List<ShippingMethodModel> shippingMethods;
    private OrderStatus status;

    public OrderModelImpl() {
    }

    public OrderModelImpl(String currency, List<OrderItemModel> items, ShippingDetailsModel shipping) {
        this.currency = currency;
        this.items = items;
        this.shipping = shipping;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public List<OrderItemModel> getItems() {
        return items;
    }

    @Override
    public void setItems(List<OrderItemModel> items) {
        this.items = items;
    }

    @Override
    public Integer getAmount() {
        return amount;
    }

    @Override
    public void setAmount(Integer amount) {
        ValidationUtil.validatePositive(amount);
        this.amount = amount;
    }

    public Integer getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(Integer applicationFee) {
        this.applicationFee = applicationFee;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public String getSelectedShippingMethodId() {
        return selectedShippingMethodId;
    }

    public void setSelectedShippingMethodId(String selectedShippingMethodId) {
        this.selectedShippingMethodId = selectedShippingMethodId;
    }

    @Override
    public ShippingDetailsModel getShipping() {
        return shipping;
    }

    public void setShipping(ShippingDetailsModel shipping) {
        this.shipping = shipping;
    }

    public List<ShippingMethodModel> getShippingMethods() {
        return shippingMethods;
    }

    public void setShippingMethods(List<ShippingMethodModel> shippingMethods) {
        this.shippingMethods = shippingMethods;
    }

    @Override
    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
