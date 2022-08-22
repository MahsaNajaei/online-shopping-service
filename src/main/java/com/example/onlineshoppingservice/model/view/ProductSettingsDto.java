package com.example.onlineshoppingservice.model.view;

import javax.validation.constraints.NotNull;

public class ProductSettingsDto {

    @NotNull(message = "ProductId can't be null!")
    private Long productId;
    private Boolean isCommentable;
    private Boolean isVotable;
    private Boolean publiclyReviewable;
    private Boolean publiclyRepresentable;

    public ProductSettingsDto() {
    }

    public ProductSettingsDto(Boolean isCommentable, Boolean isVotable, Boolean publiclyReviewable, Boolean publiclyRepresentable) {
        this.isCommentable = isCommentable;
        this.isVotable = isVotable;
        this.publiclyReviewable = publiclyReviewable;
        this.publiclyRepresentable = publiclyRepresentable;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Boolean getCommentable() {
        return isCommentable;
    }

    public void setCommentable(Boolean commentable) {
        isCommentable = commentable;
    }

    public Boolean getVotable() {
        return isVotable;
    }

    public void setVotable(Boolean votable) {
        isVotable = votable;
    }

    public Boolean getPubliclyReviewable() {
        return publiclyReviewable;
    }

    public void setPubliclyReviewable(Boolean publiclyReviewable) {
        this.publiclyReviewable = publiclyReviewable;
    }

    public Boolean getPubliclyRepresentable() {
        return publiclyRepresentable;
    }

    public void setPubliclyRepresentable(Boolean publiclyRepresentable) {
        this.publiclyRepresentable = publiclyRepresentable;
    }
}
