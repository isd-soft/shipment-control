package com.isdmoldova.shipmentcontrolbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    protected void onModified() {
        this.modifiedAt = LocalDateTime.now();
    }

    @ManyToMany(mappedBy = "cargoTypes")
    private List<Transport> transports;

    @ManyToMany
    @JoinTable(
            name = "cargo_cargo_type",
            joinColumns = @JoinColumn(name = "cargo_id"),
            inverseJoinColumns = @JoinColumn(name = "cargo_type_id"))
    private List<Cargo> cargos;

    public void addCargo(Cargo cargo) {
        cargos.add(cargo);
    }

    public void removeCargo(Cargo cargo) {
        cargos.remove(cargo);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CargoType)) return false;
        CargoType cargoType = (CargoType) obj;
        return id != null && Objects.equals(id, cargoType.getId());
    }
}
