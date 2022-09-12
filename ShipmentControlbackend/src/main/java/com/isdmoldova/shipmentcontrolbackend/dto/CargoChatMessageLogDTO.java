package com.isdmoldova.shipmentcontrolbackend.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CargoChatMessageLogDTO implements Serializable {
    private Long chatId;
    private String messageText;
    private LocalDateTime createdAt;
    private String messageFrom;
    private String senderRole;


}
