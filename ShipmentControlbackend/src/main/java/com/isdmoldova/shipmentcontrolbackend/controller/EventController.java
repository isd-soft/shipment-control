package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import com.isdmoldova.shipmentcontrolbackend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
