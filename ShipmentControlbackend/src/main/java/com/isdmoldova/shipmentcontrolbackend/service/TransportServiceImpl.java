package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.exception.UserNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.mapper.TransportDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.TransportCommand;
import com.isdmoldova.shipmentcontrolbackend.repository.RouteRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.TransportRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransportServiceImpl implements TransportService {
    private final TransportRepository transportRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;
    private final TransportDtoMapper transportDtoMapper;

    @Override
    @Transactional
    public TransportDTO add(TransportCommand command) {
        final User user = userRepository.findById(command.getUserId()).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        final Route route = routeRepository.findById(command.getRouteId()).orElseThrow();
        final Transport transport = new Transport();
        transport.setUser(user);
        transport.setRoute(route);
        transport.setTransportType(command.getTransportType());
        transport.setCargoTypes(command.getCargoTypes());

        return transportDtoMapper.map(transport);
    }

    @Override
    @Transactional
    public TransportDTO update(Long id, TransportCommand command) {
        final Transport transport = transportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("lalala"));
        transport.setTransportType(command.getTransportType());
        transport.setCargoTypes(command.getCargoTypes());

        return transportDtoMapper.map(transport);
    }

    @Override
    @Transactional
    public TransportDTO findById(Long id) {
        final Transport transports = transportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found"));

        return transportDtoMapper.map(transports);
    }

    @Override
    @Transactional
    public TransportDTO findUserById(Long id) {
        final Transport transports = transportRepository.findTransportById(id).orElseThrow(() -> new EntityNotFoundException("User with specified id not found"));

        return transportDtoMapper.map(transports);
    }

    @Override
    @Transactional
    public List<TransportDTO> findAllTransport(Transport transport) {
        final List<Transport> transports = transportRepository.findAll();

        return transports.stream().map(transportDtoMapper::map).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        transportRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<TransportDTO> findAllTransportByUser(String username) {
        List<Transport> transports =
                transportRepository.findAllByUser(userRepository.findUserByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException("User " + username + " not found.")));
        return transports.stream().map(transportDtoMapper::map).collect(Collectors.toList());
    }
}
