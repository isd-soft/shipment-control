package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "route")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Route extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "route", cascade = CascadeType.ALL,orphanRemoval = true)
    private Itinerary itinerary;

    @Column(name = "detail_route")
    private String detailedRouteDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ElementCollection(targetClass = AvailableDaysRent.class)
    @JoinTable(name = "route_available_days", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "available_day", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<AvailableDaysRent> availableDaysRent;

    @Column(name = "max_weight")
    private Double maximalLoadValue;

    @Column(name = "max_volume")
    private Double maxLoadVolume;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "route")
    private List<Transport> transports = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route")
    private List<BookingRequest> bookingRequests = new ArrayList<>();

    public Route(String detailedRouteDescription,
                 User user,
                 List<AvailableDaysRent> availableDaysRent,
                 Double maximalLoadValue,
                 Double maxLoadVolume) {
        this.detailedRouteDescription = detailedRouteDescription;
        this.user = user;
        this.availableDaysRent = availableDaysRent;
        this.maximalLoadValue = maximalLoadValue;
        this.maxLoadVolume = maxLoadVolume;
        this.transports = new ArrayList<>();
    }

    public void addItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
        itinerary.setRoute(this);
    }

    public void addTransport(Transport transport) {
        transports.add(transport);
        transport.setRoute(this);
    }

    public void clearTransports() {
        transports.clear();
    }

    public List<CargoType> getAllowedCargoTypes() {
        return this.transports.stream()
                .map(Transport::getCargoTypes)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

}
