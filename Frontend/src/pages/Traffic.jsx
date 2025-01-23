// src/pages/Traffic.jsx
import React, { useState, useEffect } from 'react';
import { Alert } from '@/components/ui/alert';
import MapView from '../components/MapView';

export const Traffic = () => {
  const [trafficEvents, setTrafficEvents] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchTrafficEvents = async () => {
      setLoading(true);
      try {
        const response = await fetch('/api/traffic/events');
        if (!response.ok) throw new Error('Failed to fetch traffic events');
        const data = await response.json();
        setTrafficEvents(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchTrafficEvents();
  }, []);

  return (
    <div className="container mx-auto px-4 py-8">
      <h2 className="text-xl font-bold mb-4">Traffic Information</h2>
      
      {loading && <p>Loading traffic data...</p>}
      {error && <Alert variant="destructive"><p>{error}</p></Alert>}

      <div className="bg-white rounded-lg shadow p-6" style={{ height: '500px' }}>
        <MapView trafficEvents={trafficEvents} />
      </div>

      <div className="mt-6">
        <h3 className="text-lg font-bold mb-4">Traffic Events</h3>
        <ul>
          {trafficEvents.map((event) => (
            <li key={event.id} className="mb-2">
              <div className="font-medium">{event.eventType}</div>
              <div className="text-sm">Severity: {event.severity}</div>
              <div className="text-sm">Location: {event.location.name}</div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};
