package com.zemnov.salon.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
