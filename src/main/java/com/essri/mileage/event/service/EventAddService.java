package com.essri.mileage.event.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.model.Event;
import com.essri.mileage.review.service.ReviewSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class EventAddService implements EventActionService {
    private final WriteEvent writeEvent;
    private final ReviewSave reviewSave;

    @Override
    public Event handleAction(EventActionRequest dto) {
        Event event = writeEvent.write(dto);
        reviewSave.save(dto);
        return event;
    }
}
