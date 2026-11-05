package br.furb.memorymanagement.api.dto;

import br.furb.core.strategies.AllocationType;
import java.util.List;
import java.util.UUID;

public record SimulationResponse(
        UUID id,
        int tamanhoTotal,
        AllocationType estrategia,
        int memoriaOcupada,
        int memoriaLivre,
        List<MemoryBlockResponse> blocos,
        List<ProcessResponse> processos) {
    public SimulationResponse {
        blocos = List.copyOf(blocos);
        processos = List.copyOf(processos);
    }
}
