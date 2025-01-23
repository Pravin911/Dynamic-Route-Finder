import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Navbar } from './components/Navbar';
import { RouteOptimizer } from './pages/RouteOptimizer';
import { Traffic } from './pages/Traffic';

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-100">
        <Navbar />
        <Routes>
          <Route path="/" element={<RouteOptimizer />} />
          <Route path="/traffic" element={<Traffic />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;