package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoType;
import com.isdmoldova.shipmentcontrolbackend.mapper.RouteDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteService implements RouteServieImpl{

    private final RouteRepository routeRepository;
    private final RouteDtoMapper dtoMapper;

    @Override
    public List<Route> findByOrigin(String origin){

        return routeRepository.findByOrigin(origin);
    }

    @Override
    public List<Route> findByDestination(String destination){
        return routeRepository.findByDestination(destination);
    }

    @Override
    public Optional<Route> findRouteByCargoType(CargoType cargoType){
        return routeRepository.findRouteByCargoType(cargoType);
    }

}
