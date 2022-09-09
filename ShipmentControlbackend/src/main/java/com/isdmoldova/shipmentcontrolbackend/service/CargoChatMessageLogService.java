package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoChatMessageLogDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoChatMessageLogCommand;

import java.security.Principal;
import java.util.List;

public interface CargoChatMessageLogService {

    List<CargoChatMessageLogDTO> getAllCargoChatLog(Long cargoId, Principal principal);

    void addCargoChatLog(CargoChatMessageLogCommand cargoChatMessageLogCommand, Principal principal);

    void deleteCargoChatLog(Long id, Principal principal);

    void updateCargoChatLog(CargoChatMessageLogCommand cargoChatMessageLogCommand,Principal principal);


}
