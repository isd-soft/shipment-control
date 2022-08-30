package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.payload.request.BookingRequestsCommand;
import com.isdmoldova.shipmentcontrolbackend.service.BookingRequestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking-request")
public class BookingRequestsController {

    private final BookingRequestsService bookingRequestsService;

    @PostMapping
    public ResponseEntity<?> requestBooking(
            @RequestBody BookingRequestsCommand bookingRequestsCommand, Principal principal) {
        bookingRequestsService.add(bookingRequestsCommand, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
