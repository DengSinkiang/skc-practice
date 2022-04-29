package com.dxj.skc;

/**
 * @author sinkiang
 * @date 2022/4/19 16:14
 */
public class Product {

    private String sku;
    private String name;
    private double price;

    public Product() {
    }

    public Product(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
