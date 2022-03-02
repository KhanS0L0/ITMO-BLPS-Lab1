package com.example.controller.authAndReg;

import com.example.dto.RegistrationDTO;
import com.example.exception.UserAlreadyExistException;
import com.example.service.interfaces.authentication.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // на будущее убрать try catch и заменить на controllerAdvice
    @PostMapping(produces = "application/json")
    public ResponseEntity signUp(@Valid @RequestBody RegistrationDTO request){
        try{
            registrationService.signUp(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("User successfully created");
        }catch (UserAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}