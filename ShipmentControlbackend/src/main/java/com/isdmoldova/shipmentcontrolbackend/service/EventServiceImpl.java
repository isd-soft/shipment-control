package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.EventLogDTO;
import com.isdmoldova.shipmentcontrolbackend.email.service.EmailService;
import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.EventLog;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import com.isdmoldova.shipmentcontrolbackend.exception.EventTypeNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.mapper.EventLogDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.repository.CargoRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.EventLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final EmailService emailService;

    private final EventLogDtoMapper eventLogDtoMapper;
    private final List<EventProcessorStrategy> strategies;
    @Value("${com.isdmoldova.shipment.control.from.email}")
    private String shipmentControlFromEmail;

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
        eventLog.setLeg(cargo.getCurrentLeg().getCountry() + "_" + cargo.getCurrentLeg().getAddress());

        eventLogRepository.save(eventLog);
        if (eventType == EventType.ARRIVED_AT_DESTINATION) {
            sendEmailWhenArrived(cargo);
        }
        if (eventType == EventType.ARRIVED_AT) {
            sendEmailWhenArrivedAt(cargo);
        }
    }

    private void sendEmailWhenArrivedAt(Cargo cargo) {

        final String goodsEmail = cargo.getUser().getEmail();
        final String goodsName = cargo.getUser().getCompanyName();
        final String subject = "The Cargo has ARRIVED at the next LEG.";
        final String textToGoods = "Hello dear " + goodsName + ",\n\nThe cargo with Tracking Number: "
                + cargo.getTrackingNumber() + ", booked on date: " + cargo.getBookingDate()
                + ",\nHas ARRIVED at the leg: " + cargo.getCurrentLeg().getAddress()
                + cargo.getCurrentLeg().getCountry()
                + ".\n\nPlease make sure to check the status and follow based on the information provided."
                + "\n\n\n\nBest regards, \nShipment Control Service Tech Team.";

        emailService.sendEmail(shipmentControlFromEmail, goodsEmail, subject, textToGoods);
    }

    private void sendEmailWhenArrived(Cargo cargo) {

        final String goodsEmail = cargo.getUser().getEmail();
        final String shipmentEmail = cargo.getProvider().getEmail();
        final String goodsName = cargo.getUser().getCompanyName();
        final String shipmentName = cargo.getProvider().getCompanyName();
        final String subject = "The Cargo has ARRIVED.";

        final String textToGoods = "Hello dear " + goodsName + ",\n\nThe cargo with Tracking Number: "
                + cargo.getTrackingNumber() + ", booked on date: " + cargo.getBookingDate()
                + ",\nHas ARRIVED at destination: " + cargo.getDestination().getAddress()
                + ".\n\nPlease make sure to check the status and follow based on the information provided."
                + "\n\n\n\nBest regards, \nShipment Control Service Tech Team.";

        final String textToShipment = "Hello dear " + shipmentName + ",\n\nThe cargo with Tracking Number: "
                + cargo.getTrackingNumber() + ", booked on date: " + cargo.getBookingDate()
                + ",\nHas ARRIVED at destination: " + cargo.getDestination().getAddress()
                + ".\n\nPlease make sure to check the status and follow based on the information provided."
                + "\n\n\n\nBest regards, \nShipment Control Service Tech Team.";


        emailService.sendEmail(shipmentControlFromEmail, goodsEmail, subject, textToGoods);
        emailService.sendEmail(shipmentControlFromEmail, shipmentEmail, subject, textToShipment);
    }

    @Override
    @Transactional
    public List<EventLogDTO> findAllEventsByTrackNumber(String trackingNumber) {
        return eventLogRepository.findAllByTrackingNumber(trackingNumber).orElseThrow(
                        () -> new EntityNotFoundException("Event Log with trackingNumber" + trackingNumber +
                                " is not found")
                )
                .stream().map(eventLogDtoMapper::map)
                .collect(Collectors.toList());
    }

}
