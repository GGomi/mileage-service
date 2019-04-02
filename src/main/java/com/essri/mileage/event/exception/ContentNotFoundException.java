package com.essri.mileage.event.exception;

import lombok.Getter;

@Getter
public class ContentNotFoundException extends RuntimeException {
    private String reviewId;

    public ContentNotFoundException(String reviewId) {
        this.reviewId = reviewId;
    }
}
