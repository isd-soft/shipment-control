package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "route")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Route extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "route")
    private Itinerary itinerary;

    @Column(name = "detail_route")
    private String detailedRouteDescription;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route", cascade = CascadeType.ALL)
    private List<Transport> transports;

}
