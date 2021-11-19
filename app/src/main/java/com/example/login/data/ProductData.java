package com.example.login.data;

import com.google.firebase.database.PropertyName;

public class ProductData {
    @PropertyName("brand")
    private String brand;
    @PropertyName("category")
    private String category;
    @PropertyName("details")
    private String details;
    @PropertyName("id")
    private String id;
    @PropertyName("image")
    private String image;
    @PropertyName("inStockAmount")
    private int inStockAmount;
    @PropertyName("name")
    private String name;
    @PropertyName("price")
    private double price;

    public ProductData(){}

    public ProductData(String brand, String category, String details, String id, String image, int inStockAmount, String name, double price) {
        this.brand = brand;
        this.category = category;
        this.details = details;
        this.id = id;
        this.image = image;
        this.inStockAmount = inStockAmount;
        this.name = name;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getInStockAmount() {
        return inStockAmount;
    }

    public void setInStockAmount(int inStockAmount) {
        this.inStockAmount = inStockAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
