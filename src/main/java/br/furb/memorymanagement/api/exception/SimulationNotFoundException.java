package br.furb.memorymanagement.application.exception;

public class SimulationNotFoundException extends RuntimeException {
    public SimulationNotFoundException() {
        super("Simulação não encontrada.");
    }
}
