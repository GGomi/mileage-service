package com.essri.mileage.event.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.domain.Events;
import com.essri.mileage.place.domain.PlaceHistory;
import com.essri.mileage.place.domain.SpecialPlace;
import com.essri.mileage.place.service.PlaceService;
import com.essri.mileage.point.service.CalculatePointService;
import com.essri.mileage.review.service.ReviewSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class EventAddService implements EventActionService {
    private final EventService eventService;
    private final PlaceService placeService;
    private final ReviewSaveService reviewSaveService;
    private final CalculatePointService calculatePointService;

    @Override
    public Events handleAction(EventActionRequest dto) {
        eventService.checkLegal(dto);

        SpecialPlace place = placeService.isSpecial(dto.getPlaceId());

        long mileage = calculatePointService.contentCalculate(dto)
                + placeService.getPlaceMileage(dto,place);

        PlaceHistory placeHistory = placeService.isPlace(dto.getPlaceId(), dto.getReviewId(), dto.getUserId());

        Events event = eventService.writeEvent(dto, mileage);
        reviewSaveService.save(dto);
        placeService.savePlaceHistory(placeHistory);

        return event;

    }
}
