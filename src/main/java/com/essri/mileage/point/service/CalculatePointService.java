package com.essri.mileage.point.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.model.ActionType;
import com.essri.mileage.review.Review;
import com.essri.mileage.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CalculatePointService {
    private final ReviewRepository reviewRepository;

    public long contentCalculate(EventActionRequest dto) {
        long mileage;

        Review review = reviewRepository.findById(dto.getReviewId()).orElse(Review.builder()
                                                                                .content(0)
                                                                                .photos(0)
                                                                                .build());

        if(dto.getAction().equals(ActionType.DELETE)) {
            return deleteAction(review);
        }

        if(review.getContent() == 0 && review.getPhotos() == 0) {
            mileage = hasPhotos(dto.getAttachedPhotoIds())
                            + hasContent(dto.getContent());
        } else {
            mileage = getMileage(dto, review);
        }

        return mileage;
    }

    private long getMileage(EventActionRequest dto, Review review) {

        long mileage = 0;

        if(hasContent(dto.getContent()) == 1 && !hasContent(review.getContent())) {
            mileage += 1;
        }
        if(hasContent(dto.getContent()) != 1 && hasContent(review.getContent())) {
            mileage -= 1;
        }
        if(hasPhotos(dto.getAttachedPhotoIds()) == 1 && !hasPhotos(review.getPhotos())) {
            mileage += 1;
        }
        if(hasPhotos(dto.getAttachedPhotoIds()) != 1 && hasPhotos(review.getPhotos())) {
            mileage -= 1;
        }

        return mileage;

    }

    private long deleteAction(Review review) {
        long mileage = 0;

        if(hasContent(review.getContent())) mileage += 1;
        if(hasPhotos(review.getPhotos())) mileage += 1;

        return mileage*-1;
    }

    private int hasPhotos(List<String> list) {
        return list.size() > 0 ? 1 : 0;
    }

    private boolean hasContent(int content) {
        return content > 0;
    }

    private boolean hasPhotos(int photosSize) {
        return photosSize > 0;
    }

    private int hasContent(String content) {
        return content.length() > 0 ? 1 : 0;
    }
}
