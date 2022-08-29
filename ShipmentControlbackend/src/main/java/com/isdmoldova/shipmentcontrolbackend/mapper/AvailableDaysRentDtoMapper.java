package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.AvailableDaysRentDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;
import org.springframework.stereotype.Component;

@Component
public class AvailableDaysRentDtoMapper {

    public AvailableDaysRentDTO map(AvailableDaysRent availableDaysRent){
        AvailableDaysRentDTO availableDaysRentDTO = new AvailableDaysRentDTO();

        availableDaysRentDTO.setName(availableDaysRent.name());
        availableDaysRentDTO.setLabel(availableDaysRent.getLabel());

        return availableDaysRentDTO;
    }

}
