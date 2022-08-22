package com.example.onlineshoppingservice.model.domain.review;

import javax.persistence.*;

@Entity
@DiscriminatorValue("VOTE")
public class Vote extends Review {
}

