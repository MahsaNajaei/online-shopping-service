package com.example.onlineshoppingservice.service;

import com.example.onlineshoppingservice.exception.ProductNotFoundException;
import com.example.onlineshoppingservice.exception.ReviewNotAllowedException;
import com.example.onlineshoppingservice.exception.ReviewNotFoundException;
import com.example.onlineshoppingservice.model.enums.ConfirmationStatus;
import com.example.onlineshoppingservice.model.enums.ReviewType;
import com.example.onlineshoppingservice.model.view.ProductSettingsDto;
import com.example.onlineshoppingservice.model.view.ReviewDto;
import com.example.onlineshoppingservice.model.view.ReviewSummaryDto;

import java.util.List;

public interface ReviewService {

    void addReview(ReviewDto reviewDto) throws ReviewNotAllowedException;

    List<ReviewDto> getAllReviews();

    List<ReviewDto> getAllReviewsByType(ReviewType reviewType) throws ReviewNotFoundException;

    List<ReviewDto> getSingleProductReviews(long productId);

    List<ReviewDto> getSingleProductReviewsByType(ReviewType reviewType, long productId) throws ReviewNotFoundException;

    ReviewSummaryDto getReviewSummaryByProductId(long productId) throws ReviewNotFoundException;

    List<ReviewSummaryDto> getAllReviewSummaries();

    ProductSettingsDto getProductReviewSettings(long productId) throws ProductNotFoundException;

    List<ProductSettingsDto> getAllProductReviewSettings();

    void partialUpdateConfirmationStatus(ConfirmationStatus confirmationStatus, long reviewId);

    void deleteReview(Long reviewId);
}
