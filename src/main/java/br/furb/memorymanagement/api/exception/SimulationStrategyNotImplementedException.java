package br.furb.memorymanagement.application.exception;

import br.furb.core.strategies.AllocationType;

public class SimulationStrategyNotImplementedException extends RuntimeException {
    public SimulationStrategyNotImplementedException(AllocationType strategy) {
        super("A estratégia " + strategy + " ainda não foi implementada.");
    }
}
