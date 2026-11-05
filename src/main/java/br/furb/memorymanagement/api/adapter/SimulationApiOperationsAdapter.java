package br.furb.memorymanagement.api.adapter;

import br.furb.memorymanagement.api.dto.AllocateProcessRequest;
import br.furb.memorymanagement.api.dto.CreateSimulationRequest;
import br.furb.memorymanagement.api.dto.SimulationResponse;
import br.furb.memorymanagement.application.SimulationService;
import br.furb.memorymanagement.api.interfaces.SimulationApiOperations;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class SimulationApiOperationsAdapter implements SimulationApiOperations {
    private final SimulationService service;

    public SimulationApiOperationsAdapter(SimulationService service) { this.service = service; }

    @Override
    public SimulationResponse criar(CreateSimulationRequest request) {
        return service.create(request.tamanhoMemoria(), request.estrategia());
    }

    @Override
    public SimulationResponse consultar(UUID simulationId) {
        return service.get(simulationId);
    }

    @Override
    public SimulationResponse alocar(UUID simulationId, AllocateProcessRequest request) {
        return service.allocate(simulationId, request.nome(), request.tamanho());
    }

    @Override
    public SimulationResponse liberar(UUID simulationId, UUID processId) {
        return service.release(simulationId, processId);
    }

    @Override
    public SimulationResponse reiniciar(UUID simulationId) {
        return service.reset(simulationId);
    }
}
