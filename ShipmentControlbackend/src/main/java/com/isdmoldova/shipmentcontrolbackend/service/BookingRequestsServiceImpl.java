package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.BookingRequests;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.mapper.BookingRequestsDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.BookingRequestsCommand;
import com.isdmoldova.shipmentcontrolbackend.repository.BookingRequestsRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.RouteRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingRequestsServiceImpl implements BookingRequestsService {

    private final BookingRequestsRepository bookingRequestsRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;

    @Override
    public List<BookingRequestsDTO> getAllByUser(User user) {
        return null;
    }

    @Override
    @Transactional
    public void add(BookingRequestsCommand bookingRequestsCommand, String username) {
        final User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        final Route route = routeRepository.findById(bookingRequestsCommand.getRouteId())
                .orElseThrow(() -> new EntityNotFoundException("Route not found"));

        final BookingRequests bookingRequests = new BookingRequests();
        bookingRequests.setUser(user);
        bookingRequests.setRoute(route);
        bookingRequests.setLocalDateRequested(bookingRequestsCommand.getLocalDateRequested());
        bookingRequestsRepository.save(bookingRequests);
    }
}
