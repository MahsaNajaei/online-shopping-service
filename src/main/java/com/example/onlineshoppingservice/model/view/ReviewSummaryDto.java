package com.example.onlineshoppingservice.model.view;

import java.util.List;

public class ReviewSummaryDto {
    private Long productId;
    List<ReviewDto> latestComments;
    private Double averageRate;
    private Integer totalCommentNumber;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<ReviewDto> getLatestComments() {
        return latestComments;
    }

    public void setLatestComments(List<ReviewDto> latestComments) {
        this.latestComments = latestComments;
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
    }

    public Integer getTotalCommentNumber() {
        return totalCommentNumber;
    }

    public void setTotalCommentNumber(Integer totalCommentNumber) {
        this.totalCommentNumber = totalCommentNumber;
    }
}
