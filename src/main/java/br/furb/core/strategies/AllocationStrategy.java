package br.furb.core.strategies;

import br.furb.core.model.Memory;
import br.furb.core.model.Process;

public interface AllocationStrategy {
    boolean alocarMemoria(Memory memory, Process process);
}
