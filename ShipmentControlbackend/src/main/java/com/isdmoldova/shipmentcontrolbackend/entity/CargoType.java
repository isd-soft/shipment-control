package com.isdmoldova.shipmentcontrolbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cargo_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CargoType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne (fetch = FetchType.LAZY)
    private TransportType transportType;

    @Column (name = "max_weight")
    private Double maxWeight;

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cargo> cargoList;


}