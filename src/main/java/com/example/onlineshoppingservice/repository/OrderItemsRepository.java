package com.example.onlineshoppingservice.repository;

import com.example.onlineshoppingservice.model.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
    @Query("select orderItem.order.id from OrderItem orderItem where orderItem.product.id = ?1 and orderItem.order.purchaser.id = ?2")
    Optional<Long> getOrderIdByProductIdAndPurchaserId(long productId, long purchaserId);
}
