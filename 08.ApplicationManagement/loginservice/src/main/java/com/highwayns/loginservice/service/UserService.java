package com.highwayns.loginservice.service;

import com.highwayns.loginservice.dto.SignUpRequest;

public interface UserService {
    public String signUpUser(SignUpRequest signUpRequest);
}
