package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoType;
import java.util.List;
import java.util.Optional;

public interface RouteServieImpl {

    List<Route> findByOrigin(String origin);

    List<Route> findByDestination(String destination);

    Optional<Route> findRouteByCargoType(CargoType cargoType);
}
