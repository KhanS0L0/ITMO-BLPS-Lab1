package com.example.security.JwtUser;

import com.example.entity.user.Account;
import com.example.entity.user.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(Account account){
        return new JwtUser(
                account.getId(),
                account.getUsername(),
                account.getEmail(),
                account.getPassword(),
                true,
                mapToGrantedAuthorities(new ArrayList<>(account.getRoles()))
        );
    }

    public static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles){
        return userRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}