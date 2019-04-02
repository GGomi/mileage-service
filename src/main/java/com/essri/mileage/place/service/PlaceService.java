package com.essri.mileage.place.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.model.ActionType;
import com.essri.mileage.place.*;
import com.essri.mileage.place.model.PlaceHistory;
import com.essri.mileage.place.model.PlaceId;
import com.essri.mileage.place.model.SpecialPlace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final SpecialPlaceRepository specialPlaceRepository;

    public void savePlaceHistory(PlaceHistory placeHistory) {
        placeRepository.save(placeHistory);
    }

    public long getPlaceMileage(EventActionRequest dto, SpecialPlace place) {
        if(isAvailableFirst(dto, place)) {
            setSpecialValue(place, dto.getReviewId());
        }

        return dto.getAction().equals(ActionType.DELETE) ? -1 : 1;
    }

    public void setSpecialPlace(SpecialPlace place, String reviewId) {
        if(reviewId.equals(place.getValue())) {
            specialPlaceRepository.save(SpecialPlace.builder()
                    .placeId(place.getPlaceId())
                    .value(null)
                    .build());
        }
    }
    public PlaceHistory isPlace(String placeId, String reviewId, String userId) {
        PlaceId placeIds = PlaceId.builder()
                .placeId(placeId)
                .userId(userId)
                .build();

        Optional<PlaceHistory> place = placeRepository.findById(placeIds);

        if(place.isPresent())
            throw new IllegalArgumentException(
                    String.format("Already Write this place : %s", placeId)
            );

        return PlaceHistory.builder()
                .id(placeIds)
                .value(reviewId)
                .build();
    }

    public void deletePlaceHistory(String placeId, String userId) {
        PlaceId placeIds = PlaceId.builder()
                .placeId(placeId)
                .userId(userId)
                .build();

        placeRepository.deleteById(placeIds);
    }

    public SpecialPlace isSpecial(String placeId) {
        return specialPlaceRepository.findById(placeId).orElse(null);
    }

    public void setSpecialValue(SpecialPlace place, String reviewId) {
        if(place != null) {
            if(place.getValue() == null) {
                specialPlaceRepository.save(SpecialPlace.builder()
                        .placeId(place.getPlaceId())
                        .value(reviewId)
                        .build());
            }
        }
    }

    public boolean isAvailableFirst(EventActionRequest dto, SpecialPlace place) {

        if(place != null) {
            if(dto.getAction().equals(ActionType.DELETE)) {
                return dto.getReviewId().equals(place.getValue());
            } else {
                return place.getValue() == null;
            }
        } else {
            return false;
        }
    }
}
