package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.BookingRequest;
import com.isdmoldova.shipmentcontrolbackend.payload.request.BookingRequestsCommand;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


public interface BookingRequestsService {

    BookingRequestsDTO findRequestById(Long id);

    BookingRequestsDTO add(BookingRequestsCommand bookingRequestsCommand, String username);

    void delete(Long id);

    List<BookingRequestsDTO> getAllRequests(String username);

    String sendBookingRequest(BookingRequestsDTO bookingRequestsDTO, Principal principal);

    String sendWhenRequestAccept(Principal principal, Long id);

    String sendWhenRequestDeny(Principal principal, Long id);
}
