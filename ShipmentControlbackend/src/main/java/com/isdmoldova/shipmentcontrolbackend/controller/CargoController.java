package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.BookingRequest;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoTypeCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.TransportCommand;
import com.isdmoldova.shipmentcontrolbackend.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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


    @GetMapping()
    public ResponseEntity<List<CargoDTO>> getAllCargoes(Principal principal) {
        List<CargoDTO> cargoDTOS = cargoService.findAllCargoes(principal.getName());
        return new ResponseEntity<>(cargoDTOS, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> getCargoById(@PathVariable("id") Long id) {
        CargoDTO cargo = cargoService.findById(id);
        return new ResponseEntity<>(cargo, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCargo(@RequestBody CargoCommand cargoCommand,
                                             @PathVariable Long id,
                                             Principal principal) {
        cargoService.update(cargoCommand, id,principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCargo(@PathVariable Long id, Principal principal) {
        cargoService.delete(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable Long id, Principal principal){
        cargoService.sendWhenCargoApproved(principal, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping ("/{id}/reject")
    public ResponseEntity<Void> reject(@PathVariable Long id, Principal principal){
        cargoService.sendWhenCargoRejected(principal, id);
        cargoService.delete(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
