package br.furb.core.factories;

import br.furb.core.strategies.AllocationBestFit;
import br.furb.core.strategies.AllocationFirstFit;
import br.furb.core.strategies.AllocationNextFit;
import br.furb.core.strategies.AllocationStrategy;
import br.furb.core.strategies.AllocationType;
import br.furb.core.strategies.AllocationWorstFit;
import java.util.Objects;

public final class AllocationFactory {
    private AllocationFactory() {
    }

    public static AllocationStrategy create(AllocationType type) {
        Objects.requireNonNull(type, "O tipo de alocação é obrigatório.");
        return switch (type) {
            case FIRST_FIT -> new AllocationFirstFit();
            case BEST_FIT -> new AllocationBestFit();
            case NEXT_FIT -> new AllocationNextFit();
            case WORST_FIT -> new AllocationWorstFit();
        };
    }
}