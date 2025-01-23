package com.jotsons.dynamic_route_optimizer.Service;

import com.jotsons.dynamic_route_optimizer.Model.Location;
import com.jotsons.dynamic_route_optimizer.Repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> searchLocations(String query) {
        return locationRepository.searchLocations(query);
    }
}