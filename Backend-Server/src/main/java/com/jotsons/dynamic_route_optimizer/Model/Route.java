package com.jotsons.dynamic_route_optimizer.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "start_location_id", nullable = false)
    private Location startLocation;

    @ManyToOne
    @JoinColumn(name = "end_location_id", nullable = false)
    private Location endLocation;

    @ManyToMany
    @JoinTable(
            name = "route_waypoints",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private List<Location> waypoints;

    private Double distance;
    private Integer estimatedTime;
    private String algorithmUsed;

    public Route(Long id, Location startLocation, Location endLocation, List<Location> waypoints, Double distance, Integer estimatedTime, String algorithmUsed) {
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.waypoints = waypoints;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.algorithmUsed = algorithmUsed;
    }

    public Route() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public List<Location> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Location> waypoints) {
        this.waypoints = waypoints;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getAlgorithmUsed() {
        return algorithmUsed;
    }

    public void setAlgorithmUsed(String algorithmUsed) {
        this.algorithmUsed = algorithmUsed;
    }
}

