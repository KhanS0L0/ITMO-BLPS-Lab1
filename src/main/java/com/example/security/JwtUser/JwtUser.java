package com.example.security.JwtUser;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class JwtUser implements UserDetails {

    private final Long userId;
    private final String username;
    private final String email;
    private final String password;
    private final boolean enable;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
            Long userId,
            String username,
            String email,
            String password,
            boolean enable,
            Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enable = enable;
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }

    @Override
    public boolean isEnabled(){
        return enable;
    }

}
