package com.isdmoldova.shipmentcontrolbackend.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "itinerary")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Itinerary extends BaseEntity{

    @OneToOne(fetch = FetchType.LAZY)
    private Route route;

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Leg> legs = new ArrayList<>();

    @Column(name = "days_of_execution")
    private Long daysOfExecution;

    public Leg getOrigin() {
        return legs.get(0);
    }

    public Leg getDestination() {
        return legs.get(legs.size() - 1);
    }

    public Itinerary(Long daysOfExecution) {
        this.daysOfExecution = daysOfExecution;
    }

    public void addLeg(Leg leg) {
        legs.add(leg);
        leg.setItinerary(this);
    }

    public void setLegs(Leg newLeg) {
        newLeg.setItinerary(this);
    }
}
