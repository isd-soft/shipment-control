package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import com.isdmoldova.shipmentcontrolbackend.payload.request.TransportCommand;

import java.util.List;


public interface TransportService {

    TransportDTO add(TransportCommand transport);

    TransportDTO update(Long id , TransportCommand transportCommand);

    TransportDTO findById(Long id);

    TransportDTO findUserById(Long id);

    List<TransportDTO> findAllTransport(Transport transport);

    void delete(Long id);
}
