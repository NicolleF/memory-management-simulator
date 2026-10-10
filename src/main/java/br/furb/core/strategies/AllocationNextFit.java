package br.furb.core.strategies;

import br.furb.core.model.Memory;
import br.furb.core.model.MemoryBlock;
import br.furb.core.model.Process;
import java.util.List;
import java.util.Objects;

public class AllocationNextFit implements AllocationStrategy {
    private int lastAllocationStart;
    private boolean hasAllocated;

    @Override
    public boolean alocarMemoria(Memory memory, Process process) {
        Objects.requireNonNull(memory, "A memória é obrigatória.");
        Objects.requireNonNull(process, "O processo é obrigatório.");

        List<MemoryBlock> blocks = memory.getBlocks();
        if (blocks.isEmpty()) {
            return false;
        }

        int startIndex = 0;
        if (hasAllocated) {
            for (int i = 0; i < blocks.size(); i++) {
                if (blocks.get(i).start() == lastAllocationStart) {
                    startIndex = i;
                    break;
                }
            }
        }

        for (int offset = 0; offset < blocks.size(); offset++) {
            MemoryBlock block = blocks.get((startIndex + offset) % blocks.size());
            if (block.isFree() && block.size() >= process.size()) {
                if (memory.alocarNoBloco(process, block)) {
                    lastAllocationStart = block.start();
                    hasAllocated = true;
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
