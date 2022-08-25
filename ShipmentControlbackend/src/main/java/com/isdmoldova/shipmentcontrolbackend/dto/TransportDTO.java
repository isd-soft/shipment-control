package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportType;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Setter
@Getter
public class TransportDTO {

    private Long userId;
  //  private Long routeId;
    private Long transportId;
    private String transportName;
    private String transportType;
    private List<CargoTypeDTO> cargoTypes;

}
