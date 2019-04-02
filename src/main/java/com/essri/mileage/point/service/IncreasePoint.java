package com.essri.mileage.point.service;

import com.essri.mileage.point.PointRepository;
import com.essri.mileage.point.Points;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class IncreasePoint {
    private final PointRepository repository;

    public Points save(String userId, long mileage) {
        Points point = Points.builder()
                .id(userId)
                .point(mileage + getMileage(userId))
                .build();

        return repository.save(point);
    }

    private long getMileage(String userId) {
        return repository.findById(userId).orElse(new Points(userId,0)).getPoint();
    }

}
