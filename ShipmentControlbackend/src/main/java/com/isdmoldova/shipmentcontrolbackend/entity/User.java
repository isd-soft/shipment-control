package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity  {

    @Column(name = "company_name", unique = true)
    private String companyName;

    @Column(name = "username", unique = true, updatable = false)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "telephone_number", unique = true)
    private String telephoneNumber;

    @Column(name = "password", length = 3000)
    private String password;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "user",
            orphanRemoval = true)
    private final List<Route> routes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<BookingRequests> bookingRequests = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role;


}
