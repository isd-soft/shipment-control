package com.isdmoldova.shipmentcontrolbackend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "leg")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Leg extends BaseEntity {

    @Column(name = "country_name")
    private String country;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "address")
    private String address;

    @Column
    private String name;

    @ManyToOne (fetch = FetchType.LAZY)
    private Itinerary itinerary;
}