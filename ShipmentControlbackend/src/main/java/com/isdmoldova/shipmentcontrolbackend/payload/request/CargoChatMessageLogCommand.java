package com.isdmoldova.shipmentcontrolbackend.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoChatMessageLogCommand {

    private Long cargoId;
    private String messageText;
}
