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
import com.isdmoldova.shipmentcontrolbackend.util.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
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
    public ResponseEntity<Void> addRoute(@Validated @RequestBody RouteCommand routeCommand,
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
    @GetMapping("/available-days")
    public ResponseEntity<List<AvailableDaysRentDTO>> getAllAvailableDaysRend() {
        List<AvailableDaysRentDTO> availableDaysRentDTOS = Arrays.stream(AvailableDaysRent.values())
                .map(availableDaysRentDtoMapper::map).collect(Collectors.toList());

        return new ResponseEntity<>(availableDaysRentDTOS, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRoute(@RequestBody RouteCommand command, @PathVariable Long id,
                                         Principal principal) {
        routeService.update(command, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable Long id, Principal principal) {
        routeService.delete(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
