package br.furb.memorymanagement.application.exception;

public class ProcessNotFoundException extends RuntimeException {
    public ProcessNotFoundException() {
        super("Processo não encontrado na simulação.");
    }
}
