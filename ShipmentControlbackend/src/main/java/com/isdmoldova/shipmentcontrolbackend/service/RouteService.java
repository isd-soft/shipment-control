package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    List<Route> findByOrigin(String origin);

    List<Route> findByDestination(String destination);

    Optional<Route> findRouteByCargoType(CargoType cargoType);
}
