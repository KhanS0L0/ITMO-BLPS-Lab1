package com.example.dto;

import com.example.validationConstraint.annotation.Password;
import com.example.validationConstraint.annotation.Username;
import lombok.Data;

@Data
public class AuthenticationDTO {

    @Username
    private String username;

    @Password
    private String password;
}
