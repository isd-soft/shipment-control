package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportType;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class TransportDTO {

    private Integer routeId;
    private Long transportId;
    private String transportName;
    private TransportType transportType;
    private List<CargoType> cargoTypes;
}
