import React, { useState } from 'react';
import { RouteDetails } from '../components/RouteDetails';
import { Alert } from '../components/ui/alert';  // Updated import path
import LocationSearch from '../components/LocationSearch';
import MapView from '../components/MapView';

export const RouteOptimizer = () => {
  const [startLocation, setStartLocation] = useState(null);
  const [endLocation, setEndLocation] = useState(null);
  const [algorithm, setAlgorithm] = useState('dijkstra');
  const [route, setRoute] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const calculateRoute = async () => {
    if (!startLocation || !endLocation) {
      setError('Please select both start and end locations');
      return;
    }

    setLoading(true);
    try {
      const response = await fetch('/api/routes/calculate', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          startLocation,
          endLocation,
          algorithm
        }),
      });

      if (!response.ok) throw new Error('Failed to calculate route');
      
      const data = await response.json();
      setRoute(data);
      setError(null);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <div className="lg:col-span-1">
          <div className="bg-white rounded-lg shadow p-6 space-y-6">
            <h2 className="text-xl font-bold">Route Settings</h2>
            
            <LocationSearch
              label="Start Location"
              onSelect={setStartLocation}
            />
            
            <LocationSearch
              label="End Location"
              onSelect={setEndLocation}
            />
            
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Algorithm
              </label>
              <select
                value={algorithm}
                onChange={(e) => setAlgorithm(e.target.value)}
                className="w-full px-3 py-2 border rounded-lg"
              >
                <option value="dijkstra">Dijkstra</option>
                <option value="astar">A* Algorithm</option>
              </select>
            </div>
            
            <button
              onClick={calculateRoute}
              disabled={loading}
              className="w-full bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 disabled:bg-gray-400"
            >
              {loading ? 'Calculating...' : 'Calculate Route'}
            </button>
          </div>
        </div>
        
        <div className="lg:col-span-2 space-y-6">
          <div className="bg-white rounded-lg shadow p-6" style={{ height: '500px' }}>
            <MapView route={route} />
          </div>
          
          {error && (
            <Alert variant="destructive">
              <p>{error}</p>
            </Alert>
          )}
          
          {route && <RouteDetails route={route} />}
        </div>
      </div>
    </div>
  );
};