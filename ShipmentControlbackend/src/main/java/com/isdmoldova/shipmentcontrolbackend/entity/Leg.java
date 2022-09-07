package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CurrencyEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "leg")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Leg extends BaseEntity {

    @Column(name = "country_name")
    private String country;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "address")
    private String address;

    @Column
    private String name;

    @Column(name = "price")
    private Double price = 0.0D;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private CurrencyEnum currency;

    @ManyToOne (fetch = FetchType.LAZY)
    private Itinerary itinerary;

    public Leg(String country, String countryCode, String address, String name, Double price) {
        this.country = country;
        this.countryCode = countryCode;
        this.address = address;
        this.name = name;
        this.price=price;
    }
}
