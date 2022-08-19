package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import org.springframework.stereotype.Component;

@Component
public class TransportDtoMapper {

    public TransportDTO map(Transport transport) {
        final TransportDTO transportDTO = new TransportDTO() ;
        transportDTO.setRoute(transport.getRoute());

        return transportDTO;

}
}
