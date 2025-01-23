package com.jotsons.dynamic_route_optimizer.Repository;

import com.jotsons.dynamic_route_optimizer.Model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
