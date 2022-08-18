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

    @Column
    private String name;

    @Column
    private String address;

    @ManyToOne (fetch = FetchType.LAZY)
    private Itinerary itinerary;
}