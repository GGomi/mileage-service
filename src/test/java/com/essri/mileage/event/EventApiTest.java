package com.essri.mileage.event;

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest
public class EventApiTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private EventAddService addService;
//
//    @Autowired
//    private EventRepository eventRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    EventActionRequest dto;
//    @Before
//    public void setUp() {
//        eventRepository.deleteAll();
//        dto = addRequest();
//        eventRepository.save(Events.builder()
//                .userId("testUser")
//                .content("test")
//                .reviewId("testReviewId")
//                .action(ActionType.ADD)
//                .placeId("tokyo")
//                .type(ReviewType.REVIEW).build());
//    }
//
//    @Test
//    public void 리뷰작성_성공() throws Exception {
//        dto = addRequest();
//        mockMvc.perform(post("/events")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .accept(MediaType.APPLICATION_JSON_UTF8)
//                        .content(objectMapper.writeValueAsBytes(dto)))
//                .andDo(print())
//                .andExpect(jsonPath("reviewId").value(dto.getReviewId()))
//                .andExpect(jsonPath("placeId").value(dto.getPlaceId()))
//                ;
//    }
//
//    @Test
//    public void 리뷰수정_성공() throws Exception {
//        dto = modRequest();
//
//        mockMvc.perform(post("/events")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .content(objectMapper.writeValueAsBytes(dto)))
//                .andDo(print())
//                .andExpect(jsonPath("point").value(-1));
//
//    }
//
//    private EventActionRequest addRequest() {
//
//        List<String> photoList = new ArrayList<>();
//        photoList.add("photo1");
//        photoList.add("photo2");
//
//        EventActionRequest dto = EventActionRequest.builder()
//                .userId("testUser")
//                .attachedPhotoIds(photoList)
//                .content("test")
//                .reviewId("testReviewId")
//                .action(ActionType.ADD)
//                .placeId("tokyo")
//                .type(ReviewType.REVIEW)
//                .build();
//
//        return dto;
//    }
//
//    private EventActionRequest modRequest() {
//        List<String> photoList = new ArrayList<>();
//
//        EventActionRequest dto = EventActionRequest.builder()
//                .userId("testUser")
//                .attachedPhotoIds(photoList)
//                .content("test")
//                .reviewId("testReviewId")
//                .action(ActionType.MOD)
//                .placeId("tokyo")
//                .type(ReviewType.REVIEW)
//                .build();
//
//        return dto;
//    }

}
