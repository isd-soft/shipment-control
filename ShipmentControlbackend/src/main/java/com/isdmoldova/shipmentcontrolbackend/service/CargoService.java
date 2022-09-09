package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.*;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoCommand;

import java.security.Principal;
import java.util.List;

public interface CargoService {

    CargoDTO add(CargoCommand cargoCommand, String username);

    List<CargoDTO> findAllCargoes(String username);
    List<CargoDTO> findAllCargoesByProvider(String username);

    CargoDTO findById(Long id);

    void delete(Long id, String username);

    String sendWhenCargoApproved(Principal principal, Long id);

    String sendWhenCargoRejected(Principal principal, Long id);

}
