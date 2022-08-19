package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import java.util.List;


public interface TransportService {

    TransportDTO add(Transport transport);

    TransportDTO update(Transport id);

    TransportDTO findById(Long id);

    TransportDTO findUserById(Long id);

    List<TransportDTO> findAll();

    TransportDTO delete(Long id);
}
