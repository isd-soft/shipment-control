package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.AvailableDaysRentDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.*;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;
import com.isdmoldova.shipmentcontrolbackend.mapper.AvailableDaysRentDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.mapper.ItineraryDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.mapper.LegDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.mapper.RouteDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.ItineraryCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.LegCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;
import com.isdmoldova.shipmentcontrolbackend.repository.ItineraryRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.RouteRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.TransportRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final RouteDtoMapper routeDtoMapper;
    private final TransportRepository transportRepository;
    private final ItineraryRepository itineraryRepository;
    private final ItineraryDtoMapper itineraryDtoMapper;
    private final LegDtoMapper legDtoMapper;
    private final AvailableDaysRentDtoMapper availableDaysRentDtoMapper;
    private final EditRouteValidation editRouteValidation;


    @Override
    @Transactional
    public RouteDTO add(RouteCommand routeCommand, String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Double maxWeight = routeCommand.getMaxLoadWeight();
        Double maxVolume = routeCommand.getMaxLoadVolume();

        List<AvailableDaysRent> availableDaysRentList = routeCommand.getAvailableDaysRentList();

        List<Transport> transportList = routeCommand.getTransportIdList()
                .stream().map(transportId -> transportRepository.findById(transportId).orElseThrow(
                        () -> new EntityNotFoundException("Transport with id " + transportId + " not found")))
                .collect(Collectors.toList());

        List<LegCommand> legCommandList = routeCommand.getItineraryCommand().getLegList();
        List<Leg> legs = legCommandList.stream()
                .map(leg -> new Leg(leg.getCountry(), leg.getCountryCode(), leg.getAddress(), leg.getName(), leg.getPrice()))
                .collect(Collectors.toList());

        Long estimatedAmountTimeShipment = routeCommand.getItineraryCommand().getEstimatedAmountTimeShipment();

        Itinerary itinerary = new Itinerary(estimatedAmountTimeShipment);

        legs.forEach(itinerary::addLeg);
        itineraryRepository.save(itinerary);
        Route route = new Route(routeCommand.getDetailedRouteDescription(),
                user,
                availableDaysRentList,
                maxWeight,
                maxVolume);
        route.addItinerary(itinerary);
        transportList.forEach(route::addTransport);
        routeRepository.save(route);
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

    @Override
    @Transactional(readOnly = true)
    public RouteDTO findById(Long id) {
        return routeRepository.findById(id).map(routeDtoMapper::map)
                .orElseThrow(() -> new EntityNotFoundException("Route with id " + id + " does not exist!"));
    }

    @Override
    @Transactional
    public RouteDTO update(RouteCommand command, Long id) {

        editRouteValidation.validate(command);

        Route route = routeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Route with this " + id + " does not found!"));
        route.setDetailedRouteDescription(command.getDetailedRouteDescription());
        route.clearTransports();

        List<Transport> transportList = command.getTransportIdList()
                .stream().map(transportId -> transportRepository.findById(transportId).orElseThrow(
                        () -> new EntityNotFoundException("Transport with id " + transportId + " not found")))
                .collect(Collectors.toList());
        transportList.forEach(route::addTransport);
        route.setAvailableDaysRent(command.getAvailableDaysRentList());
        Itinerary itinerary = route.getItinerary();
        itinerary.clearLegs();

        List<Leg> legs = command.getItineraryCommand().getLegList().stream()
                .map(leg -> new Leg(leg.getCountry(), leg.getCountryCode(), leg.getAddress(), leg.getName(), leg.getPrice()))
                .collect(Collectors.toList());
        legs.forEach(itinerary::addLeg);
        itinerary.setDaysOfExecution(command.getItineraryCommand().getEstimatedAmountTimeShipment());
        route.setMaxLoadVolume(command.getMaxLoadVolume());
        route.setMaximalLoadValue(command.getMaxLoadWeight());
        routeRepository.save(route);

        return routeDtoMapper.map(route);
    }


    @Override
    @Transactional
    public void delete(Long id, String username) {
        Route route = routeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Route not found with id " + id));

        if (!route.getUser().getUsername().equals(username)) {
            throw new EntityNotFoundException("User " + username + " is not allowed to delete route with id " + id);
        }

        routeRepository.delete(route);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AvailableDaysRentDTO> findAvailableDaysRentById(Long routeId) {
        Route route = routeRepository.findById(routeId).orElseThrow(
                () -> new EntityNotFoundException("Route not found with id " + routeId));

        List<AvailableDaysRent> daysRent = routeRepository.findAvailableDaysRentById(route.getId());

        return daysRent.stream().map(availableDaysRentDtoMapper::map).collect(Collectors.toList());
    }

}


