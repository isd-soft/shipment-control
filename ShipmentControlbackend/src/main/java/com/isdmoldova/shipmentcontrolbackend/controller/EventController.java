package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {

    @PostMapping("/{trackingId}/{eventType}")
    public ResponseEntity<Void> acceptEvent(@PathVariable("trackingId") String trackingId,
                                            @PathVariable("eventType") EventType eventType) {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

        //TODO: must to do created

    }
}
