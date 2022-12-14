package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoChatMessageLogDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoChatMessageLog;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.mapper.CargoChatMessageLogDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.payload.request.CargoChatMessageLogCommand;
import com.isdmoldova.shipmentcontrolbackend.repository.CargoChatMessageLogRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.CargoRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CargoChatMessageLogServiceImpl implements CargoChatMessageLogService {
    private final UserRepository userRepository;
    private final CargoChatMessageLogRepository cargoChatMessageLogRepository;
    private final CargoRepository cargoRepository;
    private final CargoChatMessageLogDtoMapper cargoChatMessageLogDtoMapper;


    @Override
    public List<CargoChatMessageLogDTO> getAllCargoChatLog(Long cargoId, Principal principal) {

        final Cargo cargo = cargoRepository.findById(cargoId)
                .orElseThrow(() -> new EntityNotFoundException("Cargo not found"));
        final List<CargoChatMessageLog> cargoChatMessageLogs = cargoChatMessageLogRepository.findByCargoOrderByCreatedAtAsc(cargo);

        return cargoChatMessageLogs.stream().map(cargoChatMessageLogDtoMapper::map).collect(Collectors.toList());
    }

    @Override
    public void addCargoChatLog(CargoChatMessageLogCommand cargoChatMessageLogCommand, Principal principal) {

        final User user = userRepository.findUserByUsername(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final Cargo cargo = cargoRepository.findById(cargoChatMessageLogCommand.getCargoId())
                .orElseThrow(() -> new EntityNotFoundException("Cargo not found"));

        final CargoChatMessageLog cargoChatMessageLog = new CargoChatMessageLog();
        cargoChatMessageLog.setMessageText(cargoChatMessageLogCommand.getMessageText());
        cargoChatMessageLog.setCargo(cargo);
        cargoChatMessageLog.setUser(user);


        cargoChatMessageLogRepository.save(cargoChatMessageLog);

    }

    @Override
    public HttpStatus deleteCargoChatLog(Long id, Principal principal) {

        final User user = userRepository.findUserByUsername(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final CargoChatMessageLog cargoChatMessageLog = cargoChatMessageLogRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Cargo Chat log not found"));

        if (cargoChatMessageLog.getUser().getId().equals(user.getId())) {
            cargoChatMessageLogRepository.delete(cargoChatMessageLog);
            return HttpStatus.ACCEPTED;
        }

        return HttpStatus.FORBIDDEN;
    }

    @Override
    public HttpStatus updateCargoChatLog(Long id, CargoChatMessageLogCommand cargoChatMessageLogCommand,
                                   Principal principal) {
        final User user = userRepository.findUserByUsername(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final CargoChatMessageLog cargoChatMessageLog = cargoChatMessageLogRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cargo Chat log not found"));

        if (cargoChatMessageLog.getUser().getId().equals(user.getId())) {
            cargoChatMessageLog.setMessageText(cargoChatMessageLogCommand.getMessageText());
            cargoChatMessageLogRepository.save(cargoChatMessageLog);
            return HttpStatus.ACCEPTED;
        }

        return HttpStatus.FORBIDDEN;

    }
}
