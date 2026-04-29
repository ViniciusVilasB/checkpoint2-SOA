package com.fiap.reserva_salas.api.repository;

import com.fiap.reserva_salas.api.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reserva r " +
            "WHERE r.sala.id = :salaId " +
            "AND r.dataHoraInicio < :fim " +
            "AND r.dataHoraFim > :inicio")
    boolean existeConflito(@Param("salaId") Long salaId,
                           @Param("inicio") LocalDateTime inicio,
                           @Param("fim") LocalDateTime fim);
}