package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoOverviewDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoOverviewCommand;
import java.util.List;

public interface CargoOverviewService {

    List<CargoOverviewDTO> findAll();

    CargoOverviewDTO add(CargoOverviewCommand command);

    CargoOverviewDTO update(Long id, CargoOverviewCommand cargo);

    CargoOverviewDTO findById(Long id);

    void delete(Long id);
}
