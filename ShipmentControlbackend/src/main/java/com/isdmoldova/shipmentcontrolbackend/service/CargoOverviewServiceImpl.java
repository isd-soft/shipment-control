package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoOverviewDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.exception.CargoOverviewNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.mapper.CargoOverviewDTOMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoOverviewCommand;
import com.isdmoldova.shipmentcontrolbackend.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class CargoOverviewServiceImpl implements CargoOverviewService {

    private final CargoRepository cargoRepository;
    private final CargoOverviewDTOMapper cargoOverviewDTOMapper;

    @Transactional
    @Override
    public List<CargoOverviewDTO> findAll(){
        return cargoRepository.findAll().
                stream()
                .map(cargoOverviewDTOMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CargoOverviewDTO add(CargoOverviewCommand command){
        Cargo cargoOverviewDTO = new Cargo();
        cargoOverviewDTO.setCargoStatus(command.getCargoStatus());
        cargoOverviewDTO.setTrackingNumber(command.getTrackingNumber());
        cargoRepository.save(cargoOverviewDTO);
        return cargoOverviewDTOMapper.map(cargoOverviewDTO);
    }

    @Transactional
    @Override
    public CargoOverviewDTO update(Long id, CargoOverviewCommand cargo){

        Cargo cargoOverview = cargoRepository.findById(id).orElseThrow(() ->
                new CargoOverviewNotFoundException("This id does not exists!"));
        cargoOverview.setCargoStatus(cargo.getCargoStatus());
        cargoOverview.setTrackingNumber(cargo.getTrackingNumber());

        return cargoOverviewDTOMapper.map(cargoOverview);
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
