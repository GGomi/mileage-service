package com.essri.mileage.event.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.model.Event;
import com.essri.mileage.place.SpecialPlace;
import com.essri.mileage.place.service.PlaceService;
import com.essri.mileage.point.service.CalculatePoint;
import com.essri.mileage.review.service.ReviewSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventModService implements EventActionService {

    private final EventService eventService;
    private final ReviewSave reviewSave;
    private final PlaceService placeService;
    private final CalculatePoint calculatePoint;

    @Override
    public Event handleAction(EventActionRequest dto) {
        eventService.checkLegal(dto);

        if (reviewSave.hasReview(dto.getReviewId())) {
            SpecialPlace place = placeService.isSpecial(dto.getPlaceId());

            long mileage = calculatePoint.contentCalculate(dto);
            mileage += placeService.getPlaceMileage(dto,place);

            reviewSave.save(dto);
            return eventService.writeEvent(dto, mileage);

        } else {
            throw new IllegalArgumentException(
                    String.format("Unknown reviewId : %s", dto.getReviewId()));
        }
    }
}
