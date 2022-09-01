package com.isdmoldova.shipmentcontrolbackend.repository;

import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Optional<Cargo> findByTrackingNumber(String trackingNumber);

    List<Cargo> findByCargoStatus(CargoStatus cargoStatus);

    //List<Cargo> findByCargoType(CargoType cargoTypes);

   // Itinerary findLeg(Leg leg);




}
