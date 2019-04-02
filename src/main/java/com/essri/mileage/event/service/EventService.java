package com.essri.mileage.event.service;

import com.essri.mileage.event.EventRepository;
import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.model.Event;
import com.essri.mileage.history.PointHistory;
import com.essri.mileage.place.SpecialPlace;
import com.essri.mileage.place.service.PlaceService;
import com.essri.mileage.point.Points;
import com.essri.mileage.point.service.CalculatePoint;
import com.essri.mileage.point.service.IncreasePoint;
import com.essri.mileage.review.service.ReviewSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final IncreasePoint increasePoint;
    private final EventRepository eventRepository;

    public Event writeEvent(EventActionRequest dto, long mileage) {

        Event event = eventBuilder(dto, mileage);
        Points point = increasePoint.save(dto.getUserId(), mileage);

        PointHistory pointHistory = PointHistory.builder()
                .event(event)
                .point(point)
                .build();

        event.addHistory(pointHistory);
        point.addHistory(pointHistory);

        return eventRepository.save(event);

    }

    private Event eventBuilder(EventActionRequest dto, long mileage) {
        return Event.builder()
                .userId(dto.getUserId())
                .reviewId(dto.getReviewId())
                .action(dto.getAction())
                .photos(dto.getAttachedPhotoIds().toString())
                .content(dto.getContent())
                .placeId(dto.getPlaceId())
                .type(dto.getType())
                .point(mileage)
                .build();
    }

    public void checkLegal(EventActionRequest dto) {
        if(dto.getAttachedPhotoIds().size() == 0 && dto.getContent().length() == 0)
            throw new IllegalArgumentException(
                    String.format("It's empty review content : %s", dto.getReviewId()));
    }
}
