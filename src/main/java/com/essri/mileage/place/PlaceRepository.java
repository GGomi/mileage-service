package com.essri.mileage.place;

import com.essri.mileage.place.model.PlaceHistory;
import com.essri.mileage.place.model.PlaceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<PlaceHistory, PlaceId> {
}
