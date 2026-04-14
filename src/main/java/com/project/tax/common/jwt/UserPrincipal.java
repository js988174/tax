package com.project.tax.common.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;

public class UserPrincipal {

    private final String email;

    public UserPrincipal(String email) {
        this.email = email;
    }

    public UsernamePasswordAuthenticationToken getAuthentication() {
        return new UsernamePasswordAuthenticationToken(
                email,
                null,
                Collections.emptyList()
        );
    }

}
