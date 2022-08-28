package com.example.onlineshoppingservice.service.implementations;

import com.example.onlineshoppingservice.exception.ProductNotFoundException;
import com.example.onlineshoppingservice.exception.ReviewNotAllowedException;
import com.example.onlineshoppingservice.exception.ReviewNotFoundException;
import com.example.onlineshoppingservice.model.domain.Product;
import com.example.onlineshoppingservice.model.domain.aggregation.SummaryAggregationInterface;
import com.example.onlineshoppingservice.model.domain.review.Comment;
import com.example.onlineshoppingservice.model.domain.review.Review;
import com.example.onlineshoppingservice.model.domain.review.Vote;
import com.example.onlineshoppingservice.model.enums.ConfirmationStatus;
import com.example.onlineshoppingservice.model.enums.ReviewType;
import com.example.onlineshoppingservice.model.view.ProductSettingsDto;
import com.example.onlineshoppingservice.model.view.ReviewDto;
import com.example.onlineshoppingservice.model.view.ReviewSummaryDto;
import com.example.onlineshoppingservice.repository.OrderItemsRepository;
import com.example.onlineshoppingservice.repository.ProductRepository;
import com.example.onlineshoppingservice.repository.ReviewRepository;
import com.example.onlineshoppingservice.service.ReviewService;
import com.example.onlineshoppingservice.utils.DefaultModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultReviewService implements ReviewService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private DefaultModelMapper modelMapper;


    @Override
    public void addReview(ReviewDto reviewDto) throws ReviewNotAllowedException {
        if (!checkIfReviewIsAllowed(reviewDto))
            throw new ReviewNotAllowedException("review is not allowed based on the adjusted product settings!");
        Review review = null;
        if (reviewDto.getReviewType().equals(ReviewType.COMMENT)) review = modelMapper.map(reviewDto, Comment.class);
        else if (reviewDto.getReviewType().equals(ReviewType.VOTE)) review = modelMapper.map(reviewDto, Vote.class);
        if (review != null) {
            review.setConfirmationStatus(ConfirmationStatus.NOT_CONFIRMED);
            reviewRepository.save(review);
        }
    }

    private boolean checkIfReviewIsAllowed(ReviewDto review) {
        ProductSettingsDto settingsDto = productRepository.findSettingsById(review.getProductId());
        if (!settingsDto.getPubliclyRepresentable() || review.getReviewType().equals(ReviewType.COMMENT) && !settingsDto.getCommentable() || review.getReviewType().equals(ReviewType.VOTE) && !settingsDto.getVotable())
            return false;
        if (!settingsDto.getPubliclyReviewable()) {
            Optional<Long> orderId = orderItemsRepository.getOrderIdByProductIdAndPurchaserId(review.getProductId(), review.getReviewerId());
            return orderId.isPresent();
        }
        return true;
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return modelMapper.mapLists(reviews, ReviewDto.class);
    }

    @Override
    public List<ReviewDto> getSingleProductReviews(long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return modelMapper.mapLists(reviews, ReviewDto.class);
    }

    @Override
    public List<ReviewDto> getAllReviewsByType(ReviewType reviewType) throws ReviewNotFoundException {
        List<Review> reviews;
        if (ReviewType.COMMENT.equals(reviewType)) reviews = reviewRepository.retrieveComments();
        else reviews = reviewRepository.retrieveVotes();

        if (reviews == null)
            throw new ReviewNotFoundException("There are No reviews available for type: " + reviewType.name());

        return modelMapper.mapLists(reviews, ReviewDto.class);
    }

    @Override
    public List<ReviewDto> getSingleProductReviewsByType(ReviewType reviewType, long productId) throws ReviewNotFoundException {
        List<Review> reviews;
        if (ReviewType.COMMENT.equals(reviewType))
            reviews = reviewRepository.retrieveCommentsByProductId(productId);
        else reviews = reviewRepository.retrieveVotesByProductId(productId);

        if (reviews == null)
            throw new ReviewNotFoundException("There are No reviews available for type: " + reviewType.name() + " and productId: " + productId);

        return modelMapper.mapLists(reviews, ReviewDto.class);
    }

    @Override
    public ReviewSummaryDto getReviewSummaryByProductId(long productId) throws ReviewNotFoundException {
        List<Comment> comments = getLastNCommentsFromDB(3, productId);
        List<ReviewDto> commentsDto = modelMapper.mapLists(comments, ReviewDto.class);
        SummaryAggregationInterface summaryAggregationInterface = reviewRepository.getCommentCountAndVoteAverage(productId);

        if (comments.size() == 0 && summaryAggregationInterface.getAverageVote() == null &&
                summaryAggregationInterface.getTotalCommentNumber() == null)
            throw new ReviewNotFoundException("There are no reviews available for product with id: " + productId);

        ReviewSummaryDto reviewSummaryDto = modelMapper.map(summaryAggregationInterface, ReviewSummaryDto.class);
        reviewSummaryDto.setProductId(productId);
        reviewSummaryDto.setLatestComments(commentsDto);
        return reviewSummaryDto;
    }

    private List<Comment> getLastNCommentsFromDB(int dataSizeLimit, long productId) {
        Pageable pageable = PageRequest.of(0, dataSizeLimit, Sort.by("id").descending());
        return reviewRepository.findCommentsByProductIdAndConfirmationStatus(pageable, productId, ConfirmationStatus.CONFIRMED);
    }

    @Override
    public List<ReviewSummaryDto> getAllReviewSummaries() {
        List<Comment> comments = reviewRepository.getLastNCommentsOfAllProducts();
        List<Object[]> commentCountResults = reviewRepository.getAllCommentCountsPerProductId();
        List<Object[]> averageVoteResults = reviewRepository.getAverageOfAllConfirmedVotePerProductId();
        return modelMapper.mapReviewSummaryResultsToReviewSummaryDtoList(comments, commentCountResults, averageVoteResults);
    }

    @Override
    public ProductSettingsDto getProductReviewSettings(long productId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) throw new ProductNotFoundException("Product not found with id: " + productId + "!");
        return modelMapper.map(product, ProductSettingsDto.class);
    }

    @Override
    public List<ProductSettingsDto> getAllProductReviewSettings() {
        List<Product> products = productRepository.findAll();
        return modelMapper.mapLists(products, ProductSettingsDto.class);
    }

    @Override
    public void partialUpdateConfirmationStatus(ConfirmationStatus confirmationStatus, long reviewId) {
        reviewRepository.updateConfirmationStatusIfNotConfirmed(confirmationStatus, reviewId);
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

}
