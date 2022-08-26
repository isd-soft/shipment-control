package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.AvailableDaysRentDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.TransportTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportType;
import com.isdmoldova.shipmentcontrolbackend.mapper.AvailableDaysRentDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.TransportCommand;
import com.isdmoldova.shipmentcontrolbackend.service.RouteService;
import com.isdmoldova.shipmentcontrolbackend.service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;
    private final AvailableDaysRentDtoMapper availableDaysRentDtoMapper;

    @PostMapping
    public ResponseEntity<?> addRoute(@RequestBody RouteCommand routeCommand,
                                      Principal principal) {
        routeService.add(routeCommand, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<RouteDTO>> getAllRoute(Principal principal) {
        List<RouteDTO> routeDTOS = routeService.findAllRoutes(principal.getName());
        return new ResponseEntity<>(routeDTOS, HttpStatus.OK);
    }

    @GetMapping("/available-days")
    public ResponseEntity<List<AvailableDaysRentDTO>> getAllAvailableDaysRend() {
        List<AvailableDaysRentDTO> availableDaysRentDTOS = Arrays.stream(AvailableDaysRent.values())
                .map(availableDaysRentDtoMapper::map).collect(Collectors.toList());

        return new ResponseEntity<>(availableDaysRentDTOS, HttpStatus.OK);
    }

}
