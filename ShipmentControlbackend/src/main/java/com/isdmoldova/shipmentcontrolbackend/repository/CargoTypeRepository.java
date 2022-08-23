package com.isdmoldova.shipmentcontrolbackend.repository;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Repository
public interface CargoTypeRepository extends JpaRepository<CargoType, Long> {
    List<CargoTypeDTO> list = new ArrayList<>();

    CargoTypeDTO addCargoType(CargoType obj);

    Optional<CargoType> findByName(String name);

}
