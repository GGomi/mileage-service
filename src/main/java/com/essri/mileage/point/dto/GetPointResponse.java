package com.essri.mileage.point.dto;

import com.essri.mileage.event.dto.EventActionResponse;
import com.essri.mileage.history.PointHistory;
import com.essri.mileage.point.domain.Points;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetPointResponse {

    private String userId;
    private long point;
    private List<EventActionResponse> histories;

    public GetPointResponse(Points point) {
        this.userId = point.getId();
        this.point = point.getPoint();
        this.histories = buildHistory(point.getHistories());
    }

    private List<EventActionResponse> buildHistory(List<PointHistory> histories) {

        List<EventActionResponse> returns = new ArrayList<>();

        histories.forEach(obj -> returns.add(new EventActionResponse(obj.getEvents())));

        return returns;
    }
}
