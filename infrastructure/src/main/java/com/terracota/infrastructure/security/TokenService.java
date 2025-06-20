package com.terracota.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.terracota.infrastructure.user.UserEmbedded;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("${security.secret-key}")
    private String secretKey;

    @Value("${security.expiration-token}")
    private Integer expirationToken;

    public String generateToken(UserEmbedded user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withIssuer("terracota-api")
                    .withSubject(user.getEmail())
                    .withClaim("role", user.getRole().name())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Error creation authenticating");

        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("terracota-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error verification authenticating");

        }
    }

    private Instant generateExpirationDate() {
        return Instant.now().plus(expirationToken, ChronoUnit.HOURS);
    }
}
