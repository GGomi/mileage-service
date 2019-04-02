package com.essri.mileage.review.exception;

import lombok.Getter;

@Getter
public class ReviewNotFoundException extends RuntimeException{
    private String reviewId;

    public ReviewNotFoundException(String reviewId) {
        this.reviewId = reviewId;
    }
}
