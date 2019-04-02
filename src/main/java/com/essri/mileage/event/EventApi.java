package com.essri.mileage.event;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.dto.EventActionResponse;
import com.essri.mileage.event.service.EventActionService;
import com.essri.mileage.event.service.EventFactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventApi {

    private final EventFactoryService eventFactoryService;

    @PostMapping
    public EventActionResponse event(@RequestBody EventActionRequest dto) {
        EventActionService eventActionService = eventFactoryService.getInstance(dto);
        return new EventActionResponse(eventActionService.handleAction(dto));
    }
}
