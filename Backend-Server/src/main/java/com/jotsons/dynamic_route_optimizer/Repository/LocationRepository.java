package com.jotsons.dynamic_route_optimizer.Repository;

import com.jotsons.dynamic_route_optimizer.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("SELECT l FROM Location l WHERE " +
           "LOWER(l.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(l.address) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Location> findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(
        @Param("query") String query
    );
}
