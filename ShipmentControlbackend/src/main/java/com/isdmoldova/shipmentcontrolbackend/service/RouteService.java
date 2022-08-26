package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RouteService {

//    RouteDTO findByOrigin(String origin);
//    RouteDTO findByDestination(String destination);
//    RouteDTO findRouteByCargoType(CargoType cargoType);

    RouteDTO add(RouteCommand routeCommand, String username);

    List<RouteDTO> findAllRoutes(String username);

    RouteDTO update(Long id, RouteCommand routeCommand, String username);

    void delete(Long id, String username);

}
