package com.essri.mileage.event.dto;

import com.essri.mileage.event.model.ActionType;
import com.essri.mileage.event.model.Events;
import com.essri.mileage.event.model.ReviewType;
import lombok.Getter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class EventActionResponse {
    @NotBlank
    private Long id;

    @NotBlank
    private String reviewId;

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

    @NotBlank
    private long point;

    public EventActionResponse(Events event) {
        this.id = event.getId();
        this.reviewId = event.getReviewId();
        this.type = event.getType();
        this.point = event.getPoint();
        this.action = event.getAction();
        this.content = event.getContent();
        this.attachedPhotos = event.getPhotos();
        this.placeId = event.getPlaceId();
    }
}
