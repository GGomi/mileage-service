package com.essri.mileage.place.exception;

import lombok.Getter;

@Getter
public class PlaceWriteFailException extends RuntimeException{
    private String placeId;
    private String userId;

    public PlaceWriteFailException(String placeId, String userId) {
        this.placeId = placeId;
        this.userId = userId;
    }
}
