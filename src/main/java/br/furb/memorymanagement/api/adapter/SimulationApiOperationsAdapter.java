package br.furb.memorymanagement.api.adapter;

import br.furb.memorymanagement.api.dto.AllocateProcessRequest;
import br.furb.memorymanagement.api.dto.CreateSimulationRequest;
import br.furb.memorymanagement.api.dto.SimulationResponse;
import br.furb.memorymanagement.api.exception.SimulationApiUnavailableException;
import br.furb.memorymanagement.api.interfaces.SimulationApiOperations;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class SimulationApiOperationsAdapter implements SimulationApiOperations {
    @Override
    public SimulationResponse criar(CreateSimulationRequest request) {
        throw new SimulationApiUnavailableException();
    }

    @Override
    public SimulationResponse consultar(UUID simulationId) {
        throw new SimulationApiUnavailableException();
    }

    @Override
    public SimulationResponse alocar(UUID simulationId, AllocateProcessRequest request) {
        throw new SimulationApiUnavailableException();
    }

    @Override
    public SimulationResponse liberar(UUID simulationId, UUID processId) {
        throw new SimulationApiUnavailableException();
    }

    @Override
    public SimulationResponse reiniciar(UUID simulationId) {
        throw new SimulationApiUnavailableException();
    }
}
