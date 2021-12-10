package com.example.login.data;

import com.google.firebase.database.PropertyName;

public class OrderData {
    @PropertyName("address")
    private String address;
    @PropertyName("address2")
    private String address2;
    @PropertyName("city")
    private String city;
    @PropertyName("orderId")
    private String orderId;
    @PropertyName("delivery")
    private String delivery;
    @PropertyName("firstName")
    private String firstName;
    @PropertyName("lastName")
    private String lastName;
    @PropertyName("orderDate")
    private Long orderDate;
    @PropertyName("payment")
    private String payment;
    @PropertyName("phone")
    private String phone;
    @PropertyName("state")
    private String state;
    @PropertyName("uid")
    private String uid;
    @PropertyName("zip")
    private String zip;

    public OrderData(){}

    public OrderData(String address, String address2, String city, String orderId, String delivery, String firstName, String lastName, Long orderDate, String payment, String phone, String state, String uid, String zip) {
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.orderId = orderId;
        this.delivery = delivery;
        this.firstName = firstName;
        this.lastName = lastName;
        this.orderDate = orderDate;
        this.payment = payment;
        this.phone = phone;
        this.state = state;
        this.uid = uid;
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Long orderDate) {
        this.orderDate = orderDate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
