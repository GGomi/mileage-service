package com.essri.mileage.point.dto;

import com.essri.mileage.event.dto.EventActionResponse;
import com.essri.mileage.event.model.Event;
import com.essri.mileage.history.History;
import com.essri.mileage.point.Points;
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

    public List<EventActionResponse> buildHistory(List<History> histories) {

        List<EventActionResponse> returns = new ArrayList<>();

        for (History history : histories) {
            Event event = history.getEvents();
            returns.add(new EventActionResponse(event));
        }

        return returns;
    }
}
