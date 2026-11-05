package br.furb.memorymanagement.api.interfaces;

import br.furb.memorymanagement.api.dto.AllocateProcessRequest;
import br.furb.memorymanagement.api.dto.CreateSimulationRequest;
import br.furb.memorymanagement.api.dto.SimulationResponse;
import java.util.UUID;

public interface SimulationApiOperations {
    SimulationResponse criar(CreateSimulationRequest request);

    SimulationResponse consultar(UUID simulationId);

    SimulationResponse alocar(UUID simulationId, AllocateProcessRequest request);

    SimulationResponse liberar(UUID simulationId, UUID processId);

    SimulationResponse reiniciar(UUID simulationId);
}
