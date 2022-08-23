package com.isdmoldova.shipmentcontrolbackend.payload.request;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransportCommand {

    private Long userId;
    private Long routeId;
   // private Long transportId;
    private String transportName;
    private TransportType transportType;
    private List<CargoType> cargoTypes;
}
