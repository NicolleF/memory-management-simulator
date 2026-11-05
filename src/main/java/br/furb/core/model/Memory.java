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
        Objects.requireNonNull(processId, "O identificador do processo é obrigatório.");
        int index = -1;
        for (int i = 0; i < blocks.size(); i++) {
            Process process = blocks.get(i).process();
            if (process != null && process.id().equals(processId)) {
                index = i;
                break;
            }
        }
        if (index < 0) {
            return false;
        }

        MemoryBlock released = blocks.get(index);
        blocks.set(index, new MemoryBlock(released.start(), released.size(), null));
        if (index > 0 && blocks.get(index - 1).isFree()) {
            MemoryBlock previous = blocks.get(index - 1);
            released = new MemoryBlock(previous.start(), previous.size() + released.size(), null);
            blocks.set(index - 1, released);
            blocks.remove(index);
            index--;
        }
        if (index + 1 < blocks.size() && blocks.get(index + 1).isFree()) {
            MemoryBlock next = blocks.get(index + 1);
            MemoryBlock current = blocks.get(index);
            blocks.set(index, new MemoryBlock(current.start(), current.size() + next.size(), null));
            blocks.remove(index + 1);
        }
        return true;
    }

    public void reiniciar() {
        blocks.clear();
        blocks.add(new MemoryBlock(0, totalSize, null));
    }
}
