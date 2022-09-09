package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoChatMessageLogDTO;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoChatMessageLogCommand;
import org.springframework.http.HttpStatus;

import java.security.Principal;
import java.util.List;

public interface CargoChatMessageLogService {

    List<CargoChatMessageLogDTO> getAllCargoChatLog(Long cargoId, Principal principal);

    void addCargoChatLog(CargoChatMessageLogCommand cargoChatMessageLogCommand, Principal principal);

    HttpStatus deleteCargoChatLog(Long id, Principal principal);

    HttpStatus updateCargoChatLog(Long id, CargoChatMessageLogCommand cargoChatMessageLogCommand,Principal principal);


}
