package br.furb.memorymanagement.api.dto;

public record MemoryBlockResponse(
        int enderecoInicial,
        int tamanho,
        ProcessResponse processo) {
}
