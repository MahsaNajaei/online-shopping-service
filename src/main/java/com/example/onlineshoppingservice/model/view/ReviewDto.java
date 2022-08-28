package com.example.onlineshoppingservice.model.view;

import com.example.onlineshoppingservice.model.enums.ConfirmationStatus;
import com.example.onlineshoppingservice.model.enums.ReviewType;
import com.example.onlineshoppingservice.validation.constraints.Review;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Review
public class ReviewDto {
    private Long id;
    private Date lastUpdatedDate;
    @NotNull(message = "Review type can't be null! It can either be Comment or Vote")
    private ReviewType reviewType;
    private ConfirmationStatus confirmationStatus;
    @NotNull(message = "Reviewer Id has to be specified in order to know who has entered the review!")
    private Long reviewerId;
    @NotNull(message = "Null value for ProductId is not allowed as each review should be associated with a specific product!")
    private Long productId;
    private String productName;
    @NotBlank(message = "Review content must not be blank!")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public ReviewType getReviewType() {
        return reviewType;
    }

    public void setReviewType(ReviewType reviewType) {
        this.reviewType = reviewType;
    }

    public ConfirmationStatus getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(ConfirmationStatus confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
