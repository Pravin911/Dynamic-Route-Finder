package com.jotsons.dynamic_route_optimizer.Controller;

import com.jotsons.dynamic_route_optimizer.Model.TrafficEvent;
import com.jotsons.dynamic_route_optimizer.Repository.TrafficEventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/traffic")
@CrossOrigin(origins = "http://localhost:5173") // For Vite dev server
public class TrafficEventController {
    private final TrafficEventRepository trafficEventRepository;

    public TrafficEventController(TrafficEventRepository trafficEventRepository) {
        this.trafficEventRepository = trafficEventRepository;
    }

    @GetMapping("/events/active")
    public ResponseEntity<List<TrafficEvent>> getActiveEvents() {
        return ResponseEntity.ok(trafficEventRepository.findActiveEvents());
    }
}
