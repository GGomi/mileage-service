package com.essri.mileage.place;

import com.essri.mileage.place.domain.PlaceHistory;
import com.essri.mileage.place.domain.PlaceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<PlaceHistory, PlaceId> {
}
