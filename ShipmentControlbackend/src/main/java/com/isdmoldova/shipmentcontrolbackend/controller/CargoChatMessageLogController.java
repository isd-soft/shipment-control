package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoChatMessageLogDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoChatMessageLogCommand;
import com.isdmoldova.shipmentcontrolbackend.service.CargoChatMessageLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat-log")
public class CargoChatMessageLogController {

    private final CargoChatMessageLogService cargoChatMessageLogService;


    @GetMapping("/{cargoId}")
    public ResponseEntity<List<CargoChatMessageLogDTO>> getAllCargoChatLog(
            @PathVariable Long cargoId, Principal principal) {

        List<CargoChatMessageLogDTO> cargoChatMessageLogDTOS =
                cargoChatMessageLogService.getAllCargoChatLog(cargoId, principal);

        return new ResponseEntity<>(cargoChatMessageLogDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCargoChatLog(
            @RequestBody CargoChatMessageLogCommand cargoChatMessageLogCommand, Principal principal) {

        cargoChatMessageLogService.addCargoChatLog(cargoChatMessageLogCommand, principal);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCargoChatLog(@PathVariable Long id, Principal principal){

        cargoChatMessageLogService.deleteCargoChatLog(id, principal);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<?> updateCargoChatLog(
            @RequestBody CargoChatMessageLogCommand cargoChatMessageLogCommand, Principal principal){

        cargoChatMessageLogService.updateCargoChatLog(cargoChatMessageLogCommand, principal);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
