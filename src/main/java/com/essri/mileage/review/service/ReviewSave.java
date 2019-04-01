package com.essri.mileage.review.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.review.Review;
import com.essri.mileage.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewSave {
    private final ReviewRepository reviewRepository;

    public Review save(EventActionRequest dto) {
        return reviewRepository.save(Review.builder()
                .id(dto.getReviewId())
                .content(dto.getContent().length())
                .photos(dto.getAttachedPhotoIds().size())
                .build());
    }

    public boolean hasReview(String reviewId) {
        if (reviewRepository.findById(reviewId).orElse(null) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void delete(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
