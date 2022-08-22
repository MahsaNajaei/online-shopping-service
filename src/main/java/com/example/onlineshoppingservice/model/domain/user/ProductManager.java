package com.example.onlineshoppingservice.model.domain.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MANAGER")
public class ProductManager extends AbstractUser {
}
