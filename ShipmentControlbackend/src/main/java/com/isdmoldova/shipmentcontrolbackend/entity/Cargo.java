package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "cargo")
@NoArgsConstructor
@AllArgsConstructor
public class Cargo extends BaseEntity {

    @Column(name = "tracking_number")
    private String trackingNumber;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "total_volume")
    private Double totalVolume;

    @Column(name = "total_weight")
    private Double totalWeight;

    @ManyToMany(mappedBy = "cargos", cascade = {CascadeType.MERGE})
    List<CargoType> cargoTypes = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Itinerary itinerary;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo_status")
    private CargoStatus cargoStatus;

    public Cargo(Double totalVolume,
                 Double totalWeight,
                 List<CargoType> cargoTypes,
                 Itinerary itinerary,
                 CargoStatus cargoStatus) {
        this.totalVolume = totalVolume;
        this.totalWeight = totalWeight;
        this.cargoTypes = cargoTypes;
        this.itinerary = itinerary;
        this.cargoStatus = cargoStatus;
    }

    public void addCargoType(CargoType cargoType) {
        cargoTypes.add(cargoType);
        cargoType.addCargo(this);
    }
}
