package com.essri.mileage.place;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "place_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceHistory {

    @EmbeddedId
    private PlaceId id;

    @Column(name = "review_id")
    private String value;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @Builder
    public PlaceHistory(PlaceId id, String value) {
        this.id = id;
        this.value = value;
    }

}
