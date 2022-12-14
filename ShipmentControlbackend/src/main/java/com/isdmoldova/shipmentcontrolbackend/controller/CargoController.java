package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoCommand;
import com.isdmoldova.shipmentcontrolbackend.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cargo")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    @PreAuthorize("hasRole('GOODS_COMPANY')")
    @PostMapping
    public ResponseEntity<Void> addCargo(@Validated @RequestBody CargoCommand cargoCommand,
                                         Principal principal) {
        cargoService.add(cargoCommand, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //TODO: may be need
    @GetMapping()
    public ResponseEntity<List<CargoDTO>> getAllCargoes(Principal principal) {
        List<CargoDTO> cargoDTOS = cargoService.findAllCargoes(principal.getName());
        return new ResponseEntity<>(cargoDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SHIPMENT_COMPANY') or hasRole('GOODS_COMPANY') ")
    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> getCargoById(@PathVariable("id") Long id) {
        CargoDTO cargo = cargoService.findById(id);
        return new ResponseEntity<>(cargo, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SHIPMENT_COMPANY')")
    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable Long id, Principal principal){
        cargoService.sendWhenCargoApproved(principal, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SHIPMENT_COMPANY')")
    @DeleteMapping ("/{id}/reject")
    public ResponseEntity<Void> reject(@PathVariable Long id, Principal principal){
        cargoService.sendWhenCargoRejected(principal, id);
        cargoService.delete(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
