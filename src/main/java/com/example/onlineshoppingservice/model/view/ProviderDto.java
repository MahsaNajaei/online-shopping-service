package com.example.onlineshoppingservice.model.view;

import java.util.Set;

public class ProviderDto {
    private String name;
    Set<ProductDto> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }
}
