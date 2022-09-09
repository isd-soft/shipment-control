package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoChatMessageLogDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoChatMessageLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CargoChatMessageLogDtoMapper {

    public CargoChatMessageLogDTO map(CargoChatMessageLog cargoChatMessageLog) {

        CargoChatMessageLogDTO cargoChatMessageLogDTO = new CargoChatMessageLogDTO();

        cargoChatMessageLogDTO.setChatId(cargoChatMessageLog.getId());
        cargoChatMessageLogDTO.setMessageText(cargoChatMessageLog.getMessageText());
        cargoChatMessageLogDTO.setCreatedAt(cargoChatMessageLog.getCreatedAt());
        cargoChatMessageLogDTO.setGoodsCompanyName(cargoChatMessageLog.getCargo().getUser().getCompanyName());
        cargoChatMessageLogDTO.setShipmentCompanyName(cargoChatMessageLog.getCargo().getProvider().getCompanyName());
        cargoChatMessageLogDTO.setMessageFrom(cargoChatMessageLog.getMessageSender());

        return cargoChatMessageLogDTO;
    }

}
