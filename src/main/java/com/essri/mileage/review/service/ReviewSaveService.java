package com.essri.mileage.review.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.review.domain.Review;
import com.essri.mileage.review.ReviewRepository;
import com.essri.mileage.review.exception.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewSaveService {
    private final ReviewRepository reviewRepository;

    public Review save(EventActionRequest dto) {
        return reviewRepository.save(Review.builder()
                .id(dto.getReviewId())
                .content(dto.getContent().length())
                .photos(dto.getAttachedPhotoIds().size())
                .build());
    }

    public Review hasReview(String reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(reviewId));
    }
}
