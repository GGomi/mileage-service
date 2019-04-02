package com.essri.mileage.event.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.model.Event;
import com.essri.mileage.place.SpecialPlace;
import com.essri.mileage.place.service.PlaceService;
import com.essri.mileage.point.service.CalculatePoint;
import com.essri.mileage.review.service.ReviewDelete;
import com.essri.mileage.review.service.ReviewSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventDeleteService implements EventActionService {

    private final EventService eventService;
    private final ReviewDelete reviewDelete;
    private final ReviewSave reviewSave;
    private final PlaceService placeService;
    private final CalculatePoint calculatePoint;

    @Override
    public Event handleAction(EventActionRequest dto) {

        if (reviewSave.hasReview(dto.getReviewId())) {
            SpecialPlace place = placeService.isSpecial(dto.getPlaceId());

            long contentMileage = calculatePoint.contentCalculate(dto);
            long placeMileage = placeService.getPlaceMileage(dto,place);
            placeService.setSpecialPlace(place, dto.getReviewId());

            Event event = eventService.writeEvent(dto, contentMileage + placeMileage);

            reviewDelete.delete(dto.getReviewId());
            placeService.deletePlaceHistory(dto.getPlaceId(),dto.getUserId());
            return event;

        } else {
            throw new IllegalArgumentException(
                    String.format("Unknown reviewId : %s", dto.getReviewId()));
        }
    }
}
