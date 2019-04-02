package com.essri.mileage.point;

import com.essri.mileage.point.domain.Points;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Points, String> {
}
