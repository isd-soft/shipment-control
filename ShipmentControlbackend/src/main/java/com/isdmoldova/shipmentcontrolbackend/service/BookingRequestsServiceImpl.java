package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.BookingRequest;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingRequestsServiceImpl implements BookingRequestsService {

    private final BookingRequestsRepository bookingRequestsRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;
    private final BookingRequestsDtoMapper bookingRequestsDtoMapper;

    @Override
    @Transactional
    public BookingRequestsDTO add(BookingRequestsCommand bookingRequestsCommand, String username) {
        final User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        final Route route = routeRepository.findById(bookingRequestsCommand.getRouteId())
                .orElseThrow(() -> new EntityNotFoundException("Route not found"));

        final BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setUser(user);
        bookingRequest.setRoute(route);
        bookingRequest.setLocalDateRequested(bookingRequestsCommand.getLocalDateRequested());
        bookingRequestsRepository.save(bookingRequest);

        return bookingRequestsDtoMapper.map(bookingRequest);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        bookingRequestsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("BookingRequests entity not found by specified id " + id));
        bookingRequestsRepository.deleteById(id);
    }

    @Override
    public List<BookingRequestsDTO> getAllRequests(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("User for " + username + " not found"));
        final List<BookingRequest> bookingRequests = bookingRequestsRepository
                .findAllBookingRequestsForUser(user);
        return bookingRequests.stream().map(bookingRequestsDtoMapper::map).collect(Collectors.toList());
    }
}
