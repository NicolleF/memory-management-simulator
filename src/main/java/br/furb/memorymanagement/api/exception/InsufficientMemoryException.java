package br.furb.memorymanagement.application.exception;

public class InsufficientMemoryException extends RuntimeException {
    public InsufficientMemoryException() {
        super("Não há espaço livre suficiente para alocar o processo.");
    }
}
