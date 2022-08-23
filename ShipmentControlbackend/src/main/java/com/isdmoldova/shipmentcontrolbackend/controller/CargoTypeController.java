package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoTypeCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.TransportCommand;
import com.isdmoldova.shipmentcontrolbackend.service.CargoTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cargoType")
@RequiredArgsConstructor
public class CargoTypeController {
    private final CargoTypeService cargoTypeService;


    @PostMapping
    public ResponseEntity<CargoTypeDTO> addCargoType(@RequestBody CargoTypeCommand cargoTypeCommand) {
        cargoTypeService.add(cargoTypeCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CargoTypeDTO>> getAllCargoTypes(){
        List<CargoTypeDTO> transportDTOS = cargoTypeService.findAll();
        return new ResponseEntity<>(transportDTOS,HttpStatus.OK);

    }


}
