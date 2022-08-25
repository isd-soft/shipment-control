package com.isdmoldova.shipmentcontrolbackend.payload.request;

import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteCommand {

    private Long userId;
    private List<Long> transportList;
    private ItineraryCommand itineraryCommand;
    private List<AvailableDaysRent> availableDaysRentList;
    private Double maxLoadWeight;
    private Double maxLoadVolume;
    private Long estimatedAmountTimeShipment;


}
