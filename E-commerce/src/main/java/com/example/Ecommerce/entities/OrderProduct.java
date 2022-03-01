package com.example.Ecommerce.entities;


public class OrderProduct {
    private static int count = 1;
    public int id;
    private int orderId; //long
    private int productId;  //long
    private int quantity;

    public OrderProduct(int orderId,int productId,int quantity){
    this.id=count++;
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
    }

    public int getOrderId(){
        return orderId;
    }
    public void setOrderId(int OrderID) {
        this.orderId = OrderID;
    }

    public int getProductId(){
        return productId;
    }
    public void setProductId(int ProductID) {
        this.productId = ProductID;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity1) {
        this.quantity = quantity1;
    }

    public long getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }
}
