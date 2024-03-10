package com.ebenezer.audit.controller;

import com.ebenezer.audit.dto.LoginDto;
import com.ebenezer.audit.dto.LoginResponse;
import com.ebenezer.audit.service.impl.AuthImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthImpl authImpl;

    public AuthController(AuthImpl authImpl) {
        this.authImpl = authImpl;
    }

    @PostMapping("/login")
    public LoginResponse authenticateUser(@RequestBody LoginDto loginDto) {
        return authImpl.login(loginDto);
    }
}
