package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.ItineraryDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.email.service.EmailService;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.mapper.BookingRequestsDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.BookingRequestsCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoOverviewCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.ItineraryCommand;
import com.isdmoldova.shipmentcontrolbackend.service.BookingRequestsService;
import com.isdmoldova.shipmentcontrolbackend.service.CargoService;
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
    private final CargoService cargoService;
    private final BookingRequestsDtoMapper bookingRequestsDtoMapper;

    @GetMapping
    public ResponseEntity<List<BookingRequestsDTO>> getAllRequests(Principal principal) {
        List<BookingRequestsDTO> bookingRequestsDTOS =
                bookingRequestsService.getAllRequests(principal.getName());
        return new ResponseEntity<>(bookingRequestsDTOS, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> requestBooking(
            @RequestBody BookingRequestsCommand bookingRequestsCommand, Principal principal) {
        BookingRequestsDTO bookingRequestsDTO = bookingRequestsService.add(bookingRequestsCommand, principal.getName());
        bookingRequestsService.sendBookingRequest(bookingRequestsDTO, principal);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long id) {
        bookingRequestsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/accept/{id}")
    public ResponseEntity<?> deleteOnAccept(@PathVariable Long id, Principal principal) {
        bookingRequestsService.sendWhenRequestAccept(principal, id);
        bookingRequestsService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deny/{id}")
    public ResponseEntity<?> deleteOnDeny(@PathVariable Long id, Principal principal) {
        bookingRequestsService.sendWhenRequestDeny(principal, id);
        bookingRequestsService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
