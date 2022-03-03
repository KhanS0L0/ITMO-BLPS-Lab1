package com.example.controller.controllers;

import com.example.dto.AuthenticationDTO;
import com.example.dto.RegistrationDTO;
import com.example.exception.UserAlreadyExistException;
import com.example.service.interfaces.authentication.AuthenticationService;
import com.example.service.interfaces.authentication.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {

    private final RegistrationService registrationService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AccountController(RegistrationService registrationService, AuthenticationService authenticationService) {
        this.registrationService = registrationService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/signUp", produces = "application/json")
    public ResponseEntity registration(@RequestBody RegistrationDTO dto) throws UserAlreadyExistException {
        registrationService.signUp(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/signIn", produces = "application/json")
    public ResponseEntity authentication(@RequestBody AuthenticationDTO dto){
        Map<String, String> response = authenticationService.signIn(dto);
        return ResponseEntity.ok(response);
    }
}
