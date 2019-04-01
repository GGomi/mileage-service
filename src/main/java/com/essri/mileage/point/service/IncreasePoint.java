package com.essri.mileage.point.service;

import com.essri.mileage.event.model.Event;
import com.essri.mileage.point.Points;
import com.essri.mileage.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class IncreasePoint {
    private final PointRepository repository;

    public Points save(Event event) {
        Points point = Points.builder()
                .id(event.getUserId())
                .point(event.getPoint() + getPoint(event.getUserId()))
                .build();

        return repository.save(point);
    }

    public long getPoint(String userId) {
        return repository.findById(userId).orElse(new Points(userId,0)).getPoint();
    }
}
