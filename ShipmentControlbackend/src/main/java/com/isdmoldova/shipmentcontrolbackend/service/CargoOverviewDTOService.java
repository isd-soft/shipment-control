package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoOverviewDTO;

import java.util.List;

public interface CargoOverviewDTOService {

    List<CargoOverviewDTO> findAll();
}
