package com.isdmoldova.shipmentcontrolbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingRequestsDTO {

    private Long routeId;
    private String shipmentCompanyName;
    private String goodsCompanyName;
    private LocalDate localDateRequested;

}
