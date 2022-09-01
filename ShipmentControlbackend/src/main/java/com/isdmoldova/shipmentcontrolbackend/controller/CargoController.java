package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoTypeCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;
import com.isdmoldova.shipmentcontrolbackend.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/cargo")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    @PostMapping
    public ResponseEntity<Void> addCargo(@Validated @RequestBody CargoCommand cargoCommand,
                                         Principal principal) {
        cargoService.add(cargoCommand, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
