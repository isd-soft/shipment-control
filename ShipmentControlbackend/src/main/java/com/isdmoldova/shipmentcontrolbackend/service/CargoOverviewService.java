package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoOverviewDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoOverviewCommand;
import java.util.List;

public interface CargoOverviewService {

    List<CargoOverviewDTO> findAll(String username);

    CargoOverviewDTO findById(Long id);

}
