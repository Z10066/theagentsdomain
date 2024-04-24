package com.highwayns.loginservice.service.impl;

import com.highwayns.loginservice.convertor.UserMapper;
import com.highwayns.loginservice.dto.KeycloakUser;
import com.highwayns.loginservice.dto.SignUpRequest;
import com.highwayns.loginservice.entity.User;
import com.highwayns.loginservice.repository.UserRepository;
import com.highwayns.loginservice.service.KeycloakService;
import com.highwayns.loginservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;
    private final KeycloakService keycloakService;
 
    @Override
    public String signUpUser(SignUpRequest signUpRequest) {

        LOGGER.info("UserServiceImpl | signUpUser is started");

        KeycloakUser keycloakUser = new KeycloakUser();
        keycloakUser.setFirstName(signUpRequest.getName());
        keycloakUser.setLastName(signUpRequest.getSurname());
        keycloakUser.setEmail(signUpRequest.getEmail());
        keycloakUser.setPassword(signUpRequest.getPassword());
        keycloakUser.setRole(signUpRequest.getRole());
        keycloakUser.setUsername(signUpRequest.getUsername());

        int status = keycloakService.createUserWithKeycloak(keycloakUser);

        if(status == 201){

            LOGGER.info("UserServiceImpl | signUpUser | status : " + status);

            User signUpUser = UserMapper.signUpRequestToUser(signUpRequest);

            signUpUser.setCreatedAt(LocalDateTime.now());

            userRepository.save(signUpUser);

            return "Sign Up completed";
        }

        return "Not Register";
    }
}
