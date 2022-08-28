package com.example.onlineshoppingservice.model.domain.user;

import com.example.onlineshoppingservice.model.domain.Order;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends AbstractUser {

    @OneToMany(mappedBy = "purchaser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders;

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
