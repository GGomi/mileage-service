package com.essri.mileage.point;

import com.essri.mileage.point.dto.GetPointResponse;
import com.essri.mileage.point.service.GetUserPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointApi {
    private final GetUserPointService getUserPointService;

    @GetMapping("/{id}")
    public GetPointResponse get(@PathVariable String id) {
        return new GetPointResponse(getUserPointService.get(id));
    }
}
