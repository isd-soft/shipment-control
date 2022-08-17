package com.isdmoldova.shipmentcontrolbackend.entity;

import lombok.AccessLevel;
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

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();


    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }
    public void addRole(Role role) {
        roles.add(role);
        role.addUser(this);
    }


}
