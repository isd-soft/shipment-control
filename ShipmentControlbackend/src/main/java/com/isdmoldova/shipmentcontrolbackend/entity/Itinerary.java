package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.Legs;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "itinerary")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Itinerary extends BaseEntity {

    private Legs legs;

    @Column(name = "execution_time")
    private LocalDateTime executionTime;



}
