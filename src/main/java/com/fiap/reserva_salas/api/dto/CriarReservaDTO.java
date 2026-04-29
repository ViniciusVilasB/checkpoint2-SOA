package com.fiap.reserva_salas.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CriarReservaDTO {

    @NotNull(message = "ID da Sala é obrigatório")
    private Long salaId;

    @NotBlank(message = "Responsável é obrigatório")
    private String responsavel;

    @NotNull
    @FutureOrPresent(message = "Início deve ser no presente ou futuro")
    private LocalDateTime dataHoraInicio;

    @NotNull
    @Future(message = "Fim deve ser no futuro")
    private LocalDateTime dataHoraFim;
}