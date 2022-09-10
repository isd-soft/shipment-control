package com.isdmoldova.shipmentcontrolbackend.entity;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToMany
//    @JoinColumn(name = "route_id")
    private List<Route> routes;

    @Enumerated(EnumType.STRING)
    private TransportType transportType;

    @ManyToMany
    private List<CargoType> cargoTypes;


}
