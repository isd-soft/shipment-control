package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransportDtoMapper {

    private final CargoTypeDtoMapper cargoTypeDtoMapper;
    public TransportDTO  map(Transport transport) {
        final TransportDTO transportDTO = new TransportDTO();
        transportDTO.setTransportId(transport.getId());
        transportDTO.setTransportName(transport.getName());
        transportDTO.setTransportType(transport.getTransportType().getLabel());
        transportDTO.setCargoTypes(transport.getCargoTypes()
                .stream().map(cargoTypeDtoMapper::map).collect(Collectors.toList()));
        transportDTO.setUserId(transport.getUser().getId());
        return transportDTO;
    }
}
