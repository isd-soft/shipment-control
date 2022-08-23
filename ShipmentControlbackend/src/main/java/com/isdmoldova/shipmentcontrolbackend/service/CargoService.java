package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import java.util.List;
import java.util.Optional;

public interface CargoService {

    Optional<Cargo> findByTrackingNumber(String trackingNumber);

    Optional<Cargo> findByRoute(Route route);

    List<Cargo> findByCargoStatus(CargoStatus cargoStatus);


}
