package com.essri.mileage.place;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<PlaceHistory, PlaceId> {
}
