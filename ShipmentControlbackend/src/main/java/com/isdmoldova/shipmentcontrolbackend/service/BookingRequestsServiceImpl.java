package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.email.service.EmailService;
import com.isdmoldova.shipmentcontrolbackend.entity.BookingRequest;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.mapper.BookingRequestsDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.BookingRequestsCommand;
import com.isdmoldova.shipmentcontrolbackend.repository.BookingRequestsRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.RouteRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingRequestsServiceImpl implements BookingRequestsService {

    private final BookingRequestsRepository bookingRequestsRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;
    private final BookingRequestsDtoMapper bookingRequestsDtoMapper;

    private final EmailService emailService;

    @Value("com.isdmoldova.shipment.control.from.email")
    private String shipmentControlFromEmail;

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

    @Override
    @Transactional
    public String sendBookingRequest(BookingRequestsDTO bookingRequestsDTO, Principal principal) {

        if (bookingRequestsDTO != null) {
            String shipmentCompanyEmail = userRepository
                    .findEmailByCompanyName(bookingRequestsDTO.getShipmentCompanyName());
            String subject = "Availability of a day request.";
            String content = "Dear " + bookingRequestsDTO.getShipmentCompanyName() + ","
                    + " your route:\n- Route description: " + bookingRequestsDTO.getRouteDescription()
                    + " \nhas been requested to see the availability of the day: "
                    + bookingRequestsDTO.getLocalDateRequested() + ", "
                    + bookingRequestsDTO.getLocalDateRequested().getDayOfWeek()
                    + ", from the \nGoods Company: " + bookingRequestsDTO.getGoodsCompanyName()
                    + "\n\n\n\nBest regards, \nShipment Control Service Tech Team.";

            return emailService.sendEmail(shipmentControlFromEmail, shipmentCompanyEmail, subject, content);
        } else {
            return "Error saving your request!";
        }
    }

    @Override
    public String sendWhenRequestAccept(Principal principal, Long id) {

        BookingRequest request = bookingRequestsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Request with " + id + " not found"));
        String goodsCompanyEmail = request.getUser().getEmail();
        String subject = "Availability request - Accept.";
        String content = "\nHello, your request on "
                + request.getLocalDateRequested() + ", "
                + request.getLocalDateRequested().getDayOfWeek() + ", "
                + " with the following:\n- Route description: " + request.getRoute().getDetailedRouteDescription()
                + "\n- Shipment Company: " + request.getRoute().getUser().getCompanyName()
                + " \n\nHas been APPROVED! \n\nClick on the link below to follow on the requested route.\n "
                + "http://localhost:4200/dashboard/route/details?routeId=" + request.getRoute().getId()
                + "\n\n\n\nBest regards, \nShipment Control Service Tech Team.";
        return emailService.sendEmail(shipmentControlFromEmail, goodsCompanyEmail, subject, content);
    }

    @Override
    public String sendWhenRequestDeny(Principal principal, Long id) {

        BookingRequest request = bookingRequestsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Request with " + id + " not found"));
        String goodsCompanyEmail = request.getUser().getEmail();
        String subject = "Availability request - Deny.";
        String content = "\nHello, your request on "
                + request.getLocalDateRequested() + ", "
                + request.getLocalDateRequested().getDayOfWeek() + ", "
                + " with the following:\n- Route description: " + request.getRoute().getDetailedRouteDescription()
                + "\n- Shipment Company: " + request.getRoute().getUser().getCompanyName()
                + " \n\nHas been DENIED! \n\nWe are sorry for inconvenience."
                + "\n\n\n\nBest regards, \nShipment Control Service Tech Team.";

        return emailService.sendEmail(shipmentControlFromEmail, goodsCompanyEmail, subject, content);

    }
}
