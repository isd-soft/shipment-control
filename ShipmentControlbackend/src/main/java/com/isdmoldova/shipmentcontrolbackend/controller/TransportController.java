package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.UserDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.payload.request.TransportCommand;
import com.isdmoldova.shipmentcontrolbackend.service.TransportService;
import com.isdmoldova.shipmentcontrolbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/transports")
@RequiredArgsConstructor
public class TransportController {
    private final TransportService transportService;

    @PostMapping
    public ResponseEntity<?> addTransport(@RequestBody TransportCommand transport, Principal principal) {
        transportService.add(transport, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<TransportDTO>> getAllTransport(Principal principal) {
        List<TransportDTO> transportDTOS = transportService.findAllTransport(principal.getName());
        return new ResponseEntity<>(transportDTOS, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TransportDTO> getTransportById(@PathVariable("id") Long id, Principal principal) {
        TransportDTO transport = transportService.findTransportByIdAndUsername(id, principal.getName());
        return new ResponseEntity<>(transport, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransport(@RequestBody TransportCommand transport,
                                             @PathVariable Long id,
                                             Principal principal) {
        transportService.update(id, transport, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransport(@PathVariable Long id, Principal principal) {
        transportService.delete(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
