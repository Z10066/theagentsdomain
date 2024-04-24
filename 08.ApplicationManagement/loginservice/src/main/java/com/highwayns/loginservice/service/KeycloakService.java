package com.highwayns.loginservice.service;

import com.highwayns.loginservice.dto.KeycloakUser;
import com.highwayns.loginservice.dto.LoginRequest;
import org.keycloak.representations.AccessTokenResponse;

public interface KeycloakService {
    public AccessTokenResponse loginWithKeycloak(LoginRequest request);
    public int createUserWithKeycloak(KeycloakUser keycloakUser);
}
