package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoOverviewDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoOverviewCommand;
import com.isdmoldova.shipmentcontrolbackend.service.CargoOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/cargoOverview")
@RequiredArgsConstructor
public class CargoOverviewController {


    private final CargoOverviewService cargoOverviewService;

    @PostMapping
    public ResponseEntity<CargoOverviewDTO> addCargoOverview(@RequestBody CargoOverviewCommand cargoOverviewCommand) {
        cargoOverviewService.add(cargoOverviewCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CargoOverviewDTO>> getAllCargo() {
        List<CargoOverviewDTO> cargoOverviewDTO = cargoOverviewService.findAll();
        return new ResponseEntity<>( cargoOverviewDTO , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoOverviewDTO> updateCargoOverview(@PathVariable("id") Long id, @RequestBody CargoOverviewCommand cargoOverviewCommand) {
        CargoOverviewDTO cargoOverviewDTO = cargoOverviewService.findById(id);
        cargoOverviewService.update(id, cargoOverviewCommand);
        return new ResponseEntity<>(cargoOverviewDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoOverviewDTO> getCargoById(@PathVariable("id") Long id) {
        CargoOverviewDTO cargoOverviewDTO = cargoOverviewService.findById(id);
        return new ResponseEntity<>(cargoOverviewDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCargoOverview(@PathVariable("id") Long id) {
        cargoOverviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
