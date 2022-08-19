package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transport")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transport extends BaseEntity{

    @ManyToOne
    private Route route;

/*    @ElementCollection(targetClass = TransportType.class)
    @JoinTable(name = "transport_type", joinColumns = @JoinColumn(name = "transport_id"))
    @Column(name = "transport_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransportType transportType;*/


    /*@ElementCollection(targetClass = TransportType.class)
    @JoinTable(name = "cargo_type", joinColumns = @JoinColumn(name = "transport_id"))
    @Column(name = "cargo_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CargoType cargoType;*/




}
