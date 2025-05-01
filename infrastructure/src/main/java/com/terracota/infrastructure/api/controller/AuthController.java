package com.terracota.infrastructure.api.controller;

import com.terracota.infrastructure.security.TokenService;
import com.terracota.infrastructure.security.models.AuthenticationRequest;
import com.terracota.infrastructure.security.models.AuthenticationResponse;
import com.terracota.infrastructure.user.UserEmbedded;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(
            final AuthenticationManager authenticationManager,
            final TokenService tokenService
    ) {
        this.authenticationManager = Objects.requireNonNull(authenticationManager);
        this.tokenService = Objects.requireNonNull(tokenService);
    }

    @PostMapping
    public AuthenticationResponse auth(@RequestBody AuthenticationRequest request){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication auth = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenService.generateToken((UserEmbedded) auth.getPrincipal());

        return new AuthenticationResponse(token);
    }
}
