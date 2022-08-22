package com.example.onlineshoppingservice.repository;

import com.example.onlineshoppingservice.model.domain.aggregation.AggregationSummaryInterface;
import com.example.onlineshoppingservice.model.domain.review.Comment;
import com.example.onlineshoppingservice.model.domain.review.Review;
import com.example.onlineshoppingservice.model.enums.ConfirmationStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProductId(long productId);

    @Query("from Comment")
    List<Review> retrieveComments();

    @Query("from Comment where product.id = ?1")
    List<Review> retrieveCommentsByProductId(long productId);

    @Query("from Vote")
    List<Review> retrieveVotes();

    @Query("from Vote where product.id = ?1")
    List<Review> retrieveVotesByProductId(long productId);

    @Modifying
    @Query(value = "update Review review set review.confirmationStatus = ?1 where review.id = ?2 and review.confirmationStatus = 'NOT_CONFIRMED'")
    void updateConfirmationStatusIfNotConfirmed(ConfirmationStatus confirmationStatus, long id);

    @Query("from Comment where product.id = ?1 and confirmationStatus = ?2")
    List<Comment> findCommentsByProductIdAndConfirmationStatus(Pageable pageable, long productId, ConfirmationStatus confirmed);

    @Query(value = "with newSource As (select * from review where product_id = ?1 and confirmation_status = 'CONFIRMED') " +
            "select (select count(*) from newSource where review_type='COMMENT') as totalCommentNumber," +
            "(select avg(content) from newSource where review_type='VOTE') as averageVote;", nativeQuery = true)
    AggregationSummaryInterface getCommentCountAndVoteAverage(long productId);

    @Query(value = " with newSource as (select *, ROW_NUMBER() OVER (partition by product_id order by id desc) as rn from review where review_type='COMMENT' and confirmation_status='CONFIRMED') select * from newSource where rn <= 3;", nativeQuery = true)
    List<Comment> getAllSummaries();

    @Query("select product.id, count(Comment) FROM Comment where confirmationStatus = 'CONFIRMED' group by product.id")
    List<Object[]> getAllCommentCountsPerProductId();

    @Query("select product.id as productId, avg(content) as averageVote from Vote where confirmationStatus = 'CONFIRMED' group by product.id")
    List<Object[]> getAverageOfAllConfirmedVotePerProductId();

}
