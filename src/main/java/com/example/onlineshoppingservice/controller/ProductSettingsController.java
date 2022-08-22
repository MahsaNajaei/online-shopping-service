package com.example.onlineshoppingservice.controller;

import com.example.onlineshoppingservice.exception.ProductNotFoundException;
import com.example.onlineshoppingservice.model.view.ProductSettingsDto;
import com.example.onlineshoppingservice.service.ProductService;
import com.example.onlineshoppingservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product-settings")
public class ProductSettingsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductSettingsDto> getProductSettings(@PathVariable long productId) throws ProductNotFoundException {
        ProductSettingsDto productSettings = reviewService.getProductReviewSettings(productId);
        return ResponseEntity.ok(productSettings);
    }

    @GetMapping("/get-all")
    public List<ProductSettingsDto> getAllProductSettings() {
        return reviewService.getAllProductReviewSettings();
    }

    @PatchMapping
    public void updateSettings(@Valid @RequestBody ProductSettingsDto productSettings) throws ProductNotFoundException {
        productService.updateSettings(productSettings);
    }


}
