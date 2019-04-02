package com.essri.mileage.event.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.domain.Events;
import com.essri.mileage.place.domain.SpecialPlace;
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

        reviewSaveService.hasReview(dto.getReviewId());

        SpecialPlace place = placeService.isSpecial(dto.getPlaceId());

        long mileage = calculatePointService.contentCalculate(dto)
                + placeService.getPlaceMileage(dto, place);

        placeService.setSpecialPlace(place, dto.getReviewId());

        Events event = eventService.writeEvent(dto, mileage);

        reviewDeleteService.delete(dto.getReviewId());
        placeService.deletePlaceHistory(dto.getPlaceId(), dto.getUserId());
        return event;
    }
}
