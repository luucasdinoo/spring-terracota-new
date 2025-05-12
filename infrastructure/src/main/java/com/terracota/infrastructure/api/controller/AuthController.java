package com.terracota.infrastructure.api.controller;

import com.terracota.infrastructure.security.TokenService;
import com.terracota.infrastructure.security.models.AuthenticationRequest;
import com.terracota.infrastructure.security.models.AuthenticationResponse;
import com.terracota.infrastructure.user.UserEmbedded;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("auth")
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

    @Operation(summary = "Authenticate customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer authenticated")
    })
    @PostMapping
    public AuthenticationResponse auth(@RequestBody AuthenticationRequest request){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication auth = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenService.generateToken((UserEmbedded) auth.getPrincipal());

        return new AuthenticationResponse(token);
    }
}
