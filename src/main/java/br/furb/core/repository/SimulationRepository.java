package br.furb.core.repository;

import br.furb.core.model.Simulation;
import java.util.Optional;
import java.util.UUID;

public interface SimulationRepository {
    void save(Simulation simulation);
    Optional<Simulation> findById(UUID id);
}
