package com.essri.mileage.history;

import com.essri.mileage.event.model.Events;
import com.essri.mileage.point.Points;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "point_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PointHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updateAt;

  @ManyToOne
  @JoinColumn(name = "events_id", nullable = false, updatable = false)
  private Events events;

  @ManyToOne
  @JoinColumn(name = "points_id", nullable = false, updatable = false)
  private Points points;

  @Builder
  public PointHistory(Events event, Points point) {
    this.events = event;
    this.points = point;
  }

}
