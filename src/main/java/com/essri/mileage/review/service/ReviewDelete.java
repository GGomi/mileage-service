package com.essri.mileage.review.service;

import com.essri.mileage.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewDelete {
    private final ReviewRepository repository;

    public void delete(String reviewId) {
        repository.deleteById(reviewId);
    }
}
