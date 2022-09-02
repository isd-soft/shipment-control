package com.isdmoldova.shipmentcontrolbackend.repository;

import com.isdmoldova.shipmentcontrolbackend.entity.Itinerary;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary , Long> {

    Optional<Itinerary> findItineraryById(Long id);

    Optional<Itinerary> findByRoute(Route route);

}
