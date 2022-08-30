package com.isdmoldova.shipmentcontrolbackend.email.service;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.BookingRequest;
import com.isdmoldova.shipmentcontrolbackend.repository.BookingRequestsRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final UserRepository userRepository;
    private final BookingRequestsRepository bookingRequestsRepository;

    @Override
    @Transactional
    public String sendBookingRequest(BookingRequestsDTO bookingRequestsDTO, Principal principal) {

        if (bookingRequestsDTO != null) {
            try {
                String shipmentCompanyEmail = userRepository
                        .findEmailByCompanyName(bookingRequestsDTO.getShipmentCompanyName());
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("shipmentcontrol2022@gmail.com");
                message.setTo(shipmentCompanyEmail);
                message.setSubject("Availability of a day request.");
                message.setText("Dear " + bookingRequestsDTO.getShipmentCompanyName() + ","
                        + " your route:\nRoute description: " + bookingRequestsDTO.getRouteDescription()
                        + " \nhas been requested to see the availability of the day: "
                        + bookingRequestsDTO.getLocalDateRequested() + ","
                        + bookingRequestsDTO.getLocalDateRequested().getDayOfWeek()
                        + ", from the Goods Company named:" + bookingRequestsDTO.getGoodsCompanyName()
                        + "\n\n\n\nBest regards, \nShipment Control Service Tech Team.");
                javaMailSender.send(message);
                return "Email sent successfully!";
            }
            // Catch block to handle the exceptions
            catch (Exception e) {
                return "Error while Sending Mail";
            }
        } else {
            return "Error saving your request!";
        }
    }

    @Override
    public String sendWhenRequestAccept(Principal principal, Long id) {
        try {
            BookingRequest request = bookingRequestsRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Request with " + id + " not found"));
            String goodsCompanyEmail = request.getUser().getEmail();

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("shipmentcontrol2022@gmail.com");
            message.setTo(goodsCompanyEmail);
            message.setSubject("Request available response.");
            message.setText("\nHello, your request on "
                    + request.getLocalDateRequested() + ", "
                    + request.getLocalDateRequested().getDayOfWeek() + ", "
                    + " with the:\nRoute description: " + request.getRoute().getDetailedRouteDescription()
                    + "\nShipment Company: " + request.getRoute().getUser().getCompanyName()
                    + " \n\nHas been APPROVED! \nClick on the link below to follow on the requested route.\n "
                    + "http://localhost:4200/dashboard/route/details?routeId=" + request.getRoute().getId()
                    + "\n\n\n\nBest regards, \nShipment Control Service Tech Team.");
            javaMailSender.send(message);
            return "Email sent successfully!";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    @Override
    public String sendWhenRequestDeny(Principal principal, Long id) {
        try {
            BookingRequest request = bookingRequestsRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Request with " + id + " not found"));
            String goodsCompanyEmail = request.getUser().getEmail();

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("shipmentcontrol2022@gmail.com");
            message.setTo(goodsCompanyEmail);
            message.setSubject("Request available response.");
            message.setText("\nHello, your request on "
                    + request.getLocalDateRequested() + ", "
                    + request.getLocalDateRequested().getDayOfWeek() + ", "
                    + " with the:\nRoute description: " + request.getRoute().getDetailedRouteDescription()
                    + "\nShipment Company: " + request.getRoute().getUser().getCompanyName()
                    + " \n\nHas been DENIED! \n We are sorry for inconvenience."
                    + "\n\n\n\nBest regards, \nShipment Control Service Tech Team.");
            javaMailSender.send(message);
            return "Email sent successfully!";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
