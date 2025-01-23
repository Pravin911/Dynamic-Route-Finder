import React from 'react';
import { Clock, Navigation, Activity } from 'lucide-react';

export const RouteDetails = ({ route }) => {
  if (!route) return null;

  return (
    <div className="bg-white rounded-lg shadow p-6">
      <h3 className="text-lg font-bold mb-4">Route Details</h3>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        <div className="flex items-center space-x-3">
          <Navigation className="h-5 w-5 text-blue-500" />
          <div>
            <p className="text-sm text-gray-600">Distance</p>
            <p className="font-semibold">{route.distance.toFixed(1)} km</p>
          </div>
        </div>
        
        <div className="flex items-center space-x-3">
          <Clock className="h-5 w-5 text-blue-500" />
          <div>
            <p className="text-sm text-gray-600">Estimated Time</p>
            <p className="font-semibold">{route.estimatedTime} min</p>
          </div>
        </div>
        
        <div className="flex items-center space-x-3">
          <Activity className="h-5 w-5 text-blue-500" />
          <div>
            <p className="text-sm text-gray-600">Algorithm</p>
            <p className="font-semibold capitalize">{route.algorithmUsed}</p>
          </div>
        </div>
      </div>
    </div>
  );
};