package com.example.Ecommerce.entities;

import java.util.List;

public class DoOrder {
    private String customerName;
    private int orderId;
    private List<Product> products;
   
    public DoOrder(String customerName, int orderId, List<Product> products) {
        this.customerName = customerName;
        this.orderId = orderId;
        this.products = products;
    }
    
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
