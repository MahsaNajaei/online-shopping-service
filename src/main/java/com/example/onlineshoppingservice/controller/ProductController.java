package com.example.onlineshoppingservice.controller;

import com.example.onlineshoppingservice.model.view.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {

    @GetMapping
    public ProductDto getAllProducts() {
        return null;
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable long productId) {
        return null;
    }

    @PatchMapping("/{productId}")
    public void updateProduct(@PathVariable long productId) {
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable long productId){

    }
}
