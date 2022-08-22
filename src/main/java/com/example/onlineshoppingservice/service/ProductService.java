package com.example.onlineshoppingservice.service;

import com.example.onlineshoppingservice.exception.ProductNotFoundException;
import com.example.onlineshoppingservice.model.view.ProductSettingsDto;

public interface ProductService {
    void updateSettings(ProductSettingsDto productSettings) throws ProductNotFoundException;
}
