package com.jotsons.dynamic_route_optimizer.Controller;

import com.jotsons.dynamic_route_optimizer.Model.Location;
import com.jotsons.dynamic_route_optimizer.Repository.LocationRepository;
import com.jotsons.dynamic_route_optimizer.Service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:5173")
public class LocationController {
    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Location>> searchLocations(@RequestParam String q) {
        try {
            List<Location> locations = locationRepository
                .findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(q);
            return ResponseEntity.ok(locations);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}

