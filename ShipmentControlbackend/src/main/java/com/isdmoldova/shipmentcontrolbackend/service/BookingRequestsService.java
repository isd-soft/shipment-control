package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.BookingRequestsCommand;


public interface BookingRequestsService {

    BookingRequestsDTO add(BookingRequestsCommand bookingRequestsCommand, String username);

}
