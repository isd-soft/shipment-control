package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoDTO;

import com.isdmoldova.shipmentcontrolbackend.dto.LegDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Leg;

public class LegDtoMapper {

    public LegDTO map(Leg leg) {
        final LegDTO legDTO = new LegDTO();
        legDTO.setAddress(leg.getAddress());
        legDTO.setItinerary(leg.getItinerary());
        legDTO.setName(leg.getName());

        return  legDTO;
    }
}
