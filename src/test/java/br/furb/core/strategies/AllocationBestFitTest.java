package br.furb.core.strategies;

import br.furb.core.model.Memory;
import br.furb.core.model.MemoryBlock;
import br.furb.core.model.Process;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllocationBestFitTest {
    private final AllocationBestFit strategy = new AllocationBestFit();

    @Test
    void selectsSmallestSuitableFreeBlock() {
        Memory memory = memoryWithFreeBlocks(20, 40);
        Process requested = new Process("requested", 15);

        assertTrue(memory.alocar(requested, strategy));
        assertEquals(new MemoryBlock(10, 15, requested), memory.getBlocks().get(1));
        assertEquals(new MemoryBlock(25, 5, null), memory.getBlocks().get(2));
        assertEquals(new MemoryBlock(60, 40, null), memory.getBlocks().get(4));
    }

    @Test
    void allocatesExactFitWithoutCreatingRemainder() {
        Memory memory = memoryWithFreeBlocks(20, 40);
        Process requested = new Process("requested", 20);

        assertTrue(memory.alocar(requested, strategy));
        assertEquals(new MemoryBlock(10, 20, requested), memory.getBlocks().get(1));
        assertEquals(4, memory.getBlocks().size());
    }

    @Test
    void returnsFalseWhenNoBlockIsLargeEnough() {
        Memory memory = memoryWithFreeBlocks(20, 40);

        assertFalse(memory.alocar(new Process("requested", 41), strategy));
    }

    private Memory memoryWithFreeBlocks(int firstHole, int secondHole) {
        Memory memory = new Memory(100);
        memory.alocarNoBloco(new Process("p1", 10), memory.getBlocks().get(0));
        memory.alocarNoBloco(new Process("p2", firstHole), memory.getBlocks().get(1));
        memory.alocarNoBloco(new Process("p3", 30), memory.getBlocks().get(2));
        memory.alocarNoBloco(new Process("p4", secondHole), memory.getBlocks().get(3));
        memory.liberar("p2");
        memory.liberar("p4");
        return memory;
    }
}
