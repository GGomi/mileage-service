package com.essri.mileage.place.service;

import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.domain.ActionType;
import com.essri.mileage.place.*;
import com.essri.mileage.place.domain.PlaceHistory;
import com.essri.mileage.place.domain.PlaceId;
import com.essri.mileage.place.domain.SpecialPlace;
import com.essri.mileage.place.exception.PlaceWriteFailException;
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
            setSpecialValue(dto.getPlaceId(), dto.getReviewId());
        } else {
            return 0;
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

        if(place.isPresent()) {
            throw new PlaceWriteFailException(placeId,userId);
        }

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

    public void setSpecialValue(String placeId, String reviewId) {
        specialPlaceRepository.save(SpecialPlace.builder()
                .placeId(placeId)
                .value(reviewId)
                .build());
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
