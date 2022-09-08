package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CargoOverviewDTO {

    private Long id;
    private String trackingNumber;
    private String origin;
    private String destination;
    private LocalDate bookingDate;
    private CargoStatus cargoStatus;
    private String goodsCompanyName;

}
