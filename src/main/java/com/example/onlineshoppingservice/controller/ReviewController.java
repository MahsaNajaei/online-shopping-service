package com.example.onlineshoppingservice.controller;

import com.example.onlineshoppingservice.exception.ReviewNotAllowedException;
import com.example.onlineshoppingservice.exception.ReviewNotFoundException;
import com.example.onlineshoppingservice.model.enums.ConfirmationStatus;
import com.example.onlineshoppingservice.model.enums.ReviewType;
import com.example.onlineshoppingservice.model.view.ReviewDto;
import com.example.onlineshoppingservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/get-all")
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/get-all/{reviewType}")
    public List<ReviewDto> getAllReviewsByType(@PathVariable ReviewType reviewType) throws ReviewNotFoundException {
        return reviewService.getAllReviewsByType(reviewType);
    }

    @GetMapping("/{productId}")
    public List<ReviewDto> getProductReviews(@PathVariable long productId) {
        return reviewService.getSingleProductReviews(productId);
    }

    @GetMapping("/{reviewType}/{productId}")
    public List<ReviewDto> getProductReviewsByType(@PathVariable ReviewType reviewType, @PathVariable long productId) throws ReviewNotFoundException {
        return reviewService.getSingleProductReviewsByType(reviewType, productId);
    }

    @PostMapping
    public void addReview(@Valid @RequestBody ReviewDto reviewDto) throws ReviewNotAllowedException {
        reviewService.addReview(reviewDto);
    }

    @PatchMapping("/update-confirmation-status")
    public void updateConfirmationStatus(@RequestParam ConfirmationStatus confirmationStatus, @RequestParam long reviewId) {
        reviewService.partialUpdateConfirmationStatus(confirmationStatus, reviewId);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId){
        reviewService.deleteReview(reviewId);
    }
}
