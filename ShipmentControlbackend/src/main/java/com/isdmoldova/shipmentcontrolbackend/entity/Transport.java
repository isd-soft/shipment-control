package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportType;
import com.isdmoldova.shipmentcontrolbackend.mapper.CargoTypeDtoMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transport")
@Getter
@Setter
@NoArgsConstructor
public class Transport extends BaseEntity {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private List<Route> routes;

    @Enumerated(EnumType.STRING)
    private TransportType transportType;

    @ManyToMany
    private List<CargoType> cargoTypes;


}
