package com.example.onlineshoppingservice.utils;

import com.example.onlineshoppingservice.model.domain.review.Comment;
import com.example.onlineshoppingservice.model.view.ReviewDto;
import com.example.onlineshoppingservice.model.view.ReviewSummaryDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DefaultModelMapper extends ModelMapper {

    public <S, T> List<T> mapLists(List<S> sourceList, Class<T> targetClass) {
        List<T> targetList = sourceList.stream().map(element -> map(element, targetClass)).collect(Collectors.toList());
        return targetList;
    }

    public List<ReviewSummaryDto> mapReviewSummaryResultsToReviewSummaryDtoList(List<Comment> comments, List<Object[]> commentCountResults, List<Object[]> averageVoteResults) {

        Map<Long, Integer> productIdToCommentCount = commentCountResults.stream().collect(Collectors.toMap((arr -> Long.valueOf(arr[0].toString())), (arr -> Integer.valueOf(arr[1].toString()))));
        Map<Long, Double> productIdToVoteAverage = averageVoteResults.stream().collect(Collectors.toMap((arr -> Long.valueOf(arr[0].toString())), (arr -> Double.valueOf(arr[1].toString()))));

        HashMap<Long, ReviewSummaryDto> productIdToReviewSummaryMap = new HashMap<>();
        List<ReviewSummaryDto> reviewSummaries = new ArrayList<>();

        for (Comment comment : comments) {
            ReviewDto reviewDto = map(comment, ReviewDto.class);
            long productId = reviewDto.getProductId();
            if (!productIdToReviewSummaryMap.containsKey(productId)) {
                ReviewSummaryDto reviewSummary = new ReviewSummaryDto();
                reviewSummary.setProductId(productId);
                reviewSummary.setAverageRate(productIdToVoteAverage.get(productId));
                reviewSummary.setTotalCommentNumber(productIdToCommentCount.get(productId));
                reviewSummary.setLatestComments(new ArrayList<>());
                productIdToReviewSummaryMap.put(productId, reviewSummary);
                reviewSummaries.add(reviewSummary);
                productIdToVoteAverage.remove(productId);
            }
            productIdToReviewSummaryMap.get(productId).getLatestComments().add(reviewDto);
        }

        for (Long productId : productIdToVoteAverage.keySet()) {
            ReviewSummaryDto reviewSummary = new ReviewSummaryDto();
            reviewSummary.setProductId(productId);
            reviewSummary.setAverageRate(productIdToVoteAverage.get(productId));
            reviewSummaries.add(reviewSummary);
        }

        return reviewSummaries;
    }
}
