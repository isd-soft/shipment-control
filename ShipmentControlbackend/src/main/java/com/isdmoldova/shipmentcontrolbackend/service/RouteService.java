package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface RouteService {

    RouteDTO add(@Valid RouteCommand routeCommand, String username);

    List<RouteDTO> findAllRoutes(String username);

    RouteDTO update(RouteCommand routeCommand, Long id);

    RouteDTO findById(Long id);

    void delete(Long id, String username);

}
