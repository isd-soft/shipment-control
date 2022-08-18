package com.isdmoldova.shipmentcontrolbackend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "transportation_type")
public class TransportType extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "transportType")
    private List<Route> routeList;







}
