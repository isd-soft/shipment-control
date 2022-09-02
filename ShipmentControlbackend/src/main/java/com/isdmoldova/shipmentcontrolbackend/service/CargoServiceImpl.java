package com.isdmoldova.shipmentcontrolbackend.service;

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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .map(leg -> new Leg(leg.getCountry(), leg.getCountryCode(), leg.getAddress(), leg.getName()))
                .collect(Collectors.toList());
        Long estimatedAmountTimeShipment = cargoCommand.getItineraryCommand().getEstimatedAmountTimeShipment();
        Itinerary itinerary = new Itinerary(estimatedAmountTimeShipment);
        legs.forEach(itinerary::addLeg);

        final Cargo cargo = new Cargo();
        cargo.setUser(user);
        cargoTypes.forEach(cargo::addCargoType);
        cargo.setTotalVolume(cargoCommand.getTotalVolume());
        cargo.setTotalWeight(cargoCommand.getTotalWeight());
        cargo.setBookingDate(cargoCommand.getBookingDate());

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

    @Override
    public void delete(Long id, String username) {
        Cargo cargo = cargoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cargo not found with id " + id));
        cargo.getCargoTypes().forEach(cargoType -> cargoType.removeCargo(cargo));
        if (!cargo.getUser().getUsername().equals(username)) {
            throw new EntityNotFoundException("User " + username + " is not allowed to delete route with id " + id);
        }
        cargoRepository.delete(cargo);
    }


}






