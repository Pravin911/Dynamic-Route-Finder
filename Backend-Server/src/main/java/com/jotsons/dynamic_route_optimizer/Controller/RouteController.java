package com.jotsons.dynamic_route_optimizer.Controller;

import com.jotsons.dynamic_route_optimizer.Model.Location;
import com.jotsons.dynamic_route_optimizer.Model.Route;
import com.jotsons.dynamic_route_optimizer.Repository.LocationRepository;
import com.jotsons.dynamic_route_optimizer.Service.RouteService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "http://localhost:5173") // For Vite dev server
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Route> calculateRoute(@RequestBody RouteRequest request) {
        Route route = routeService.calculateRoute(
            request.getStartLocation(),
            request.getEndLocation(),
            request.getAlgorithm()
        );
        return ResponseEntity.ok(route);
    }

    // Request DTO
    public static class RouteRequest {
        private Location startLocation;
        private Location endLocation;
        private String algorithm;

        // Getters and setters
        public Location getStartLocation() { return startLocation; }
        public void setStartLocation(Location startLocation) { this.startLocation = startLocation; }
        public Location getEndLocation() { return endLocation; }
        public void setEndLocation(Location endLocation) { this.endLocation = endLocation; }
        public String getAlgorithm() { return algorithm; }
        public void setAlgorithm(String algorithm) { this.algorithm = algorithm; }
    }
}



