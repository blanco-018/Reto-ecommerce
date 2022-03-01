package com.example.Ecommerce.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No element found")
public class ElementNotFoundException extends RuntimeException{
    
}