package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.TransportCommand;
import com.isdmoldova.shipmentcontrolbackend.service.RouteService;
import com.isdmoldova.shipmentcontrolbackend.service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<Void> addRoute(@RequestBody RouteCommand routeCommand,
                                          Principal principal) {
        routeService.add(routeCommand, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<RouteDTO>> getAllRoute(Principal principal) {
        List<RouteDTO> routeDTOS = routeService.findAllRoutes(principal.getName());
        return new ResponseEntity<>(routeDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDTO> getById(@PathVariable("id") Long id) {
        RouteDTO route = routeService.findById(id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRoute(@RequestBody RouteCommand command, @PathVariable Long id,
                                         Principal principal) {
        routeService.update(command, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
