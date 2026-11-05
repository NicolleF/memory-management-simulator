package br.furb.core.model;

import br.furb.core.strategies.AllocationStrategy;
import br.furb.core.strategies.AllocationType;
import java.util.Objects;
import java.util.UUID;

public class Simulation {
    private final UUID id;
    private final Memory memory;
    private final AllocationType allocationType;
    private final AllocationStrategy allocationStrategy;

    public Simulation(UUID id, int memorySize, AllocationType allocationType, AllocationStrategy allocationStrategy) {
        this.id = Objects.requireNonNull(id);
        this.memory = new Memory(memorySize);
        this.allocationType = Objects.requireNonNull(allocationType);
        this.allocationStrategy = Objects.requireNonNull(allocationStrategy);
    }

    public UUID id() { return id; }
    public Memory memory() { return memory; }
    public AllocationType allocationType() { return allocationType; }

    public boolean allocate(Process process) {
        return memory.alocar(process, allocationStrategy);
    }

    public boolean release(String processId) { return memory.liberar(processId); }
    public void reset() { memory.reiniciar(); }
}
