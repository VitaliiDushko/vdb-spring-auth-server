package com.vdb.auth.server;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.UUID;

public class CustomUserDetails implements UserDetails {
    private final String username;
    private final String password;
    // Getters for additional fields
    @Getter
    private String surname;
    @Getter
    private String name;
    @Getter
    private UUID userId;
    private final Collection<? extends GrantedAuthority> authorities;

    // Constructor
    public CustomUserDetails(String username, String password, UUID userId, String name, String surname, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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