package com.isdmoldova.shipmentcontrolbackend.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "itinerary")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Itinerary {

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin_point")
    private String originPoint;

    @Column(name = "destination_point")
    private String destinationPoint;

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL)
    private List<Leg> legs;

    @Column(name = "execution_time")
    private LocalDateTime executionTime;


}
