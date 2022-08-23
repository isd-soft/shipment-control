package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cargo")
@NoArgsConstructor
@AllArgsConstructor
public class Cargo extends BaseEntity{

    @Column(name = "tracking_number")
    private String trackingNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @Column(name = "total_volume")
    private Double totalVolume;

    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo_status")
    private CargoStatus cargoStatus;


}
