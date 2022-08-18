package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "route")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Route extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Itinerary itinerary;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo_type")
    private CargoType cargoType;

    @Column(name = "detail_route")
    private String detailedRouteDescription;

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_type_id")
    private TransportType transportType;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "route",orphanRemoval = true)
    private List<Cargo> cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "estimated_route_days")
    private String estimatedDays;

    private String origin;

    private String destination;

    @ElementCollection(targetClass = AvailableDaysRent.class)
    @JoinTable(name = "route_available_days", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "available_day", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<AvailableDaysRent> availableDaysRent;

    @Column(name = "estimated_time_amount",columnDefinition = "TIME")
    private LocalTime estimatedAmountTimeShipment;

    @Column(name = "max_weight")
    private Double maximalLoadValue;


}
