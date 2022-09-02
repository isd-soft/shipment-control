package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.*;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoCommand;
import java.util.List;

public interface CargoService {

    CargoDTO add(CargoCommand cargoCommand, String username);

    List<CargoDTO> findAllCargoes(String username);

    CargoDTO findById(Long id);


    CargoDTO update(CargoCommand cargoCommand, Long id,String username);


    void delete(Long id, String username);

}
