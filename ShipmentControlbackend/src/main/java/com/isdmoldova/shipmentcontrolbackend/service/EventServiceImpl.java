package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.EventLogDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.EventLog;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import com.isdmoldova.shipmentcontrolbackend.exception.EventTypeNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.mapper.EventLogDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.repository.CargoRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.EventLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class EventServiceImpl implements EventService {

    private final CargoRepository cargoRepository;
    private final EventLogRepository eventLogRepository;

    private final EventLogDtoMapper eventLogDtoMapper;
    private final List<EventProcessorStrategy> strategies;

    @Override
    @Transactional
    public void processEvent(String trackingNumber, EventType eventType) {
        final Cargo cargo = cargoRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cargo with trackingId "
                                + trackingNumber + " not found "));
        strategies.stream()
                .filter(strategy -> strategy.supports(eventType))
                .findFirst()
                .orElseThrow(() ->
                        new EventTypeNotFoundException("EventType + " + eventType + " not found"))
                .process(cargo);


        EventLog eventLog = new EventLog();

        eventLog.setTrackingNumber(cargo.getTrackingNumber());
        eventLog.setEventType(eventType);
        eventLog.setCargoStatus(cargo.getCargoStatus());
        eventLog.setLeg(cargo.getCurrentLeg().getCountry() + cargo.getCurrentLeg().getAddress());

        eventLogRepository.save(eventLog);
    }

    @Override
    @Transactional
    public List<EventLogDTO> findAllEventsByTrackNumber(String trackingNumber) {
        return eventLogRepository.findAllByTrackingNumber(trackingNumber).orElseThrow(
                        ()-> new EntityNotFoundException("Event Log with trackingNumber" + trackingNumber +
                                " is not found")
                )
                .stream().map(eventLogDtoMapper::map)
                .collect(Collectors.toList());
    }

}
