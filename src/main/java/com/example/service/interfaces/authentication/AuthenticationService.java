package com.example.service.interfaces.authentication;

import com.example.dto.AuthenticationDTO;
import com.example.exception.UserNotFoundException;

import java.util.Map;

public interface AuthenticationService {
    Map<String, String> signIn(AuthenticationDTO dto) throws UserNotFoundException;
}
