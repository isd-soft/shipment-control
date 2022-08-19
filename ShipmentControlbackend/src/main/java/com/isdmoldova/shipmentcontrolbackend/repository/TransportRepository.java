package com.isdmoldova.shipmentcontrolbackend.repository;

import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TransportRepository extends JpaRepository<Transport , Long> {

    Transport add( Transport transport);

    Transport update(Transport id);

    Optional<Transport> findById(Long id);

    Optional<Transport> findUserById(Long id);

    Optional<Transport> findAll(Transport transport);

    Transport delete(Long id);
}
