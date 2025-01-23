package com.jotsons.dynamic_route_optimizer.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "traffic_events")
public class TrafficEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private Integer severity;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private LocalDateTime estimatedEndTime;

    @Column(nullable = false)
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getEstimatedEndTime() {
        return estimatedEndTime;
    }

    public void setEstimatedEndTime(LocalDateTime estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public TrafficEvent() {
    }

    public TrafficEvent(Long id, Location location, String eventType, Integer severity, LocalDateTime timestamp, LocalDateTime estimatedEndTime, Boolean isActive) {
        this.id = id;
        this.location = location;
        this.eventType = eventType;
        this.severity = severity;
        this.timestamp = timestamp;
        this.estimatedEndTime = estimatedEndTime;
        this.isActive = isActive;
    }
}