package com.example.onlineshoppingservice.model.view;

public class ProductDto {

    private Long id;
    private String name;
    private double price;
    private Integer quantity;
    private ProviderDto provider;
    private ProductSettingsDto productSettingsDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProviderDto getProvider() {
        return provider;
    }

    public void setProvider(ProviderDto provider) {
        this.provider = provider;
    }

    public ProductSettingsDto getProductSettingsDto() {
        return productSettingsDto;
    }

    public void setProductSettingsDto(ProductSettingsDto productSettingsDto) {
        this.productSettingsDto = productSettingsDto;
    }
}
