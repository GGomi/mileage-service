package com.essri.mileage.event;

import com.essri.mileage.event.domain.ActionType;
import com.essri.mileage.event.domain.Events;
import com.essri.mileage.event.domain.ReviewType;
import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.service.EventAddService;
import com.essri.mileage.exception.ExceptionApi;
import com.essri.mileage.review.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(MockitoJUnitRunner.class)
public class EventApiTest {
    @InjectMocks
    private EventApi controller;

    @Mock
    private EventAddService addService;

    private ReviewRepository reviewRepository;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionApi())
                .build();
    }

    @Test
    public void 리뷰작성() throws Exception {
        final EventActionRequest dto = EventActionRequest.builder()
                .userId("test")
                .content("content")
                .reviewId("review Id")
                .action(ActionType.ADD)
                .placeId("test")
                .type(ReviewType.REVIEW)
                .build();

        final Events event = Events.builder()
                .userId("test")
                .content("content")
                .reviewId("review Id")
                .action(ActionType.ADD)
                .placeId("test")
                .type(ReviewType.REVIEW)
                .build();

        given(addService.handleAction(dto)).willReturn(event);

        ResultActions resultActions = mockMvc.perform(post("/events")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print());
    }
}
