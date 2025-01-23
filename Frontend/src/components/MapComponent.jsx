import React from 'react';
import { MapContainer, TileLayer, Polyline, Marker, useMap } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';

function MapComponent({ route, startLocation, endLocation }) {
  const center = startLocation 
    ? [startLocation.latitude, startLocation.longitude]
    : [0, 0];

  return (
    <MapContainer
      center={center}
      zoom={13}
      className="h-full w-full"
    >
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution='&copy; OpenStreetMap contributors'
      />
      {route && (
        <>
          <Polyline
            positions={route.waypoints.map(point => [point.latitude, point.longitude])}
            color="blue"
          />
          {route.waypoints.map((point, index) => (
            <Marker
              key={index}
              position={[point.latitude, point.longitude]}
            />
          ))}
        </>
      )}
    </MapContainer>
  );
}

export default MapComponent;
