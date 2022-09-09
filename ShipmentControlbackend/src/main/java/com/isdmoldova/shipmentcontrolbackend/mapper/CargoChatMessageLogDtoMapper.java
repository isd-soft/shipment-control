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
        cargoChatMessageLogDTO.setMessageFrom(cargoChatMessageLog.getUser().getCompanyName());
        cargoChatMessageLogDTO.setSenderRole(cargoChatMessageLog.getUser().getRole().name());

        return cargoChatMessageLogDTO;
    }

}
