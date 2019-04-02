package com.essri.mileage.event;

import com.essri.mileage.event.domain.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Events, String> {
}
