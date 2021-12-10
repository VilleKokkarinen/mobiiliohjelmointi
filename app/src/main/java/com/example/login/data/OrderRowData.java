package com.example.login.data;

import com.google.firebase.database.PropertyName;

public class OrderRowData {
    @PropertyName("delivered")
    private Boolean delivered;
    @PropertyName("orderId")
    private String orderId;
    @PropertyName("uid")
    private String uid;
    @PropertyName("item")
    private ProductData item;

    public OrderRowData() {
    }

    public OrderRowData(Boolean delivered, String orderId, String uid, ProductData item) {
        this.delivered = delivered;
        this.orderId = orderId;
        this.uid = uid;
        this.item = item;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ProductData getItem() {
        return item;
    }

    public void setItem(ProductData item) {
        this.item = item;
    }
}
