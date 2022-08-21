package com.isdmoldova.shipmentcontrolbackend.repository;

import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
    Optional<Transport> findTransportById(Long id);

    List<Transport> findAllByUser(User user);
}
