package br.furb.memorymanagement.api.controller;

import br.furb.memorymanagement.api.dto.AllocateProcessRequest;
import br.furb.memorymanagement.api.dto.CreateSimulationRequest;
import br.furb.memorymanagement.api.dto.SimulationResponse;
import br.furb.memorymanagement.api.interfaces.SimulationApiOperations;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/simulacoes")
public class SimulationController {
    private final SimulationApiOperations operations;

    public SimulationController(SimulationApiOperations operations) {
        this.operations = operations;
    }

    @PostMapping
    public ResponseEntity<SimulationResponse> criar(@Valid @RequestBody CreateSimulationRequest request) {
        SimulationResponse response = operations.criar(request);
        URI location = URI.create("/api/simulacoes/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{simulationId}")
    public SimulationResponse consultar(@PathVariable UUID simulationId) {
        return operations.consultar(simulationId);
    }

    @PostMapping("/{simulationId}/processos")
    public SimulationResponse alocar(
            @PathVariable UUID simulationId,
            @Valid @RequestBody AllocateProcessRequest request) {
        return operations.alocar(simulationId, request);
    }

    @DeleteMapping("/{simulationId}/processos/{processId}")
    public SimulationResponse liberar(
            @PathVariable UUID simulationId,
            @PathVariable UUID processId) {
        return operations.liberar(simulationId, processId);
    }

    @PostMapping("/{simulationId}/reiniciar")
    public SimulationResponse reiniciar(@PathVariable UUID simulationId) {
        return operations.reiniciar(simulationId);
    }
}
