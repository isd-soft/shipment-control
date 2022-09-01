package com.isdmoldova.shipmentcontrolbackend.payload.request;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteCommand {

    @NotEmpty(message = "Please enter the route description")
    private String detailedRouteDescription;

    @NotEmpty(message = "Please enter the transports")
    private List<Long> transportIdList = new ArrayList<>();

    @NotNull(message = "Please enter an itinerary")
    @Valid
    private ItineraryCommand itineraryCommand;

    @Size(min = 1, message = "Please enter available days of rent")
    private List<AvailableDaysRent> availableDaysRentList = new ArrayList<>();

    @NotNull(message = "Please enter the maximum load weight")
    private Double maxLoadWeight;

    @NotNull(message = "Please enter the maximum load volume")
    private Double maxLoadVolume;


}
