package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import com.isdmoldova.shipmentcontrolbackend.mapper.RouteDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final RouteDtoMapper dtoMapper;

    @Override
    public RouteDTO findByOrigin(String origin){
        return null;
    }

    @Override
    public RouteDTO findByDestination(String destination){
        return  null;
    }

    @Override
    public RouteDTO findRouteByCargoType(CargoType cargoType){
        return null;
    }

}
