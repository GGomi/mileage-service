package com.essri.mileage.event.model;


import com.essri.mileage.history.PointHistory;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "action", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ActionType action;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ReviewType type;

    @Column(name = "point", nullable = false)
    private long point;

    @Column(name = "photos", nullable = false)
    private String photos;

    @Column(name = "placeId", nullable = false)
    private String placeId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "events", cascade = CascadeType.ALL)
    private List<PointHistory> histories = new ArrayList<>();

    @Builder
    public Event(String userId, String reviewId, String content, ActionType action, ReviewType type, long point, String photos, String placeId) {
        this.userId = userId;
        this.reviewId = reviewId;
        this.content = content;
        this.action = action;
        this.type = type;
        this.point = point;
        this.photos = photos;
        this.placeId = placeId;
    }

    public void addHistory(PointHistory pointHistory) {
        getHistories().add(pointHistory);
    }
}
