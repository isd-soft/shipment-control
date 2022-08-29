package com.isdmoldova.shipmentcontrolbackend.payload.request;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteCommand {

    private String detailedRouteDescription;
    private List<Long> transportIdList = new ArrayList<>();
    private ItineraryCommand itineraryCommand;
    private List<AvailableDaysRent> availableDaysRentList = new ArrayList<>();
    private Double maxLoadWeight;
    private Double maxLoadVolume;


}
