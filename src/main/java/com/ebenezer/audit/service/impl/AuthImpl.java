package com.ebenezer.audit.service.impl;

import com.ebenezer.audit.dto.LoginDto;
import com.ebenezer.audit.dto.LoginResponse;
import com.ebenezer.audit.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthImpl {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public LoginResponse login(LoginDto loginDTO) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginDTO.username(),
                            loginDTO.password()
                    ));
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            List<String> roles = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            String token = jwtUtils.createJwt(userDetails.getUsername(), roles, new Date(System.currentTimeMillis() * 10 * 60 * 1000));

            return new LoginResponse(loginDTO.username(), token);
        } catch (Exception ex) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
