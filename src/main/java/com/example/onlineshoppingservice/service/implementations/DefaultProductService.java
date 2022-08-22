package com.example.onlineshoppingservice.service.implementations;

import com.example.onlineshoppingservice.exception.ProductNotFoundException;
import com.example.onlineshoppingservice.model.domain.Product;
import com.example.onlineshoppingservice.model.view.ProductSettingsDto;
import com.example.onlineshoppingservice.repository.ProductRepository;
import com.example.onlineshoppingservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultProductService implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void updateSettings(ProductSettingsDto productSettingsDto) throws ProductNotFoundException {
        Optional<Product> optionalEntityProduct = productRepository.findById(productSettingsDto.getProductId());
        if (optionalEntityProduct.isEmpty())
            throw new ProductNotFoundException("Product not found with id: " + productSettingsDto.getProductId() + "!");
        Product product = updateEntityProductSettingsByDtoNoneNullValues(productSettingsDto, optionalEntityProduct.get());
        productRepository.save(product);
    }

    private Product updateEntityProductSettingsByDtoNoneNullValues(ProductSettingsDto updateSource, Product updateTarget) {
        Boolean settingsValue;
        if ((settingsValue = updateSource.getPubliclyRepresentable()) != null)
            updateTarget.setPubliclyRepresentable(settingsValue);
        if ((settingsValue = updateSource.getPubliclyReviewable()) != null)
            updateTarget.setPubliclyReviewable(settingsValue);
        if ((settingsValue = updateSource.getCommentable()) != null)
            updateTarget.setCommentable(settingsValue);
        if ((settingsValue = updateSource.getVotable()) != null)
            updateTarget.setVotable(settingsValue);
        return updateTarget;
    }
}