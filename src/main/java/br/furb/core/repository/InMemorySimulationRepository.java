package br.furb.core.repository;

import br.furb.core.model.Simulation;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemorySimulationRepository implements SimulationRepository {
    private final ConcurrentMap<UUID, Simulation> simulations = new ConcurrentHashMap<>();

    @Override
    public void save(Simulation simulation) { simulations.put(simulation.id(), simulation); }

    @Override
    public Optional<Simulation> findById(UUID id) { return Optional.ofNullable(simulations.get(id)); }
}
