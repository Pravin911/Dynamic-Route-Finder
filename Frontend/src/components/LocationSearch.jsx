import { useState, useEffect } from 'react';
import { Search } from 'lucide-react';

const LocationSearch = ({ onSelect, label = 'Location', selectedLocation }) => {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (selectedLocation) {
      setQuery(selectedLocation.name || '');
    }
  }, [selectedLocation]);

  useEffect(() => {
    const searchLocations = async () => {
      if (!query || query.length < 2) {
        setResults([]);
        setError(null);
        return;
      }

      setLoading(true);
      setError(null);
      
      try {
        // Log the search request
        console.log('Searching for:', query);
        
        const response = await fetch(`/api/locations/search?q=${encodeURIComponent(query.trim())}`, {
          method: 'GET',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
          },
        });
        
        // Log the raw response
        console.log('Response status:', response.status);
        const data = await response.json();
        console.log('Search results:', data);

        if (!response.ok) {
          throw new Error(`Search failed: ${response.statusText}`);
        }
        
        if (!Array.isArray(data)) {
          console.error('Unexpected response format:', data);
          throw new Error('Invalid response format from server');
        }
        
        setResults(data);
        
        if (data.length === 0) {
          setError('No locations found. Try a different search term.');
        }
      } catch (err) {
        console.error('Search error:', err);
        setError('Failed to fetch locations. Please try again.');
      } finally {
        setLoading(false);
      }
    };

    const timeoutId = setTimeout(searchLocations, 300);
    return () => clearTimeout(timeoutId);
  }, [query]);

  const handleSelect = (location) => {
    if (!location) return;
    
    console.log('Selected location:', location);
    if (onSelect && typeof onSelect === 'function') {
      onSelect(location);
    }
    setQuery(location.name || '');
    setResults([]);
  };

  return (
    <div className="relative">
      <label className="block text-sm font-medium text-gray-700 mb-1">
        {label}
      </label>
      <div className="relative">
        <input
          type="text"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          className="w-full px-4 py-2 border rounded-lg pr-10"
          placeholder={`Search ${label.toLowerCase()}...`}
          autoComplete="off"
        />
        <div className="absolute right-3 top-2.5 h-5 w-5 text-gray-400">
          {loading ? (
            <div className="animate-spin">⌛</div>
          ) : (
            <Search />
          )}
        </div>
      </div>

      {query.length >= 2 && !loading && (
        <div className="absolute w-full bg-white shadow-lg rounded-lg mt-1 z-50">
          {error ? (
            <div className="p-2 text-sm text-red-600">
              {error}
              <div className="text-gray-600 text-xs mt-1">
                Try entering a more specific search term
              </div>
            </div>
          ) : results.length > 0 ? (
            <ul className="max-h-60 overflow-auto">
              {results.map((location) => (
                <li
                  key={location.id}
                  onClick={() => handleSelect(location)}
                  className="px-4 py-2 hover:bg-gray-100 cursor-pointer"
                >
                  <div className="font-medium">{location.name || 'Unnamed Location'}</div>
                  <div className="text-sm text-gray-600">{location.address || 'No address'}</div>
                </li>
              ))}
            </ul>
          ) : null}
        </div>
      )}
    </div>
  );
};

export default LocationSearch;