package com.isdmoldova.shipmentcontrolbackend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "transportation_type")
public class TransportType extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name ="cargo_type_id", nullable = false)
    @OneToMany(mappedBy = "transportType", cascade = CascadeType.ALL)
    private List<CargoType> cargoTypeList;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "transportType")
    private List<Route> routeList;







}
