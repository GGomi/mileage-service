package com.essri.mileage.event;

import com.essri.mileage.event.domain.ActionType;
import com.essri.mileage.event.domain.Events;
import com.essri.mileage.event.domain.ReviewType;
import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.service.EventAddService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EventApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventAddService addService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 리뷰작성_성공() throws Exception {
        EventActionRequest dto = addRequest();

        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(jsonPath("reviewId").value(dto.getReviewId()))
                .andExpect(jsonPath("placeId").value(dto.getPlaceId()))
                ;
    }

    @Test
    public void 리뷰수정_성공() throws Exception {
        Events oldEvent = saveEvents();
        EventActionRequest dto = modRequest();

        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsBytes(dto)))
                .andDo(print());

        Events newEvent = eventRepository.findById(oldEvent.getId()+1).get();
        assertThat(oldEvent.getPoint() + newEvent.getPoint() ).isEqualTo(1);

    }

    private EventActionRequest addRequest() {

        List<String> photoList = new ArrayList<>();
        photoList.add("photo1");
        photoList.add("photo2");

        EventActionRequest dto = EventActionRequest.builder()
                .userId("testUser")
                .attachedPhotoIds(photoList)
                .content("test")
                .reviewId("testReviewId")
                .action(ActionType.ADD)
                .placeId("tokyo")
                .type(ReviewType.REVIEW)
                .build();

        return dto;
    }

    private EventActionRequest modRequest() {
        List<String> photoList = new ArrayList<>();

        EventActionRequest dto = EventActionRequest.builder()
                .userId("testUser")
                .attachedPhotoIds(photoList)
                .content("test")
                .reviewId("testReviewId")
                .action(ActionType.MOD)
                .placeId("tokyo")
                .type(ReviewType.REVIEW)
                .build();

        return dto;
    }

    private Events saveEvents() {
        EventActionRequest dto = addRequest();
        return addService.handleAction(dto);
    }
}
