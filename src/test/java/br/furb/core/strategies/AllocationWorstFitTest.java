package br.furb.core.strategies;

import br.furb.core.model.Memory;
import br.furb.core.model.MemoryBlock;
import br.furb.core.model.Process;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllocationWorstFitTest {
    private final AllocationWorstFit strategy = new AllocationWorstFit();

    @Test
    void selectsLargestSuitableFreeBlock() {
        Memory memory = memoryWithFreeBlocks();
        Process requested = new Process("requested", 15);

        assertTrue(memory.alocar(requested, strategy));
        assertEquals(new MemoryBlock(60, 15, requested), memory.getBlocks().get(3));
        assertEquals(new MemoryBlock(75, 25, null), memory.getBlocks().get(4));
        assertEquals(new MemoryBlock(10, 20, null), memory.getBlocks().get(1));
    }

    @Test
    void allocatesExactFitWithoutCreatingRemainder() {
        Memory memory = new Memory(40);
        Process requested = new Process("requested", 40);

        assertTrue(memory.alocar(requested, strategy));
        assertEquals(1, memory.getBlocks().size());
        assertEquals(new MemoryBlock(0, 40, requested), memory.getBlocks().get(0));
    }

    @Test
    void returnsFalseWhenNoBlockIsLargeEnough() {
        Memory memory = memoryWithFreeBlocks();

        assertFalse(memory.alocar(new Process("requested", 41), strategy));
    }

    private Memory memoryWithFreeBlocks() {
        Memory memory = new Memory(100);
        memory.alocarNoBloco(new Process("p1", 10), memory.getBlocks().get(0));
        memory.alocarNoBloco(new Process("p2", 20), memory.getBlocks().get(1));
        memory.alocarNoBloco(new Process("p3", 30), memory.getBlocks().get(2));
        memory.alocarNoBloco(new Process("p4", 40), memory.getBlocks().get(3));
        memory.liberar("p2");
        memory.liberar("p4");
        return memory;
    }
}
