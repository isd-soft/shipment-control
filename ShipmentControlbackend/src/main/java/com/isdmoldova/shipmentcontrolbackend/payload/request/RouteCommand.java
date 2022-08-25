package com.isdmoldova.shipmentcontrolbackend.payload.request;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.TransportTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Itinerary;

import java.util.List;

public class RouteCommand {

    private Long userId;
    private List<TransportTypeDTO> transportTypeDTOList;
    private List<CargoTypeDTO> cargoTypeDTOList;
    private Itinerary itinerary;



}
