package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
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
import java.util.List;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<Void> addRoute(@Validated @RequestBody RouteCommand routeCommand,
                                          Principal principal) {
        routeService.add(routeCommand, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ExceptionResponse> customException(BadCredentialsException ex) {
//        ExceptionResponse response=new ExceptionResponse();
//        response.setErrorCode("BAD_REQUEST");
//        response.setErrorMessage(ex.getMessage());
//        response.setTimestamp(LocalDateTime.now());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }



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
    public ResponseEntity<?> updateRoute(@RequestBody RouteCommand routeCommand,
                                             @PathVariable Long id) {
        routeService.update(routeCommand, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable Long id, Principal principal) {
        routeService.delete(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
