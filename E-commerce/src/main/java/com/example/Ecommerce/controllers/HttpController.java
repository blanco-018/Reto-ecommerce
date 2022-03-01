package com.example.Ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HttpController {
    @GetMapping("/")
    public String Test(){
        return "home";
    }
}
