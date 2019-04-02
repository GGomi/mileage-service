package com.essri.mileage.place;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "special_place")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpecialPlace {
    @Id
    @Column(name = "id")
    private String placeId;

    @Column(name = "value")
    private String value;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @Builder
    public SpecialPlace(String placeId, String value) {
        this.placeId = placeId;
        this.value = value;
    }
}
