package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.email.service.EmailService;
import com.isdmoldova.shipmentcontrolbackend.payload.request.BookingRequestsCommand;
import com.isdmoldova.shipmentcontrolbackend.service.BookingRequestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking-request")
public class BookingRequestsController {

    private final BookingRequestsService bookingRequestsService;
    private final EmailService emailService;

    @GetMapping
    public ResponseEntity<List<BookingRequestsDTO>> getAllRequests(Principal principal) {
        List<BookingRequestsDTO> bookingRequestsDTOS =
                bookingRequestsService.getAllRequests(principal.getName());
        return new ResponseEntity<>(bookingRequestsDTOS, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> requestBooking(
            @RequestBody BookingRequestsCommand bookingRequestsCommand, Principal principal) {
        BookingRequestsDTO bookingRequestsDTO = bookingRequestsService.add(bookingRequestsCommand, principal.getName());
        String alertInfo = emailService.sendBookingRequest(bookingRequestsDTO, principal);

        return new ResponseEntity<>(alertInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long id) {
        bookingRequestsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/accept/{id}")
    public ResponseEntity<?> deleteOnAccept(@PathVariable Long id, Principal principal) {
        emailService.sendWhenRequestAccept(principal, id);
        bookingRequestsService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deny/{id}")
    public ResponseEntity<?> deleteOnDeny(@PathVariable Long id, Principal principal) {
        emailService.sendWhenRequestDeny(principal, id);
        bookingRequestsService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
