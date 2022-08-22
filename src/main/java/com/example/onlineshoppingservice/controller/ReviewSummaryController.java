package com.example.onlineshoppingservice.controller;

import com.example.onlineshoppingservice.exception.ReviewNotFoundException;
import com.example.onlineshoppingservice.model.view.ReviewSummaryDto;
import com.example.onlineshoppingservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews/summary")
public class ReviewSummaryController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{productId}")
    public ReviewSummaryDto getProductReviewSummary(@PathVariable long productId) throws ReviewNotFoundException {
        return reviewService.getReviewSummaryByProductId(productId);
    }

    @GetMapping("/get-all")
    public List<ReviewSummaryDto> getAllProductReviewSummaries() {
        return reviewService.getAllReviewSummaries();
    }
}
