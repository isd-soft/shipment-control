package com.isdmoldova.shipmentcontrolbackend.repository;

import com.isdmoldova.shipmentcontrolbackend.entity.BookingRequest;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRequestsRepository extends JpaRepository<BookingRequest, Long> {

    @Query(value = "select br from BookingRequest br " +
            "join br.route r " +
            "join r.user u " +
            "where u = :user ")
    List<BookingRequest> findAllBookingRequestsForUser(@Param("user") User user);

}
