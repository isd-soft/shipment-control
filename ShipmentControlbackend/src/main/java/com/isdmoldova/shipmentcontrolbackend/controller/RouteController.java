package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.*;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;
import com.isdmoldova.shipmentcontrolbackend.mapper.AvailableDaysRentDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;
import com.isdmoldova.shipmentcontrolbackend.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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

    @PreAuthorize("hasRole('SHIPMENT_COMPANY')")
    @PostMapping
    public ResponseEntity<Void> addRoute(@Validated @RequestBody RouteCommand routeCommand,
                                         Principal principal) {
        routeService.add(routeCommand, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('SHIPMENT_COMPANY') or hasRole('GOODS_COMPANY')")
    @GetMapping()
    public ResponseEntity<List<RouteDTO>> getAllRoute(Principal principal) {
        List<RouteDTO> routeDTOS = routeService.findAllRoutes(principal.getName());
        return new ResponseEntity<>(routeDTOS, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('SHIPMENT_COMPANY') or hasRole('GOODS_COMPANY')")
    @GetMapping("/{id}")
    public ResponseEntity<RouteDTO> getById(@PathVariable("id") Long id) {
        RouteDTO route = routeService.findById(id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SHIPMENT_COMPANY') or hasRole('GOODS_COMPANY')")
    @GetMapping("/available-days")
    public ResponseEntity<List<AvailableDaysRentDTO>> getAllAvailableDaysRend() {
        List<AvailableDaysRentDTO> availableDaysRentDTOS = Arrays.stream(AvailableDaysRent.values())
                .map(availableDaysRentDtoMapper::map).collect(Collectors.toList());
        return new ResponseEntity<>(availableDaysRentDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SHIPMENT_COMPANY')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRoute(@RequestBody RouteCommand command, @PathVariable Long id,
                                            Principal principal) {
        routeService.update(command, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('SHIPMENT_COMPANY')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable Long id, Principal principal) {
        routeService.delete(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('GOODS_COMPANY')")
    @GetMapping("/days-available/{id}")
    public ResponseEntity<List<AvailableDaysRentDTO>> requestAvailableDays(@PathVariable Long id) {
        List<AvailableDaysRentDTO> availableDaysRentDTOS = routeService.findAvailableDaysRentById(id);
        return new ResponseEntity<>(availableDaysRentDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SHIPMENT_COMPANY') or hasRole('GOODS_COMPANY')")
    @GetMapping("/{id}/legs")
    public ResponseEntity<List<LegDTO>> getRouteLegs(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.getLegsForRoute(id));
    }
}

