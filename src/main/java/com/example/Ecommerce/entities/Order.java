package com.example.Ecommerce.entities;

import java.util.Date;

public class Order {
    private static int count = 1;
    private int id;
    private Date creationDate;
    private String customerName;
    private OrderStatus status;


public Order(Date creationDate,String customerName,OrderStatus status ){
    this.id=count++;
    this.creationDate = creationDate;
    this.customerName = customerName;
    this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
