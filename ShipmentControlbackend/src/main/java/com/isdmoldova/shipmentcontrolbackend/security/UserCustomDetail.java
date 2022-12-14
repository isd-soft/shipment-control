package com.isdmoldova.shipmentcontrolbackend.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public class UserCustomDetail implements UserDetails {

    private static final String ROLE_PREFIX = "ROLE_";

    private final Long id;
    private final String username;
    private final String email;


    @JsonIgnore
    private final String password;

    @Setter
    private List<GrantedAuthority> authorities = new ArrayList<>();


    public static UserCustomDetail from(User user) {
        UserCustomDetail userCustomDetail = new UserCustomDetail(user.getId(),user.getUsername(),
                user.getEmail(), user.getPassword());
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole().name()));
        userCustomDetail.setAuthorities(authorities);
        return userCustomDetail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
