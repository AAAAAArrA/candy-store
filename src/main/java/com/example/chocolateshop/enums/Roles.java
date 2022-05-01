package com.example.chocolateshop.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    USER, DIRECTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
