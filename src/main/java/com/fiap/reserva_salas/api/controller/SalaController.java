package com.fiap.reserva_salas.api.controller;

import com.fiap.reserva_salas.api.domain.Sala;
import com.fiap.reserva_salas.api.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {
    private final SalaService service;
    public SalaController(SalaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Sala> criar(@Valid @RequestBody Sala sala) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(sala));
    }

    @GetMapping
    public ResponseEntity<List<Sala>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> atualizar(@PathVariable Long id, @Valid @RequestBody Sala sala) {
        return ResponseEntity.ok(service.atualizar(id, sala));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}