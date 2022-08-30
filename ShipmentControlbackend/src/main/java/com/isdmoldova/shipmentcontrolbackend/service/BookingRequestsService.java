package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.BookingRequestsCommand;

import java.util.List;


public interface BookingRequestsService {

    BookingRequestsDTO add(BookingRequestsCommand bookingRequestsCommand, String username);

    void delete(Long id);

    List<BookingRequestsDTO> getAllRequests(String username);
}
