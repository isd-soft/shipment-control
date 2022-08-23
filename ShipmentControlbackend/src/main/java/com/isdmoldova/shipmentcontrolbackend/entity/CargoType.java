package com.isdmoldova.shipmentcontrolbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cargo_type")
public class CargoType extends BaseEntity {
    @Column(name = "cargo_type_name")
    private String name;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onModified() {
        this.modifiedAt = LocalDateTime.now();
    }
}
