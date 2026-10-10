package br.furb.core.strategies;

import br.furb.core.model.Memory;
import br.furb.core.model.Process;

public class AllocationWorstFit implements AllocationStrategy {
    @Override
    public boolean alocarMemoria(Memory memory, Process process) {
        throw new UnsupportedOperationException("Worst Fit ainda não implementado.");
    }

}
