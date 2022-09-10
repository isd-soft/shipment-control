package com.isdmoldova.shipmentcontrolbackend.mapper;
import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.BookingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingRequestsDtoMapper {
    private final RouteDtoMapper routeDtoMapper;

    public BookingRequestsDTO map(BookingRequest bookingRequest) {

        BookingRequestsDTO bookingRequestsDTO = new BookingRequestsDTO();

        bookingRequestsDTO.setRouteId(bookingRequest.getRoute().getId());
        bookingRequestsDTO.setShipmentCompanyName(bookingRequest.getRoute().getUser().getCompanyName());
        bookingRequestsDTO.setLocalDateRequested(bookingRequest.getLocalDateRequested());
        bookingRequestsDTO.setGoodsCompanyName(bookingRequest.getUser().getCompanyName());
        bookingRequestsDTO.setBookingRequestId(bookingRequest.getId());
        bookingRequestsDTO.setRouteDescription(bookingRequest.getRoute().getDetailedRouteDescription());


        return bookingRequestsDTO;
    }

}
