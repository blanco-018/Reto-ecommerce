package com.example.Ecommerce.entities;

public class Product {
    private static int count = 1;
    public int id;
    private String name;
    private int precio;
    private String img;

    public Product(String name,int precio,String img){
        this.id=count++;
        this.name = name;
        this.precio = precio;
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImg(){
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getID() {
        return id;
    }
    
    public void setID(int id) {
        this.id = id;
    }
}

