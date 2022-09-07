package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.LegDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Leg;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LegDtoMapper {

    public LegDTO map(Leg leg) {
        LegDTO legDTO = new LegDTO();
        legDTO.setName(leg.getName());
        legDTO.setAddress(leg.getAddress());
        legDTO.setCountry(leg.getCountry());
        legDTO.setCountryCode(leg.getCountryCode());
        legDTO.setPrice(leg.getPrice());

        return legDTO;
    }
}
