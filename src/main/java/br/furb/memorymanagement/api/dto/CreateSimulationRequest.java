package br.furb.memorymanagement.api.dto;

import br.furb.core.strategies.AllocationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateSimulationRequest(
        @Positive(message = "deve ser maior que zero") int tamanhoMemoria,
        @NotNull(message = "é obrigatória") AllocationType estrategia) {
}
