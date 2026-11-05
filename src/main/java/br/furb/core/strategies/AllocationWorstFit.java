package br.furb.core.strategies;

import br.furb.core.model.Memory;
import br.furb.core.model.MemoryBlock;
import br.furb.core.model.Process;
import java.util.Objects;

public class AllocationWorstFit implements AllocationStrategy {
    @Override
    public boolean alocarMemoria(Memory memory, Process process) {
        Objects.requireNonNull(memory, "A memória é obrigatória.");
        Objects.requireNonNull(process, "O processo é obrigatório.");

        MemoryBlock worst = null;
        for (MemoryBlock block : memory.getBlocks()) {
            if (block.isFree() && block.size() >= process.size()
                    && (worst == null || block.size() > worst.size())) {
                worst = block;
            }
        }

        return worst != null && memory.alocarNoBloco(process, worst);
    }

}
