package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.exception.TransportNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.exception.UserNotAllowedException;
import com.isdmoldova.shipmentcontrolbackend.exception.UserNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.mapper.TransportDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.TransportCommand;
import com.isdmoldova.shipmentcontrolbackend.repository.RouteRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.TransportRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.TransactionalException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
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
    public TransportDTO add(TransportCommand command, String username) {
        final User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        final Transport transport = new Transport();
        transport.setUser(user);
        transport.setTransportType(command.getTransportType());
//        transport.setCargoTypes(command.getCargoTypes());
        transportRepository.save(transport);
        return transportDtoMapper.map(transport);
    }


    @Override
    @Transactional(readOnly = true)
    public TransportDTO findTransportByIdAndUsername(Long id, String username) {
        return transportRepository.findTransportByIdAndUserUsername(id, username)
                .map(transportDtoMapper::map)
                .orElseThrow(
                        () -> new TransportNotFoundException("Transport with id " + id + " for username " + username + " not found"));
    }


    @Override
    @Transactional(readOnly = true)
    public List<TransportDTO> findAllTransport(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new UserNotFoundException("Transports for user " + username + " not found"));
        final List<Transport> transports = transportRepository.findAllByUser(user);
        return transports.stream().map(transportDtoMapper::map).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public TransportDTO update(Long id, TransportCommand command, String username) {
        Transport transport = transportRepository.findById(id).orElseThrow(
                () -> new TransportNotFoundException("Transport entity not found by specified id " + id));
        if (!transport.getUser().getUsername().equals(username)) {
            throw new UserNotAllowedException("User " + username + " not allowed to update transport with id " + id);
        }
        transport.setTransportType(command.getTransportType());
        //        transport.setCargoTypes(command.getCargoTypes());
        transportRepository.save(transport);
        return transportDtoMapper.map(transport);
    }


    @Override
    @Transactional
    public void delete(Long id, String username) {
        Transport transport = transportRepository.findById(id).orElseThrow(
                () -> new TransportNotFoundException("Transport entity not found by specified id " + id));
        if (!transport.getUser().getUsername().equals(username)) {
            throw new UserNotAllowedException("User " + username + " not allowed to delete transport with id " + id);
        }
        transportRepository.deleteById(id);
    }

}
