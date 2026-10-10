package br.furb.core.model;

public record MemoryBlock(int start, int size, Process process) {
    public MemoryBlock {
        if (start < 0 || size <= 0) {
            throw new IllegalArgumentException("O início deve ser não negativo e o tamanho positivo.");
        }
        if (process != null && process.size() != size) {
            throw new IllegalArgumentException("O bloco ocupado deve ter o tamanho do processo.");
        }
    }

    public boolean isFree() {
        return process == null;
    }
}
