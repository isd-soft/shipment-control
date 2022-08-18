package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.Legs;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;


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

    private Legs legs;

    @Column(name = "execution_time")
    private LocalDateTime executionTime;



}
