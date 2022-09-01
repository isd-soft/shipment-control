package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.ItineraryDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.LegDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.*;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoCommand;

import java.util.List;
import java.util.Optional;

public interface CargoService {

    Optional<CargoDTO> findByTrackingNumber(String trackingNumber);

    CargoStatus findByCargoStatus(CargoDTO cargoDTO);

    List<CargoTypeDTO> findAll();

    CargoDTO add(CargoCommand cargoCommand, String username);




}
