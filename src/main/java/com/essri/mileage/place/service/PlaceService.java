package com.essri.mileage.place.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.model.ActionType;
import com.essri.mileage.place.Place;
import com.essri.mileage.place.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public Place addFirstReview(String placeId, String value) {
        return placeRepository.save(Place.builder()
                .id(placeId)
                .value(value)
                .build());
    }

    public Place isPlace(String placeId) {
        return placeRepository.findById(placeId).orElse(null);
    }
}
