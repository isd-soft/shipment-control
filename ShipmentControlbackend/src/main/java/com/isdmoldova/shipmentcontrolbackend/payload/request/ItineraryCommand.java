package com.isdmoldova.shipmentcontrolbackend.payload.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ItineraryCommand {

    @NotNull(message = "Please enter estimated time of shipment")
    private Long estimatedAmountTimeShipment;

    @Size(min = 2, message = "Please enter the legs")
    @Valid
    private List<LegCommand> legList;

}
