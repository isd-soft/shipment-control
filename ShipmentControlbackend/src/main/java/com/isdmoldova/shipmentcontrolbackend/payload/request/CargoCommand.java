package com.isdmoldova.shipmentcontrolbackend.payload.request;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

import java.util.List;

@Getter
@Setter
public class CargoCommand {

    private Double totalVolume;
    private Double totalWeight;
    private List<Long> cargoTypeList;
    //private CargoStatus cargoStatus;
    private ItineraryCommand itineraryCommand;
    private LocalDate bookingDate;
    private Long routeId;

}
