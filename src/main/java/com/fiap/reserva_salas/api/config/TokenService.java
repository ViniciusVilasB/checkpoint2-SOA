package com.fiap.reserva_salas.api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    private final String SECRET = "minha-senha-secreta-cp2";

    public String gerarToken(String usuario) {
        return JWT.create()
                .withIssuer("API_Salas")
                .withSubject(usuario)
                .withExpiresAt(Instant.now().plusSeconds(7200)) // Expira em 2 horas
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String validarToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .withIssuer("API_Salas")
                .build()
                .verify(token)
                .getSubject();
    }
}