package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoTypeCommand;

import java.util.List;

public interface CargoTypeService  {
    List<CargoTypeDTO> findAll();

    CargoTypeDTO add(CargoTypeCommand command);

    CargoTypeDTO findById(Long id);

    CargoTypeDTO update(Long id, CargoTypeCommand cargo);
    void delete(Long id);
}
