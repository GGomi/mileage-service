package com.essri.mileage.event.dto;

import com.essri.mileage.event.model.ActionType;
import com.essri.mileage.event.model.Event;
import com.essri.mileage.event.model.ReviewType;
import lombok.Getter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class EventActionResponse {
    @NotBlank
    private String eventId;

    @NotNull
    private ReviewType type;

    @NotNull
    private ActionType action;

    @NotBlank
    private String content;

    @NotBlank
    private String attachedPhotos;

    @NotBlank
    private String placeId;

    public EventActionResponse(Event event) {
        this.eventId = event.getReviewId();
        this.type = event.getType();
        this.action = event.getAction();
        this.content = event.getContent();
        this.attachedPhotos = event.getPhotos();
        this.placeId = event.getPlaceId();
    }
}
