package com.example.Ecommerce.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.Ecommerce.entities.Order;
import com.example.Ecommerce.entities.OrderProduct;
import com.example.Ecommerce.entities.OrderStatus;
import com.example.Ecommerce.entities.Product;
import com.example.Ecommerce.errors.ElementNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
    private ArrayList<Product> db = new ArrayList<Product>(Arrays.asList(
        new Product("Bañera", 200,"https://static5.depositphotos.com/1016130/496/i/950/depositphotos_4968859-stock-photo-bathtub.jpg"),
        new Product("Camisa", 100,"https://atletismoextremadura.es/wp-content/uploads/2021/04/camisas-columbia.jpg" ),
        new Product("Sudadera", 50,"https://www.versace.com/dw/image/v2/ABAO_PRD/on/demandware.static/-/Sites-ver-master-catalog/default/dw33f7733b/original/90_1004002-1A02874_5B020_10_MedusaMusicHoodie-Sweatshirts-versace-online-store_0_0.jpg?sw=748&sh=1050&sm=fit"),
        new Product("Legos", 25,"https://www.nintenderos.com/wp-content/uploads/2020/06/LEGO-Star-Wars.jpg" ),
        new Product("Peluche", 5,"https://galacticblum.com/4125-thickbox_default/peluche-mi-primer-osito-20-cm-h.jpg")
        ));    
        
    // //FINDS
    // ArrayList<Product> FindProductByName(String nombre)  {
    //     ArrayList<Product> results = new ArrayList<>();
    //     for (Product prod : db) {
    //          if (prod.get_Name().equalsIgnoreCase(nombre)) {
    //             results.add(prod);
    //         } 
    //     }
    //     if(results.isEmpty()){
    //         throw new ElementNotFoundException();
    //     }
    //     return results;
    // }

    @GetMapping("/api/orders")
    public List<Order> getOrders() {
        return orders;
    }

    @PostMapping("/api/orders/createOrder")
    public Order createOrder(){
        Order newOrder = new Order(Date.from(Instant.now()), "guest", OrderStatus.ACEPTADO);
        orders.add(newOrder);
        return newOrder;
    }

    @PostMapping("/api/orderProducts/{id}")
    public void addOrderProductToOrder(@PathVariable int id, @RequestBody List<Product> products) {
        HashMap<Integer, Integer> productCount = getProductCount(products);
        for (int productId : productCount.keySet()) {
            int count = productCount.get(productId);
                orderProducts.add(new OrderProduct(id, productId, count));
            }
    }

    @DeleteMapping("/api/orders/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orders.removeIf(order -> order.getID() == orderId);
        orderProducts.removeIf(orderProduct -> orderProduct.getOrderId() == orderId);
    }

    @GetMapping("/api/orderProducts/{id}")
    public List<OrderProduct> getOrderById(@PathVariable("id") int orderId, HttpServletRequest request, HttpServletResponse response){
        var o = getOrderProductByOrderId(orderId);
        if(o == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return o;
    }

    public List<OrderProduct> getOrderProductByOrderId(@PathVariable int id) {
        List<OrderProduct> result = new ArrayList<OrderProduct>();
        Boolean empty=true;
        for (OrderProduct orderProduct : orderProducts) {
            if (orderProduct.getOrderId() == id) {
                result.add(orderProduct);
                empty=false;
            }
        }
        if (!empty){
            return result;
        }else{
            throw new ElementNotFoundException();
        }
    }

    @PutMapping("/api/orderProducts/{id}")
    public void updateOrderProduct(@PathVariable int id, @RequestBody OrderProduct orderProduct) {
        Boolean empty=true;
        for (OrderProduct op : orderProducts) {
            if (op.getID() == id) {
                op.setQuantity(orderProduct.getQuantity());
                empty=false;
            }
        }
        if(empty){
            throw new ElementNotFoundException();
        }
    }

    @GetMapping("/api/home")
    public List<Product> getAllProducts() {
        return db;
    }


private HashMap<Integer, Integer> getProductCount(List<Product> products) {
    HashMap<Integer, Integer> productCount = new HashMap<Integer, Integer>();
    for (Product product : products) {
        if (productCount.containsKey(product.getID())) {
            productCount.put(product.getID(), productCount.get(product.getID()) + 1);
        } else {
            productCount.put(product.getID(), 1);
        }
    }
    return productCount;
}

@GetMapping("/api/products/{id}")
public Product GetProduct(@PathVariable("id") int productId, HttpServletRequest request, HttpServletResponse response){
    var p = GetProductById(productId);
    if(p == null){
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
    return p;
}

public Product GetProductById(@PathVariable("id") int id) {
    Product result = null;
    for (Product p : db) {
        if (p.getID()==(id)) {
            result = p;
            return result;
        }
    }
    throw new ElementNotFoundException();
}

@DeleteMapping("/api/orderProducts/{id}")
public void removeOrderProduct(@PathVariable int id) {
    orderProducts.removeIf(orderProduct -> orderProduct.getID() == id);

}
}

