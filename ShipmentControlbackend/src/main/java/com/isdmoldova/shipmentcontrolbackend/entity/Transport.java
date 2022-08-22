package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    @ManyToOne
    private Route route;

    @Enumerated(EnumType.STRING)
    private TransportType transportType;

    @ElementCollection(targetClass = CargoType.class)
    @JoinTable(name = "transport_cargo_type", joinColumns = @JoinColumn(name = "transport_id"))
    @Column(name = "cargo_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<CargoType> cargoTypes;
}
