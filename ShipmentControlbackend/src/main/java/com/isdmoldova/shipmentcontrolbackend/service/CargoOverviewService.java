package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoOverviewDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoOverviewCommand;
import java.util.List;

public interface CargoOverviewService {

    List<CargoOverviewDTO> findAll(String username);
    List<CargoOverviewDTO> findAll();
    List<CargoOverviewDTO> findAllCargoes(String username);

    CargoOverviewDTO add(CargoOverviewCommand command);

    CargoOverviewDTO update(Long id, CargoOverviewCommand cargo);

    CargoOverviewDTO findById(Long id);

}
