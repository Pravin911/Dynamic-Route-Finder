// src/components/Navbar.jsx
import React from 'react';
import { Link } from 'react-router-dom';
import { Map, Activity } from 'lucide-react';

export const Navbar = () => {
  return (
    <nav className="bg-white shadow-lg">
      <div className="max-w-7xl mx-auto px-4">
        <div className="flex justify-between h-16">
          <div className="flex">
            <Link to="/" className="flex items-center px-2 py-2 text-gray-700 hover:text-gray-900">
              <Map className="h-6 w-6 mr-2" />
              <span className="font-bold">Route Optimizer</span>
            </Link>
            
            <div className="ml-6 flex space-x-4">
              <Link 
                to="/" 
                className="inline-flex items-center px-3 py-2 text-gray-700 hover:text-gray-900"
              >
                Routes
              </Link>
              <Link 
                to="/traffic"  
                className="inline-flex items-center px-3 py-2 text-gray-700 hover:text-gray-900"
              >
                Traffic
              </Link>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
};
