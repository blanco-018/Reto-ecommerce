package com.example.Ecommerce.entities;

import java.util.ArrayList;

public class WhisesList {
    private ArrayList<Product> productsWL;

    public WhisesList() {
        this.productsWL = new ArrayList<Product>();
    }

    public ArrayList<Product> get_ProductList() {
        return productsWL;
    }

    public boolean add_Product(Product productADD) {
        if (!contains(productADD)) {
            this.productsWL.add(productADD);
            return true;
        }
        return false;
    }

    public boolean remove_Product(Product productRM) {
        if (contains(productRM)) {
            this.productsWL.remove(productRM);
            return true;
        }
        return false;
    }

    private boolean contains(Product product) {
        for (Product p : productsWL) {
            if (p.getID() == (product.getID())) {
                return true;
            }
        }
        return false;
    }
}
