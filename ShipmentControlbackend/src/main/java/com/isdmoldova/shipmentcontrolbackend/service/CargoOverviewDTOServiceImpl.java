package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoOverviewDTO;
import com.isdmoldova.shipmentcontrolbackend.mapper.CargoOverviewDTOMapper;
import com.isdmoldova.shipmentcontrolbackend.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class CargoOverviewDTOServiceImpl implements CargoOverviewDTOService{

    private CargoRepository cargoRepository;
    private CargoOverviewDTOMapper cargoOverviewDTOMapper;

    @Override
    public List<CargoOverviewDTO> findAll(){
        return cargoRepository.findAll().
                stream()
                .map(cargoOverviewDTOMapper::map)
                .collect(Collectors.toList());
    }
}
