package com.example.onlineshoppingservice.model.domain.review;

import com.example.onlineshoppingservice.model.domain.Product;
import com.example.onlineshoppingservice.model.domain.user.AbstractUser;
import com.example.onlineshoppingservice.model.enums.ConfirmationStatus;
import com.example.onlineshoppingservice.model.enums.ReviewType;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ReviewType")
@EntityListeners(AuditingEntityListener.class)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private ConfirmationStatus confirmationStatus;
    @LastModifiedDate
    private Date lastUpdatedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private AbstractUser reviewer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Product product;
    private String content;
    @Transient
    private ReviewType reviewType;

    public Review() {
        if (this instanceof Vote)
            reviewType = ReviewType.VOTE;
        else if (this instanceof Comment)
            reviewType = ReviewType.COMMENT;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ConfirmationStatus getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(ConfirmationStatus confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public AbstractUser getReviewer() {
        return reviewer;
    }

    public void setReviewer(AbstractUser reviewer) {
        this.reviewer = reviewer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ReviewType getReviewType() {
        return reviewType;
    }

    public void setReviewType(ReviewType reviewType) {
        this.reviewType = reviewType;
    }
}
