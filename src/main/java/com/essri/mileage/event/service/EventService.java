package com.essri.mileage.event.service;

import com.essri.mileage.event.EventRepository;
import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.model.Event;
import com.essri.mileage.history.History;
import com.essri.mileage.point.Points;
import com.essri.mileage.point.service.CalculatePoint;
import com.essri.mileage.point.service.IncreasePoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final IncreasePoint increasePoint;
    private final CalculatePoint calculatePoint;
    private final EventRepository eventRepository;

    public Event writeEvent(EventActionRequest dto) {

        Event event = eventBuilder(dto, calculatePoint.calculate(dto));
        Points point = savePoint(event);

        History history = History.builder()
                .event(event)
                .point(point)
                .build();

        event.addHistory(history);
        point.addHistory(history);

        return eventRepository.save(event);

    }

    public Points savePoint(Event event) {
        return increasePoint.save(event);
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
}
