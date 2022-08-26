package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;

import java.util.List;

public interface RouteService {

    RouteDTO add(RouteCommand routeCommand, String username);

    List<RouteDTO> findAllRoutes(String username);

    RouteDTO update(RouteCommand command, Long id);

    RouteDTO findById(Long id);

    void delete(Long id, String username);

}
