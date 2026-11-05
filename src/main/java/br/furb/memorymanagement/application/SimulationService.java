package br.furb.memorymanagement.application;

import br.furb.core.factories.AllocationFactory;
import br.furb.core.model.MemoryBlock;
import br.furb.core.model.Process;
import br.furb.core.model.Simulation;
import br.furb.core.repository.SimulationRepository;
import br.furb.core.strategies.AllocationStrategy;
import br.furb.core.strategies.AllocationType;
import br.furb.memorymanagement.application.exception.InsufficientMemoryException;
import br.furb.memorymanagement.application.exception.ProcessNotFoundException;
import br.furb.memorymanagement.application.exception.SimulationNotFoundException;
import br.furb.memorymanagement.application.exception.SimulationStrategyNotImplementedException;
import br.furb.memorymanagement.api.dto.*;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {
    private final SimulationRepository repository;

    public SimulationService(SimulationRepository repository) { this.repository = repository; }

    public SimulationResponse create(int size, AllocationType type) {
        if (type != AllocationType.BEST_FIT && type != AllocationType.WORST_FIT) {
            throw new SimulationStrategyNotImplementedException(type);
        }
        AllocationStrategy strategy = AllocationFactory.create(type);
        Simulation simulation = new Simulation(UUID.randomUUID(), size, type, strategy);
        repository.save(simulation);
        return response(simulation);
    }

    public SimulationResponse get(UUID id) { return response(find(id)); }

    public SimulationResponse allocate(UUID id, String name, int size) {
        Simulation simulation = find(id);
        Process process = new Process(UUID.randomUUID().toString(), name, size);
        if (!simulation.allocate(process)) throw new InsufficientMemoryException();
        return response(simulation);
    }

    public SimulationResponse release(UUID id, UUID processId) {
        Simulation simulation = find(id);
        if (!simulation.release(processId.toString())) throw new ProcessNotFoundException();
        return response(simulation);
    }

    public SimulationResponse reset(UUID id) {
        Simulation simulation = find(id);
        simulation.reset();
        return response(simulation);
    }

    private Simulation find(UUID id) {
        return repository.findById(id).orElseThrow(SimulationNotFoundException::new);
    }

    private SimulationResponse response(Simulation simulation) {
        int occupied = 0;
        var blocks = simulation.memory().getBlocks();
        var blockResponses = new java.util.ArrayList<MemoryBlockResponse>();
        var processes = new java.util.ArrayList<ProcessResponse>();
        for (MemoryBlock block : blocks) {
            ProcessResponse processResponse = null;
            if (!block.isFree()) {
                Process process = block.process();
                occupied += block.size();
                processResponse = new ProcessResponse(UUID.fromString(process.id()), process.name(), process.size());
                processes.add(processResponse);
            }
            blockResponses.add(new MemoryBlockResponse(block.start(), block.size(), processResponse));
        }
        int total = simulation.memory().getTotalSize();
        return new SimulationResponse(simulation.id(), total, simulation.allocationType(), occupied,
                total - occupied, blockResponses, processes);
    }
}
