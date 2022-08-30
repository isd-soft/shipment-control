package com.isdmoldova.shipmentcontrolbackend.email.service;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;

import java.security.Principal;


public interface EmailService {

    String sendBookingRequest(BookingRequestsDTO bookingRequestsDTO, Principal principal);

    String sendWhenRequestAccept(Principal principal, Long id);
    String sendWhenRequestDeny(Principal principal, Long id);
}
