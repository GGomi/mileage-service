package com.essri.mileage.event.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.model.Event;
import com.essri.mileage.place.PlaceHistory;
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
public class EventAddService implements EventActionService {
    private final EventService eventService;
    private final PlaceService placeService;
    private final ReviewSave reviewSave;
    private final CalculatePoint calculatePoint;

    @Override
    public Event handleAction(EventActionRequest dto) {
        eventService.checkLegal(dto);

        SpecialPlace place = placeService.isSpecial(dto.getPlaceId());
        long mileage = calculatePoint.contentCalculate(dto);
        mileage += placeService.getPlaceMileage(dto,place);
        PlaceHistory placeHistory = placeService.isPlace(dto.getPlaceId(), dto.getReviewId(), dto.getUserId());

        Event event = eventService.writeEvent(dto, mileage);
        reviewSave.save(dto);
        placeService.savePlaceHistory(placeHistory);

        return event;

    }
}
