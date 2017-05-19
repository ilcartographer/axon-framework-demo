package com.example.rest;

/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 3/28/17
 * Time: 11:35 AM
 */
public class Product {
    private long id;
    private String sku;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
