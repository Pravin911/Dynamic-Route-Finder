package com.jotsons.dynamic_route_optimizer.Repository;

import com.jotsons.dynamic_route_optimizer.Model.TrafficEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrafficEventRepository extends JpaRepository<TrafficEvent, Long> {
    @Query("SELECT te FROM TrafficEvent te WHERE te.isActive = true")
    List<TrafficEvent> findActiveEvents();
}
