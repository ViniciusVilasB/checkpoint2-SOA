package com.fiap.reserva_salas.api.controller;

import com.fiap.reserva_salas.api.config.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody Map<String, String> credenciais) {
        String usuario = credenciais.get("usuario");
        String senha = credenciais.get("senha");

        if ("admin".equals(usuario) && "admin123".equals(senha)) {
            String token = tokenService.gerarToken(usuario);
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}