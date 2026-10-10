package br.furb.core.strategies;

import br.furb.core.model.Memory;
import br.furb.core.model.MemoryBlock;
import br.furb.core.model.Process;
import java.util.Objects;

public class AllocationBestFit implements AllocationStrategy {
    @Override
    public boolean alocarMemoria(Memory memory, Process process) {
        Objects.requireNonNull(memory, "A memória é obrigatória.");
        Objects.requireNonNull(process, "O processo é obrigatório.");
        MemoryBlock best = null;
        for (MemoryBlock block : memory.getBlocks()) {
            if (block.isFree() && block.size() >= process.size()
                    && (best == null || block.size() < best.size())) {
                best = block;
            }
        }
        return best != null && memory.alocarNoBloco(process, best);
    }
}
