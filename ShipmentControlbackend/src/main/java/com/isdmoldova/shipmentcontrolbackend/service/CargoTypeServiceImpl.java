package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.exception.CargoTypeNotFoundException;
//import com.isdmoldova.shipmentcontrolbackend.exception.TransportNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.exception.UserNotAllowedException;
//import com.isdmoldova.shipmentcontrolbackend.exception.UserNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.mapper.CargoTypeDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoTypeCommand;
import com.isdmoldova.shipmentcontrolbackend.repository.CargoTypeRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class CargoTypeServiceImpl implements CargoTypeService {


    private final CargoTypeRepository cargoTypeRepository;
    private final UserRepository userRepository;
    private final CargoTypeDtoMapper mapper;


    @Transactional
    @Override
    public CargoTypeDTO add(CargoTypeCommand command) {
        CargoType cargoType = new CargoType();
        cargoType.setName(command.getName());
        cargoTypeRepository.save(cargoType);
        return mapper.map(cargoType);
    }


    @Transactional
    @Override
    public List<CargoTypeDTO> findAll() {
        List<CargoType> cargoTypeDTOS = cargoTypeRepository.findAll();
        return cargoTypeRepository.findAll().stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CargoTypeDTO findById(Long id) {
        CargoType cargoType = cargoTypeRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("This id does not exists!"));

        return mapper.map(cargoType);
    }

    @Transactional
    @Override
    public CargoTypeDTO update(Long id, CargoTypeCommand cargo) {
        CargoType cargoType = cargoTypeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("This id does not exists!"));
        cargoType.setName(cargo.getName());
        return mapper.map(cargoType);
    }

    @Transactional
    @Override
    public void delete(Long id) {
         cargoTypeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("CargoType entity not found by specified id " + id));
        cargoTypeRepository.deleteById(id);
    }
}
