package com.example.controller.authAndReg;

import com.example.dto.AuthenticationDTO;
import com.example.service.interfaces.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/login")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity signIn(@Valid @RequestBody AuthenticationDTO request){
        try{
            Map<String, String> response = authenticationService.signIn(request);
            return ResponseEntity.ok(response);
        }catch(AuthenticationException e){
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }
}
