package com.essri.mileage.place;

import com.essri.mileage.place.domain.SpecialPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialPlaceRepository extends JpaRepository<SpecialPlace, String> {
}
