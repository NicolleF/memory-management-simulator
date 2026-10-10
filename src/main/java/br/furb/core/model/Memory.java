package br.furb.core.model;

import br.furb.core.strategies.AllocationStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Memory {
    private final int totalSize;
    private final List<MemoryBlock> blocks = new ArrayList<>();

    public Memory(int totalSize) {
        if (totalSize <= 0) {
            throw new IllegalArgumentException("O tamanho da memória deve ser positivo.");
        }
        this.totalSize = totalSize;
        blocks.add(new MemoryBlock(0, totalSize, null));
    }

    public int getTotalSize() {
        return totalSize;
    }

    public List<MemoryBlock> getBlocks() {
        return List.copyOf(blocks);
    }

    public boolean alocar(Process process, AllocationStrategy strategy) {
        Objects.requireNonNull(strategy, "A estratégia é obrigatória.");
        return strategy.alocarMemoria(this, process);
    }

    public boolean alocarNoBloco(Process process, MemoryBlock block) {
        Objects.requireNonNull(process, "O processo é obrigatório.");
        Objects.requireNonNull(block, "O bloco é obrigatório.");
        int index = blocks.indexOf(block);
        if (index < 0 || !block.isFree() || block.size() < process.size()) {
            return false;
        }

        blocks.set(index, new MemoryBlock(block.start(), process.size(), process));
        int remainingSize = block.size() - process.size();
        if (remainingSize > 0) {
            blocks.add(index + 1, new MemoryBlock(block.start() + process.size(), remainingSize, null));
        }
        return true;
    }

    public boolean liberar(String processId) {
        throw new UnsupportedOperationException("Liberação ainda não implementada.");
    }
}
