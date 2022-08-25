package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.*;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;
import com.isdmoldova.shipmentcontrolbackend.mapper.RouteDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;
import com.isdmoldova.shipmentcontrolbackend.repository.RouteRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.TransportRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final RouteDtoMapper routeDtoMapper;
    private final TransportRepository transportRepository;


    @Override
    @Transactional
    public RouteDTO add(RouteCommand routeCommand, String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));



        Double maxWeight = routeCommand.getMaxLoadWeight();
        Double maxVolume = routeCommand.getMaxLoadVolume();
        Long estimatedDays = routeCommand.getEstimatedAmountTimeShipment();
//        String routeDescription = "smthg";
//        Itinerary itinerary = routeCommand.getItineraryCommand();
        List<AvailableDaysRent> availableDaysRentList = routeCommand.getAvailableDaysRentList();
        List<Transport> transportList = routeCommand.getTransportList().stream()
                .map(transportId -> transportRepository.findById(transportId).orElseThrow(
                        () -> new EntityNotFoundException("Transport type with id " + transportId + " not found")
                ))
                .collect(Collectors.toList());


//        List<Transport> transport = routeCommand.getTransportList()
//                .stream().map(tr -> transportRepository.findById(tr.getId()).orElseThrow())
//                .collect(Collectors.toList());
        Route route = Route.builder().maximalLoadValue(maxWeight).maxLoadVolume(maxVolume)
                .estimatedDays(estimatedDays)
                .availableDaysRent(availableDaysRentList).transports(transportList).build();

        return routeDtoMapper.map(route);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RouteDTO> findAllRoutes(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("Transports for user " + username + " not found"));
        final List<Route> routes = routeRepository.findAllByUser(user);
        return routes.stream().map(routeDtoMapper::map).collect(Collectors.toList());
    }


//    @Override
//    @Transactional
//    public TransportDTO add(TransportCommand command, String username) {
//        final User user = userRepository.findUserByUsername(username)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//        List<CargoType> cargoTypes = command.getCargoTypes().stream()
//                .map(cargoTypeId -> cargoTypeRepository.findById(cargoTypeId).orElseThrow(
//                        () -> new EntityNotFoundException("Cargo type with id " + cargoTypeId + " not found")
//                ))
//                .collect(Collectors.toList());
//        final Transport transport = new Transport();
//        transport.setUser(user);
//        transport.setTransportType(command.getTransportType());
//        transport.setCargoTypes(cargoTypes);
//        transport.setName(command.getTransportName());
//        transportRepository.save(transport);
//        return transportDtoMapper.map(transport);
//    }


}
