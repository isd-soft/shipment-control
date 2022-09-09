package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoOverviewDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoOverviewCommand;
import com.isdmoldova.shipmentcontrolbackend.service.CargoOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cargoOverview")
@RequiredArgsConstructor
public class CargoOverviewController {
    private final CargoOverviewService cargoOverviewService;

    @PreAuthorize("hasRole('SHIPMENT_COMPANY') or hasRole('GOODS_COMPANY') ")
    @GetMapping
    public ResponseEntity<List<CargoOverviewDTO>> getAllCargo(Principal principal) {
        List<CargoOverviewDTO> cargoOverviewDTO = cargoOverviewService.findAll(principal.getName());
        return new ResponseEntity<>( cargoOverviewDTO , HttpStatus.OK);
    }
    @PreAuthorize("hasRole('SHIPMENT_COMPANY') or hasRole('GOODS_COMPANY') ")
    @GetMapping("/{id}")
    public ResponseEntity<CargoOverviewDTO> getCargoById(@PathVariable("id") Long id) {
        CargoOverviewDTO cargoOverviewDTO = cargoOverviewService.findById(id);
        return new ResponseEntity<>(cargoOverviewDTO, HttpStatus.OK);
    }

}
