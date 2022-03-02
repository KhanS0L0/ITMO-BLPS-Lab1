package com.example.service.implementation.authentication;

import com.example.dto.AuthenticationDTO;
import com.example.entity.user.Account;
import com.example.exception.UserNotFoundException;
import com.example.security.JwtUtils.JwtTokenProvider;
import com.example.service.interfaces.user.AccountService;
import com.example.service.interfaces.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountService accountService;

    private final Map<String, String> response = new HashMap<>();

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     JwtTokenProvider jwtTokenProvider,
                                     AccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accountService = accountService;
    }

    @Override
    public Map<String, String> signIn(AuthenticationDTO dto) throws UserNotFoundException {
        String username = dto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.getPassword()));

        Account account = accountService.findAccountByUsername(username);
        if(account == null)
            throw new UserNotFoundException("User with username: " + username + " not found");

        String token = jwtTokenProvider.createToken(username, account.getRoles(), account.getId());

        response.put("token", token);
        return response;
    }
}
