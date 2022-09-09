package com.isdmoldova.shipmentcontrolbackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CargoChatMessageLogDTO {
    private Long chatId;
    private String messageText;
    private String goodsCompanyName;
    private String shipmentCompanyName;
    private LocalDateTime createdAt;
    private String messageFrom;


}
