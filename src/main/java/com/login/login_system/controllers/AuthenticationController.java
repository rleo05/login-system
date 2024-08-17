package com.login.login_system.controllers;

import com.login.login_system.dto.LoginRequest;
import com.login.login_system.dto.RegisterRequest;
import com.login.login_system.dto.TokenResponse;
import com.login.login_system.entities.User;
import com.login.login_system.repositories.UserRepository;
import com.login.login_system.services.TokenService;
import com.login.login_system.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request){
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody @Valid RegisterRequest request){
        if(userService.findByEmail(request.email()) != null)
            throw new DataIntegrityViolationException("User with this email already exists");

        userService.registerUser(request);
        return ResponseEntity.ok().build();
    }
}
