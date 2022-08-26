package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;
import com.isdmoldova.shipmentcontrolbackend.service.RouteService;
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
    public ResponseEntity<?> addTransport(@RequestBody RouteCommand routeCommand,
                                          Principal principal) {
        routeService.add(routeCommand, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<RouteDTO>> getAllRoute(Principal principal) {
        List<RouteDTO> routeDTOS = routeService.findAllRoutes(principal.getName());
        return new ResponseEntity<>(routeDTOS, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoute(@RequestBody RouteCommand routeCommand,
                                             @PathVariable Long id,
                                             Principal principal) {
        routeService.update(id, routeCommand, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable Long id, Principal principal) {
        routeService.delete(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
