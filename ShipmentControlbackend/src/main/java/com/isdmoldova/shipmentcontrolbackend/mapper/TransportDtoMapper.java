package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import org.springframework.stereotype.Component;

@Component
public class TransportDtoMapper {
    public TransportDTO map(Transport transport) {
        final TransportDTO transportDTO = new TransportDTO();
        transportDTO.setTransportId(transport.getId());
        transportDTO.setTransportName(transport.getName());
        transportDTO.setTransportType(transport.getTransportType());
        transportDTO.setCargoTypes(transport.getCargoTypes());
        transportDTO.setUserId(transport.getUser().getId());
        return transportDTO;
    }
}
