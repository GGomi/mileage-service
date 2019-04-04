package com.essri.mileage.event;

import com.essri.mileage.event.domain.ActionType;
import com.essri.mileage.event.domain.ReviewType;
import com.essri.mileage.event.dto.EventActionRequest;
import com.essri.mileage.event.service.EventAddService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EventAddServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventAddService addService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 리뷰작성_성공() throws Exception {
        EventActionRequest dto = buildRequest();

        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(jsonPath("reviewId").value(dto.getReviewId()))
                .andExpect(jsonPath("placeId").value(dto.getPlaceId()))
                ;
    }

    


    private EventActionRequest buildRequest() {

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

    private void saveEvents() {
        EventActionRequest dto = buildRequest();
        addService.handleAction(dto);
    }
}
