package com.isdmoldova.shipmentcontrolbackend.email.service;

import com.isdmoldova.shipmentcontrolbackend.dto.BookingRequestsDTO;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final UserRepository userRepository;

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
                        + " your route with id: " + bookingRequestsDTO.getRouteId()
                        + " has been requested to see the availability of the day: "
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
}
