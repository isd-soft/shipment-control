package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.*;
import com.isdmoldova.shipmentcontrolbackend.entity.*;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;

import java.util.List;
import java.util.Optional;

public interface CargoService {

    CargoDTO add(CargoCommand cargoCommand, String username);

    List<CargoDTO> findAllCargoes(String username);

    CargoDTO findById(Long id);

    CargoDTO update(CargoCommand cargoCommand, Long id,String username);

    void delete(Long id, String username);
}
