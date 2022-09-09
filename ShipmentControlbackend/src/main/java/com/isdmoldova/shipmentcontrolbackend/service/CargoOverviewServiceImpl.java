package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.CargoOverviewDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.UserRole;
import com.isdmoldova.shipmentcontrolbackend.exception.CargoOverviewNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.exception.CargoTypeNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.mapper.CargoOverviewDTOMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoOverviewCommand;
import com.isdmoldova.shipmentcontrolbackend.repository.CargoRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class CargoOverviewServiceImpl implements CargoOverviewService {

    private final CargoRepository cargoRepository;
    private final CargoOverviewDTOMapper cargoOverviewDTOMapper;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CargoOverviewDTO> findAll(){

        return cargoRepository.findAll()
                .stream()
                .map(cargoOverviewDTOMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CargoOverviewDTO> findAllCargoes(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("Cargoes for user " + username + " not found"));
        if (user.getRole() == UserRole.GOODS_COMPANY)
            return cargoRepository.findAllByUser(user).
                    stream()
                    .map(cargoOverviewDTOMapper::map)
                    .collect(Collectors.toList());
        return cargoRepository.findAllByProvider(user)
                .stream()
                .map(cargoOverviewDTOMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CargoOverviewDTO add(CargoOverviewCommand cargoOverviewCommand){
        Cargo cargo = new Cargo();
        cargo.setCargoStatus(cargoOverviewCommand.getCargoStatus());
       // cargoOverviewDTO.setDestination(command.getDestination());
        cargo.setTrackingNumber(cargoOverviewCommand.getTrackingNumber());
        cargoRepository.save(cargo);
        return cargoOverviewDTOMapper.map(cargo);
    }

    @Transactional
    @Override
    public CargoOverviewDTO update(Long id, CargoOverviewCommand cargoOverviewCommand){

        Cargo cargo = cargoRepository.findById(id).orElseThrow(() ->
                new CargoOverviewNotFoundException("This id does not exists!"));
        cargo.setCargoStatus(cargoOverviewCommand.getCargoStatus());
      //  cargoOverview.setDestination(cargo.getDestination());
        cargo.setTrackingNumber(cargoOverviewCommand.getTrackingNumber());

        return cargoOverviewDTOMapper.map(cargo);
    }

    @Transactional
    @Override
    public void delete(Long id){
        cargoRepository.findById(id).orElseThrow(
                () -> new CargoOverviewNotFoundException("Cargo entity not found by specified id " + id));
        cargoRepository.deleteById(id);
    }


    @Transactional
    @Override
    public CargoOverviewDTO findById(Long id) {
         Cargo cargoOverview = cargoRepository.findById(id).orElseThrow(
                 ()-> new CargoOverviewNotFoundException("This id does not exists!"));
        return cargoOverviewDTOMapper.map(cargoOverview);
    }
}
