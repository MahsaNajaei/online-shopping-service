package com.example.onlineshoppingservice.repository;

import com.example.onlineshoppingservice.model.domain.Product;
import com.example.onlineshoppingservice.model.view.ProductSettingsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.example.onlineshoppingservice.model.view.ProductSettingsDto(p.isCommentable, p.isVotable, p.publiclyReviewable, p.publiclyRepresentable) from Product p where p.id = ?1")
    ProductSettingsDto findSettingsById(long productId);
}
