package com.example.onlineshoppingservice.model.domain.review;

import javax.persistence.*;


@Entity
@DiscriminatorValue("COMMENT")
public class Comment extends Review {
}
