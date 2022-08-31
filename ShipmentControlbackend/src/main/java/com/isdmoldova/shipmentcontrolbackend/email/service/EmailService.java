package com.isdmoldova.shipmentcontrolbackend.email.service;

public interface EmailService {

    String sendEmail(String from, String to, String subject, String content);
}
