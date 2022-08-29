package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.BookingRequests;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.payload.request.BookingRequestsCommand;

import java.util.List;

public interface BookingRequestsService {

    List<BookingRequestsDTO> getAllByUser(User user);

    void add(BookingRequestsCommand bookingRequestsCommand, String username);


}
