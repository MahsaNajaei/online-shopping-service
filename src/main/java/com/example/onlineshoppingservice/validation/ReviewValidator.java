package com.example.onlineshoppingservice.validation;

import com.example.onlineshoppingservice.model.enums.ReviewType;
import com.example.onlineshoppingservice.model.view.ReviewDto;
import com.example.onlineshoppingservice.validation.constraints.Review;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ReviewValidator implements ConstraintValidator<Review, ReviewDto> {

    @Override
    public void initialize(Review constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ReviewDto reviewDto, ConstraintValidatorContext constraintValidatorContext) {
        if (reviewDto.getContent() != null) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            if (reviewDto.getReviewType().equals(ReviewType.VOTE)) {
                constraintValidatorContext.buildConstraintViolationWithTemplate("Invalid vote! Vote value should be a float number from 0 to 10!").addPropertyNode("content").addConstraintViolation();
                return validateVote(reviewDto);
            }
            if (reviewDto.getReviewType().equals(ReviewType.COMMENT)) {
                constraintValidatorContext.buildConstraintViolationWithTemplate("Invalid comment! Comment value must be a string with maximum length of 1000!").addPropertyNode("content").addConstraintViolation();
                return validateComment(reviewDto);
            }
            constraintValidatorContext.buildConstraintViolationWithTemplate("Invalid reviewType! ReviewType can be either Comment or Vote").addPropertyNode("reviewType").addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean validateVote(ReviewDto reviewDto) {
        try {
            float vote = Float.parseFloat(reviewDto.getContent());
            if (vote >= 0 && vote <= 10)
                return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean validateComment(ReviewDto reviewDto) {
        return reviewDto.getContent().length() > 2 && reviewDto.getContent().length() <= 1000;
    }
}
