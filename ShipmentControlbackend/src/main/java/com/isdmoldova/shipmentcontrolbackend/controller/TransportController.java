package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transports")
@RequiredArgsConstructor
public class TransportController {
    private final TransportService transportService;

    @GetMapping("/{username}")
    public ResponseEntity<List<TransportDTO>> getTransportsByUser(@PathVariable String username) {
        List<TransportDTO> transportDTOs = transportService.findAllTransportByUser(username);
        return new ResponseEntity<>(transportDTOs, HttpStatus.OK);
    }
}
