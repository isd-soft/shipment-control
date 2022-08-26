package com.isdmoldova.shipmentcontrolbackend.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class ItineraryCommand {

    private Long estimatedAmountTimeShipment;
    private List<LegCommand> legList;
}
