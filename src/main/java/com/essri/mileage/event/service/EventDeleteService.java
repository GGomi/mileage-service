package com.essri.mileage.event.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.model.Event;
import com.essri.mileage.review.service.ReviewSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventDeleteService implements EventActionService {
    private final WriteEvent writeEvent;
    private final ReviewSave reviewSave;

    @Override
    public Event handleAction(EventActionRequest dto) {
        if (reviewSave.hasReview(dto.getReviewId())) {
            reviewSave.delete(dto.getReviewId());
            return writeEvent.write(dto);
        } else {
            throw new IllegalArgumentException(
                    String.format("Unknown reviewId : %s", dto.getReviewId()));
        }
    }
}
