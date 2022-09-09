package com.isdmoldova.shipmentcontrolbackend.repository;

import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoChatMessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CargoChatMessageLogRepository extends JpaRepository<CargoChatMessageLog,Long> {


    List<CargoChatMessageLog> findByCargo(Cargo cargo);
}
