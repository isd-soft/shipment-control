package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportationType;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Route {

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "route",orphanRemoval = true)
    private List<Cargo> cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private Itinerary itinerary;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transportation_type")
    private TransportationType transportationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo_type")
    private CargoType cargoType;

    @Column(name = "detail_route")
    private String detailedRouteDescription;

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
