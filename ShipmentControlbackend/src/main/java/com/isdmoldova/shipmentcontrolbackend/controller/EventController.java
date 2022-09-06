package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.EventLogDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import com.isdmoldova.shipmentcontrolbackend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/{trackingNumber}/{eventType}")
    public ResponseEntity<Void> acceptEvent(@PathVariable("trackingNumber") String trackingNumber,
                                            @PathVariable("eventType") EventType eventType) {
        eventService.processEvent(trackingNumber, eventType);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{trackingNumber}")
    public ResponseEntity<List<EventLogDTO>> getAllByTrackingNumber(
            @PathVariable("trackingNumber") String trackingNumber){
       List<EventLogDTO> eventLogDTOS = eventService.findAllEventsByTrackNumber(trackingNumber);
        return new ResponseEntity<>(eventLogDTOS,HttpStatus.OK);
    }

}
