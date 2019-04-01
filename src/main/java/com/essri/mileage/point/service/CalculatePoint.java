package com.essri.mileage.point.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.model.ActionType;
import com.essri.mileage.place.Place;
import com.essri.mileage.place.service.PlaceService;
import com.essri.mileage.review.Review;
import com.essri.mileage.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CalculatePoint {
    private final PlaceService placeService;
    private final ReviewRepository repository;

    public long calculate(EventActionRequest dto) {

        Review review = repository.findById(dto.getReviewId())
                .orElse(Review.builder().content(0).photos(0).build());

        String placeId = dto.getPlaceId();
        ActionType action = dto.getAction();
        Place place = placeService.isPlace(placeId);
        List<String> photos = dto.getAttachedPhotoIds();
        String content = dto.getContent();

        long point = 0;

        if (place != null) {
            if (action.equals(ActionType.DELETE) && place.getValue() != null) {

                if (place.getValue().equals(dto.getReviewId())) {
                    placeService.addFirstReview(placeId, null);
                    point += 1;
                }

                if (hasContent(dto.getContent())) point += 1;
                if (hasPhotos(dto.getAttachedPhotoIds())) point += 1;

                return point * -1;

            } else if (place.getValue() == null) {

                placeService.addFirstReview(placeId, dto.getReviewId());
                point += 1;

            }
        }

        if (review.getContent() == 0 && hasContent(content)) point += 1;
        else {
            if (!hasContent(content)) point -= 1;
        }

        if (review.getPhotos() == 0 && hasPhotos(photos)) point += 1;
        else {
            if (!hasPhotos(photos)) point -= 1;
        }

        return point;
    }

    private boolean hasPhotos(List<String> list) {
        return list.size() > 0;
    }

    private boolean hasContent(String content) {
        return content.length() > 0;
    }
}
