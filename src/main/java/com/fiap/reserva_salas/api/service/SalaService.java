package com.fiap.reserva_salas.api.service;

import com.fiap.reserva_salas.api.domain.Sala;
import com.fiap.reserva_salas.api.repository.SalaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalaService {
    private final SalaRepository repo;

    public SalaService(SalaRepository repo) {
        this.repo = repo;
    }

    public List<Sala> listar() {
        return repo.findAll();
    }

    public Sala salvar(Sala sala) {
        return repo.save(sala);
    }

    public Sala atualizar(Long id, Sala sala) {
        sala.setId(id);
        return repo.save(sala);
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }
}