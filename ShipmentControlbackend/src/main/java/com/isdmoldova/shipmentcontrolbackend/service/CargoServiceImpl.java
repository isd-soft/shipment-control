package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.ItineraryDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.LegDTO;
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

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CargoServiceImpl implements CargoService {

    private final CargoTypeRepository cargoTypeRepository;
    private final CargoTypeDtoMapper cargoDtoMapper;
    private final CargoDtoMapper cargoMapper;
    private final CargoRepository cargoRepository;
    private final UserRepository userRepository;


    @Override
    public Optional<CargoDTO> findByTrackingNumber(String trackingNumber) {
        return Optional.empty();
    }


    @Override
    public CargoStatus findByCargoStatus(CargoDTO cargoDTO) {
        return cargoDTO.getCargoStatus();
    }

    @Override
    public List<CargoTypeDTO> findAll() {
        List<CargoType> cargoTypeList  = cargoTypeRepository.findAll();
        return cargoTypeRepository.findAll().stream()
                .map(cargoDtoMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public CargoDTO add(CargoCommand cargoCommand, String username) {
        final User user = userRepository.findUserByUsername(username)
                .orElseThrow(()-> new EntityNotFoundException("User not found"));


        List<CargoType> cargoTypes = cargoCommand.getCargoTypeList().stream()
                .map(cargoTypeId -> cargoTypeRepository.findById(cargoTypeId).orElseThrow(
                        () -> new EntityNotFoundException("Cargo type with id " + cargoTypeId + " not found")))
                .collect(Collectors.toList());


        List<LegCommand> legCommandList = cargoCommand.getItineraryCommand().getLegList();
        List<Leg> legs = legCommandList.stream()
                .map(leg -> new Leg(leg.getCountry(), leg.getCountryCode(), leg.getAddress(), leg.getName()))
                .collect(Collectors.toList());
        Long estimatedAmountTimeShipment = cargoCommand.getItineraryCommand().getEstimatedAmountTimeShipment();
        Itinerary itinerary = new Itinerary(estimatedAmountTimeShipment);
        legs.forEach(itinerary::addLeg);

        final Cargo cargo = new Cargo();
        cargo.setUser(user);
        cargo.setCargoTypes(cargoTypes);
        cargo.setTotalVolume(cargoCommand.getTotalVolume());
        cargo.setTotalWeight(cargo.getTotalWeight());


        cargoRepository.save(cargo);
        cargo.setItinerary(itinerary);
        return cargoMapper.map(cargo);

    }



}






