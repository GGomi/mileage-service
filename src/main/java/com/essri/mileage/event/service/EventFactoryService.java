package com.essri.mileage.event.service;

import com.essri.mileage.event.dto.EventActionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventFactoryService {

    private final EventAddService eventAddService;
    private final EventModService eventModService;
    private final EventDeleteService eventDeleteService;

    public EventActionService getInstance(EventActionRequest dto) {

        final EventActionService eventActionService;

        switch (dto.getAction()) {
            case ADD:
                eventActionService = eventAddService;
                break;
            case MOD:
                eventActionService = eventModService;
                break;
            case DELETE:
                eventActionService = eventDeleteService;
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Unknown action type : %s", dto.getAction()));
        }

        return eventActionService;
    }
}
