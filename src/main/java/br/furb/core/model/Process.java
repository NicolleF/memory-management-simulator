package br.furb.core.model;

import java.util.Objects;

public record Process(String id, String name, int size) {
    public Process {
        Objects.requireNonNull(id, "O identificador é obrigatório.");
        Objects.requireNonNull(name, "O nome é obrigatório.");
        if (id.isBlank() || name.isBlank() || size <= 0) {
            throw new IllegalArgumentException("Identificador e nome devem ser preenchidos e o tamanho positivo.");
        }
    }

    public Process(String id, int size) {
        this(id, id, size);
    }
}
