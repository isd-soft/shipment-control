package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import com.isdmoldova.shipmentcontrolbackend.mapper.TransportDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.repository.TransportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransportServiceImpl implements TransportService{

    private final TransportRepository transportRepository;
    private final TransportDtoMapper transportDtoMapper;

    @Override
    public TransportDTO add(Transport transport){
        final Transport transports = transportRepository.add(transport);
        return transportDtoMapper.map(transports);
    }

    @Override
    public TransportDTO update(Transport id){
        final  Transport transports = transportRepository.update(id);
        return transportDtoMapper.map(transports);
    }

    @Override
    public TransportDTO findById(Long id){
        final Transport transports = transportRepository.findById(id).orElseThrow(()-> new NullPointerException("Id not found" ));
        return transportDtoMapper.map(transports);
    }

     @Override
    public TransportDTO findUserById(Long id){
        final Transport transports = transportRepository.findUserById(id).orElseThrow(() -> new NullPointerException("User with specified id not found"));
        return transportDtoMapper.map(transports);
     }

    @Override
    public List<TransportDTO> findAll(){
        final List<Transport> transports = transportRepository.findAll();
        return transports.stream().map(transportDtoMapper::map).collect(Collectors.toList());

    }

    @Override
    public TransportDTO delete(Long id){
        final Transport transport = transportRepository.delete(id);
        return transportDtoMapper.map(transport);
    }


}
