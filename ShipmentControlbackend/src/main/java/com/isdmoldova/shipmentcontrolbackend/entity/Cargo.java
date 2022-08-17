package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "cargo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Cargo extends BaseEntity{

    @Column(name = "tracking_number")
    private String trackingNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @Column(name = "total_volume")
    private Double totalVolume;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo_type")
    private CargoType cargoType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transportation_type")
    private TransportationType transportationType;

    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo_status")
    private CargoStatus cargoStatus;


}
