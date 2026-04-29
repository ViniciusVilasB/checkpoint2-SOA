package com.fiap.reserva_salas.api.repository;

import com.fiap.reserva_salas.api.domain.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {
}