import React, { useEffect, useState } from 'react';
import { MapContainer, TileLayer, Polyline, Marker, Popup, useMap } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import { Icon } from 'leaflet';

// Map Bounds Handler Component
const MapBoundsHandler = ({ route }) => {
  const map = useMap();

  useEffect(() => {
    if (route && route.waypoints && route.waypoints.length > 0) {
      // Create bounds from all waypoints
      const bounds = route.waypoints.map(point => [point.latitude, point.longitude]);
      
      // Fit map to these bounds with padding
      map.fitBounds(bounds, {
        padding: [50, 50],
        maxZoom: 15
      });
    }
  }, [route, map]);

  return null;
};

// Route Line Component
const RouteLine = ({ route }) => {
  if (!route || !route.waypoints || route.waypoints.length < 2) return null;

  const positions = route.waypoints.map(point => [point.latitude, point.longitude]);
  
  return (
    <Polyline
      positions={positions}
      color="#2563eb"
      weight={4}
      opacity={0.8}
      lineJoin="round"
    />
  );
};

const MapView = ({ route, trafficEvents = [] }) => {
  const [defaultCenter] = useState([20.5937, 78.9629]); // Default center (India)
  const [defaultZoom] = useState(5);

  // Custom icons for start and end points
  const startIcon = new Icon({
    iconUrl: '/api/placeholder/32/32',
    iconSize: [32, 32],
    iconAnchor: [16, 32],
    popupAnchor: [0, -32],
  });

  const endIcon = new Icon({
    iconUrl: '/api/placeholder/32/32',
    iconSize: [32, 32],
    iconAnchor: [16, 32],
    popupAnchor: [0, -32],
  });

  return (
    <MapContainer
      center={defaultCenter}
      zoom={defaultZoom}
      className="h-full w-full rounded-lg"
      style={{ minHeight: "400px" }}
    >
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution='&copy; OpenStreetMap contributors'
      />
      
      {/* Dynamic bounds handler */}
      <MapBoundsHandler route={route} />
      
      {/* Route line */}
      <RouteLine route={route} />
      
      {/* Start and End Markers */}
      {route && route.waypoints && route.waypoints.length >= 2 && (
        <>
          {/* Start Marker */}
          <Marker
            position={[route.waypoints[0].latitude, route.waypoints[0].longitude]}
            icon={startIcon}
          >
            <Popup>
              <div className="font-semibold">Start Point</div>
              <div className="text-sm">{route.startLocation.name}</div>
            </Popup>
          </Marker>
          
          {/* End Marker */}
          <Marker
            position={[
              route.waypoints[route.waypoints.length - 1].latitude,
              route.waypoints[route.waypoints.length - 1].longitude
            ]}
            icon={endIcon}
          >
            <Popup>
              <div className="font-semibold">End Point</div>
              <div className="text-sm">{route.endLocation.name}</div>
            </Popup>
          </Marker>
        </>
      )}
      
      {/* Traffic Events */}
      {trafficEvents.map((event) => (
        <Marker
          key={event.id}
          position={[event.location.latitude, event.location.longitude]}
        >
          <Popup>
            <div className="font-semibold">{event.eventType}</div>
            <div className="text-sm">Severity: {event.severity}</div>
            {event.estimatedEndTime && (
              <div className="text-sm">
                Until: {new Date(event.estimatedEndTime).toLocaleString()}
              </div>
            )}
          </Popup>
        </Marker>
      ))}
    </MapContainer>
  );
};

export default MapView;