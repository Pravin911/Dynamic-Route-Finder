package com.jotsons.dynamic_route_optimizer.Service;


import com.jotsons.dynamic_route_optimizer.Model.Location;
import com.jotsons.dynamic_route_optimizer.Model.Route;
import com.jotsons.dynamic_route_optimizer.Repository.RouteRepository;
import com.jotsons.dynamic_route_optimizer.Repository.TrafficEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * @Service
 * 
 * @Transactional public class RouteService { private final RouteRepository
 * routeRepository; private final TrafficEventRepository trafficEventRepository;
 * 
 * public RouteService(RouteRepository routeRepository, TrafficEventRepository
 * trafficEventRepository) { this.routeRepository = routeRepository;
 * this.trafficEventRepository = trafficEventRepository; }
 * 
 * public Route calculateRoute(Location start, Location end, String algorithm) {
 * // Create new route Route route = new Route(); route.setStartLocation(start);
 * route.setEndLocation(end); route.setAlgorithmUsed(algorithm);
 * 
 * // Calculate waypoints (simplified version - direct path) List<Location>
 * waypoints = new ArrayList<>(); waypoints.add(start); waypoints.add(end);
 * route.setWaypoints(waypoints);
 * 
 * // Calculate distance using Haversine formula double distance =
 * calculateDistance( start.getLatitude(), start.getLongitude(),
 * end.getLatitude(), end.getLongitude() ); route.setDistance(distance);
 * 
 * // Estimate time (assuming average speed of 50 km/h) int estimatedTime =
 * (int) (distance / 50.0 * 60); route.setEstimatedTime(estimatedTime);
 * 
 * return routeRepository.save(route); }
 * 
 * private double calculateDistance(double lat1, double lon1, double lat2,
 * double lon2) { final int R = 6371; // Earth's radius in kilometers
 * 
 * double latDistance = Math.toRadians(lat2 - lat1); double lonDistance =
 * Math.toRadians(lon2 - lon1);
 * 
 * double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
 * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
 * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
 * 
 * double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
 * 
 * return R * c; }
 * 
 * }
 */

@Service
@Transactional
public class RouteService {
    private final RouteRepository routeRepository;
    private final TrafficEventRepository trafficEventRepository;

    public RouteService(RouteRepository routeRepository, TrafficEventRepository trafficEventRepository) {
        this.routeRepository = routeRepository;
        this.trafficEventRepository = trafficEventRepository;
    }

    public Route calculateRoute(Location start, Location end, String algorithm) {
        // Create new route
        Route route = new Route();
        route.setStartLocation(start);
        route.setEndLocation(end);
        route.setAlgorithmUsed(algorithm);

        // Calculate waypoints (simplified version - direct path)
        List<Location> waypoints = new ArrayList<>();
        waypoints.add(start);
        waypoints.add(end);
        route.setWaypoints(waypoints);

        // Calculate distance using Haversine formula
        double distance = calculateDistance(
            start.getLatitude(), start.getLongitude(),
            end.getLatitude(), end.getLongitude()
        );
        route.setDistance(distance);

        // Estimate time (assuming average speed of 50 km/h)
        int estimatedTime = (int) (distance / 50.0 * 60);
        route.setEstimatedTime(estimatedTime);

        return routeRepository.save(route);
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius in kilometers

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}

