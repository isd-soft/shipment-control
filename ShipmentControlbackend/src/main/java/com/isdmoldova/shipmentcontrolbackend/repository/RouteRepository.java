package com.isdmoldova.shipmentcontrolbackend.repository;

import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findByOrigin(String origin);

/*    List<Route> findByDestination(String destination);
    @Query("select r from Route r " +
            "join r.transportType tt " +
            "join tt.cargoTypeList ct " +
            "where ct = :cargoType")
    Optional<Route> findRouteByCargoType( CargoType cargoType);*/


}