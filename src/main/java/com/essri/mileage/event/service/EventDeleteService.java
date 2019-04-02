package com.essri.mileage.event.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.model.Events;
import com.essri.mileage.place.model.SpecialPlace;
import com.essri.mileage.place.service.PlaceService;
import com.essri.mileage.point.service.CalculatePointService;
import com.essri.mileage.review.service.ReviewDeleteService;
import com.essri.mileage.review.service.ReviewSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventDeleteService implements EventActionService {

    private final EventService eventService;
    private final ReviewDeleteService reviewDeleteService;
    private final ReviewSaveService reviewSaveService;
    private final PlaceService placeService;
    private final CalculatePointService calculatePointService;

    @Override
    public Events handleAction(EventActionRequest dto) {

        if (reviewSaveService.hasReview(dto.getReviewId())) {
            SpecialPlace place = placeService.isSpecial(dto.getPlaceId());

            long contentMileage = calculatePointService.contentCalculate(dto);
            long placeMileage = placeService.getPlaceMileage(dto,place);
            placeService.setSpecialPlace(place, dto.getReviewId());

            Events event = eventService.writeEvent(dto, contentMileage + placeMileage);

            reviewDeleteService.delete(dto.getReviewId());
            placeService.deletePlaceHistory(dto.getPlaceId(),dto.getUserId());
            return event;

        } else {
            throw new IllegalArgumentException(
                    String.format("Unknown reviewId : %s", dto.getReviewId()));
        }
    }
}
