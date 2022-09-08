package com.isdmoldova.shipmentcontrolbackend.service;


import com.isdmoldova.shipmentcontrolbackend.email.service.EmailService;
import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;

import com.isdmoldova.shipmentcontrolbackend.dto.*;
import com.isdmoldova.shipmentcontrolbackend.entity.*;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.mapper.CargoDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.mapper.CargoTypeDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.LegCommand;
import com.isdmoldova.shipmentcontrolbackend.repository.CargoRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.CargoTypeRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.RouteRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {

    private final CargoTypeRepository cargoTypeRepository;
    private final CargoTypeDtoMapper cargoDtoMapper;
    private final CargoDtoMapper cargoMapper;
    private final CargoRepository cargoRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final RouteRepository routeRepository;
    private final CargoDtoMapper cargoDTOMapper;
    @Value("com.isdmoldova.shipment.control.from.email")
    private String shipmentControlFromEmail;

    @Override
    @Transactional
    public CargoDTO add(CargoCommand cargoCommand, String username) {
        final User user = userRepository.findUserByUsername(username)
                .orElseThrow(()-> new EntityNotFoundException("User not found"));

        List<CargoType> cargoTypes = cargoCommand.getCargoTypeList().stream()
                .map(cargoTypeId -> cargoTypeRepository.findById(cargoTypeId).orElseThrow(
                        () -> new EntityNotFoundException("Cargo type with id " + cargoTypeId + " not found")))
                .collect(Collectors.toList());

        List<LegCommand> legCommandList = cargoCommand.getItineraryCommand().getLegList();
        List<Leg> legs = legCommandList.stream()
                .map(leg -> new Leg(leg.getCountry(), leg.getCountryCode(), leg.getAddress(), leg.getName(), leg.getPrice()))
                .collect(Collectors.toList());
        Long estimatedAmountTimeShipment = cargoCommand.getItineraryCommand().getEstimatedAmountTimeShipment();
        Itinerary itinerary = new Itinerary(estimatedAmountTimeShipment);
        legs.forEach(itinerary::addLeg);

        final Route route = routeRepository.findById(cargoCommand.getRouteId())
                .orElseThrow();

        final Cargo cargo = new Cargo();
        cargo.setUser(user);
        cargoTypes.forEach(cargo::addCargoType);
        cargo.setTotalVolume(cargoCommand.getTotalVolume());
        cargo.setTotalWeight(cargoCommand.getTotalWeight());
        cargo.setBookingDate(cargoCommand.getBookingDate());
        cargo.setProvider(route.getUser());

        cargoRepository.save(cargo);
        cargo.setItinerary(itinerary);
        return cargoMapper.map(cargo);

    }

    @Override
    @Transactional(readOnly = true)
    public List<CargoDTO> findAllCargoes(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("Cargoes for user " + username + " not found"));
        final List<Cargo> cargos = cargoRepository.findAllByUser(user);
        return cargos.stream().map(cargoMapper::map).collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public CargoDTO findById(Long id) {
        return cargoRepository.findById(id).map(cargoMapper::map)
                .orElseThrow(() -> new EntityNotFoundException("Cargo with id " + id + " does not exist!"));
    }

    @Override
    @Transactional
    public CargoDTO update(CargoCommand cargoCommand, Long id,String username) {
        Cargo cargo = cargoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cargo entity not found by specified id " + id));
        if (!cargo.getUser().getUsername().equals(username)) {
            throw new EntityNotFoundException("User " + username + " is not allowed to update Cargo with id " + id);
        }

        cargo.setTotalVolume(cargoCommand.getTotalVolume());
        cargo.setTotalWeight(cargoCommand.getTotalWeight());
        cargoRepository.save(cargo);
        return cargoMapper.map(cargo);


    }

//    @Override
//    public void delete(Long id, String username) {
//        Cargo cargo = cargoRepository.findById(id).orElseThrow(
//                () -> new EntityNotFoundException("Cargo not found with id " + id));
//        cargo.getCargoTypes().forEach(cargoType -> cargoType.removeCargo(cargo));
//        if (!cargo.getUser().getUsername().equals(username)) {
//            throw new EntityNotFoundException("User " + username + " is not allowed to delete route with id " + id);
//        }
//        cargoRepository.delete(cargo);
//    }

//delete cargo by goodsCompany userId
    @Override
    @Transactional
    public void delete(Long id, String username) {
        Cargo cargo = cargoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cargo not found with id " + id));
        cargo.getCargoTypes().forEach(cargoType -> cargoType.removeCargo(cargo));
        if (!cargo.getUser().getUsername().equals(username)) {
            throw new EntityNotFoundException("User " + username + " is not allowed to delete route with id " + id);
        }
       cargoRepository.delete(cargo);
    }

    @Override
    @Transactional
    public String sendWhenCargoApproved(Principal principal, Long id) {
        Cargo cargo = cargoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Request with " + id + " not found"));
        cargo.setCargoStatus(CargoStatus.PREPARING);
        cargo.setTrackingNumber(generateTrackingNumber(cargo));
        String goodsCompanyEmail = cargo.getUser().getEmail();
        String subject = "Shipment Control Notification";
        String content = "\nHello, " + cargo.getUser().getUsername()
                + "\n your cargo booked for the date " + cargo.getBookingDate()
                + "\n from the origin " + cargo.getOrigin().getAddress()
                + "\n to the destination " + cargo.getDestination().getAddress()
                + "\n has been APPROVED. Follow the tracking number below to see the status of your cargo.\n "
                + cargo.getTrackingNumber()
                + "\nPlease check the required information to deliver the cargo to " + cargo.getOrigin().getAddress()
                + "\n\n\n\nBest regards, \nShipment Control Service Tech Team.";
        return emailService.sendEmail(shipmentControlFromEmail, goodsCompanyEmail, subject, content);
    }

    private String generateTrackingNumber(Cargo cargo) {
        return cargo.getId().toString() + cargo.getUser().getUsername()
                + cargo.getBookingDate().format(DateTimeFormatter.ofPattern("ddMMMyy"));
    }

    @Override
    @Transactional
    public String sendWhenCargoRejected(Principal principal, Long id) {
        Cargo cargo = cargoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Request with " + id + " not found"));
        String goodsCompanyEmail = cargo.getUser().getEmail();
        String subject = "Shipment Control Notification";
        String content = "\nHello, " + cargo.getUser().getUsername()
                + "\n your cargo booked for the date " + cargo.getBookingDate()
                + "\n from the origin " + cargo.getOrigin().getAddress()
                + "\n to the destination " + cargo.getDestination().getAddress()
                + "\n has been REJECTED, due to technical reason. We're sorry for the inconvenience\n "
                + "\n\n\n\nBest regards, \nShipment Control Service Tech Team.";

        return emailService.sendEmail(shipmentControlFromEmail, goodsCompanyEmail, subject, content);
    }

}






