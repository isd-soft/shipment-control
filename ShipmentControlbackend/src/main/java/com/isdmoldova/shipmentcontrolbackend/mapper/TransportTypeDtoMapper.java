package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.TransportTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportType;
import org.springframework.stereotype.Component;


@Component
public class TransportTypeDtoMapper {

    public TransportTypeDTO map(TransportType transportType){
        TransportTypeDTO transportTypeDto= new TransportTypeDTO();
        transportTypeDto.setName(transportType.name());
        transportTypeDto.setLabel(transportType.getLabel());

        return transportTypeDto;
    }

}
