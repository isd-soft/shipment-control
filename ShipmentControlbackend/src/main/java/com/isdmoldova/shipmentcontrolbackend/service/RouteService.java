package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoType;

public interface RouteService {

    RouteDTO findByOrigin(String origin);

    RouteDTO findByDestination(String destination);

    RouteDTO findRouteByCargoType(CargoType cargoType);
}
