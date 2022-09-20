package com.example.serviceauthformicroproject04.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by Vladislav Domaniewski
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Value("${auth.jwt.secret}")
    public String secretKey;

    @Override
    public String personGeneratedToken(String personId) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant now = Instant.now();
        Instant exp = now.plus(5, ChronoUnit.MINUTES);

        return JWT.create()
                .withIssuer("auth-service")
                .withAudience("bookstore")
                .withSubject(personId)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .sign(algorithm);
    }
}
