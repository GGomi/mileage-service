package com.essri.mileage.event.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.domain.Events;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface EventActionService {
    Events handleAction(EventActionRequest dto);
}
