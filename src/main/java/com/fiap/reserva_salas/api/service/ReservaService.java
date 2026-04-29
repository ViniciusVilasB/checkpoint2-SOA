package com.fiap.reserva_salas.api.service;

import com.fiap.reserva_salas.api.domain.Reserva;
import com.fiap.reserva_salas.api.domain.Sala;
import com.fiap.reserva_salas.api.dto.CriarReservaDTO;
import com.fiap.reserva_salas.api.repository.ReservaRepository;
import com.fiap.reserva_salas.api.repository.SalaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReservaService {

    private final ReservaRepository reservaRepo;
    private final SalaRepository salaRepo;

    public ReservaService(ReservaRepository reservaRepo, SalaRepository salaRepo) {
        this.reservaRepo = reservaRepo;
        this.salaRepo = salaRepo;
    }

    public Reserva criar(CriarReservaDTO dto) {
        log.info("Iniciando criacao de reserva para a sala ID: {}", dto.getSalaId()); // <-- LOG AQUI

        Sala sala = salaRepo.findById(dto.getSalaId())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada!"));

        if (dto.getDataHoraInicio().isAfter(dto.getDataHoraFim()) || dto.getDataHoraInicio().isEqual(dto.getDataHoraFim())) {
            log.warn("Tentativa de reserva com datas invalidas pelo usuario: {}", dto.getResponsavel()); // <-- LOG AQUI
            throw new IllegalArgumentException("A data de início deve ser anterior à data de fim.");
        }

        boolean temConflito = reservaRepo.existeConflito(dto.getSalaId(), dto.getDataHoraInicio(), dto.getDataHoraFim());

        if (temConflito) {
            log.error("Conflito detectado para a sala {} no periodo selecionado", sala.getNome()); // <-- LOG AQUI
            throw new RuntimeException("Conflito de horários: Já existe uma reserva para esta sala neste período.");
        }

        Reserva reserva = new Reserva();
        reserva.setSala(sala);
        reserva.setResponsavel(dto.getResponsavel());
        reserva.setDataHoraInicio(dto.getDataHoraInicio());
        reserva.setDataHoraFim(dto.getDataHoraFim());

        log.info("Reserva criada com sucesso no banco de dados!"); // <-- LOG AQUI
        return reservaRepo.save(reserva);
    }

    public List<Reserva> listar() {
        log.info("Buscando todas as reservas cadastradas...");
        return reservaRepo.findAll();
    }

    public void cancelar(Long id) {
        if (!reservaRepo.existsById(id)) {
            throw new RuntimeException("Reserva não encontrada!");
        }
        log.info("Cancelando reserva de ID: {}", id);
        reservaRepo.deleteById(id);
    }
}