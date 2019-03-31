package com.essri.mileage.event.dto;

import com.essri.mileage.event.model.ActionType;
import com.essri.mileage.event.model.ReviewType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventActionRequest {

    @NotNull
    private ReviewType type;

    @NotNull
    private ActionType action;

    @NotBlank
    private String reviewId;

    @NotBlank
    private String content;

    @NotBlank
    private List<String> attachedPhotoIds;

    @NotBlank
    private String userId;

    @NotBlank
    private String placeId;

    @Builder
    public EventActionRequest(@NotNull ReviewType type, @NotNull ActionType action, @NotBlank String reviewId, @NotBlank String content, @NotBlank List<String> attachedPhotoIds, @NotBlank String userId, @NotBlank String placeId) {
        this.type = type;
        this.action = action;
        this.reviewId = reviewId;
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
        this.userId = userId;
        this.placeId = placeId;
    }
}
