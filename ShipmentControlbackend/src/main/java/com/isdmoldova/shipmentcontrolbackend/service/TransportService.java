package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.TransportCommand;

import java.util.List;


public interface TransportService {
    TransportDTO add(TransportCommand transport, String username);

    TransportDTO findTransportByIdAndUsername(Long id, String username);

    List<TransportDTO> findAllTransport(String username);

    TransportDTO update(Long id, TransportCommand command, String username);

    void delete(Long id, String username);

//    List<TransportDTO> findAllTransportByUser(String username);
}
