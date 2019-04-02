package com.essri.mileage.point.service;

import com.essri.mileage.point.Points;
import com.essri.mileage.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GetUserPointService {
    private final PointRepository repository;

    public Points get(String userId) {
        return repository.findById(userId).orElseThrow(() -> new IllegalArgumentException(
                String.format("Unknown userId : %s", userId)
        ));
    }
}
