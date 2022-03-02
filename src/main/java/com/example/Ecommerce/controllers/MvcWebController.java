package com.example.Ecommerce.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.Ecommerce.entities.Order;
import com.example.Ecommerce.entities.Product;
import com.example.Ecommerce.entities.WhisesList;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MvcWebController {
    private WebController webController;
    private WhisesList whiseslist;
    private Order orderActual;

    public MvcWebController() {
        webController = new WebController();
        whiseslist = new WhisesList();
    }

    @GetMapping("/home")
    public ModelAndView ProductList() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("db", webController.getAllProducts());
        return modelAndView;
    }

    @GetMapping("/myOrder")
    public ModelAndView OrderList() {
        ModelAndView modelAndView = new ModelAndView("orders");
        // if (orderActual == null) {
        //     orderActual = webController.createOrder();
        // }
        modelAndView.addObject("orderProducts", webController.getOrderProductByOrderId(orderActual.getID()));
        return modelAndView;
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public String AddToMyOrder(@Nullable int id) {
        Product p = webController.GetProductById(id);
        if (orderActual == null) {
            orderActual = webController.createOrder();
        }
        webController.addOrderProductToOrder(orderActual.getID(), new ArrayList<Product>(Arrays.asList(p)));
        return "redirect:/myOrder";
    }

    @RequestMapping(value = "/removeOrder", method = RequestMethod.POST)
    public String RemoveFromMyOrder(@Nullable int id) {
        webController.removeOrderProduct(id);
        return "redirect:/myOrder";
    }

    @GetMapping("/whiseslist")
    public ModelAndView MyProductList() {
        ModelAndView modelAndView = new ModelAndView("whiseslist");
        modelAndView.addObject("whiseslist", whiseslist.get_ProductList());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String AddToMyList(@Nullable int id) {
        var p = webController.GetProductById(id);
        whiseslist.add_Product(p);
        return "redirect:/whiseslist";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String RemoveFromMyList(@Nullable int id) {
        var p = webController.GetProductById(id);
        whiseslist.remove_Product(p);
        return "redirect:/home";
    } 
}
