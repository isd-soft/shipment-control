package com.isdmoldova.shipmentcontrolbackend.repository;

import com.isdmoldova.shipmentcontrolbackend.entity.BookingRequests;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRequestsRepository extends JpaRepository<BookingRequests, Long> {


    Optional<BookingRequests> findById(Long aLong);


    List<BookingRequests> findAll();


   // void save(BookingRequests bookingRequests);

    List<BookingRequestsRepository> findAllByUser(User user);


    List<BookingRequestsRepository> findAllByRoute(Route route);
}
