package br.furb.core.strategies;

import br.furb.core.model.Memory;
import br.furb.core.model.Process;

public class AllocationFirstFit implements AllocationStrategy {
    @Override
    public boolean alocarMemoria(Memory memory, Process process) {
        throw new UnsupportedOperationException("First Fit ainda não implementado.");
    }

}
