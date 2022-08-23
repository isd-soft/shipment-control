package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService {

    private  final CargoRepository cargoRepository;

    @Autowired
    public CargoServiceImpl(final CargoRepository cargoRepository){
        this.cargoRepository = cargoRepository;
    }

    @Override
   public Optional<Cargo> findByTrackingNumber(String trackingNumber){
        return  cargoRepository.findByTrackingNumber(trackingNumber);
    }

    @Override
    public Optional<Cargo> findByRoute(Route route){
        return cargoRepository.findByRoute(route);
    }

    @Override
    public List<Cargo> findByCargoStatus(CargoStatus cargoStatus){
        return  cargoRepository.findByCargoStatus(cargoStatus);
    }

    @Override
    public List<Cargo> findAll(){
        return cargoRepository.findAll();
    }

}
