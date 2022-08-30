package com.isdmoldova.shipmentcontrolbackend.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingRequestsCommand {

    private Long routeId;
    private LocalDate localDateRequested;

}
