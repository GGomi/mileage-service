package com.essri.mileage.point;

import com.essri.mileage.point.domain.Points;
import com.essri.mileage.point.service.GetUserPointService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class GetPointTest {
    @Autowired
    private GetUserPointService getUserPointService;

    @Autowired
    private PointRepository pointRepository;



    @Test
    public void 유저포인트가져오기() {
        Points point = Points.builder()
                .id("user")
                .point(1)
                .build();


        pointRepository.save(point);
        // given
//        given(pointRepository.save(point)).willReturn(point);

        // when
        long getPoint = getUserPointService.get("user").getPoint();

        assertEquals(getPoint,1);
    }
}
