package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cargo")
@NoArgsConstructor
@AllArgsConstructor
public class Cargo extends BaseEntity {

    @OneToOne
    private Leg currentLeg;


    @Column(name = "tracking_number")
    private String trackingNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

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



    public Leg getDestination() {
        return itinerary.getDestination();
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public Leg getOrigin() {
        return itinerary.getOrigin();
    }





}
