package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.BookingRequests;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingRequestsDtoMapper {

    public BookingRequestsDTO map(BookingRequests bookingRequests) {

        BookingRequestsDTO bookingRequestsDTO = new BookingRequestsDTO();

        bookingRequestsDTO.setRouteId(bookingRequests.getRoute().getId());
        bookingRequestsDTO.setShipmentCompanyName(bookingRequests.getRoute().getUser().getCompanyName());
        bookingRequestsDTO.setLocalDateRequested(bookingRequests.getLocalDateRequested());
        bookingRequestsDTO.setGoodsCompanyName(bookingRequests.getUser().getCompanyName());

        return bookingRequestsDTO;
    }

}
