package br.furb.memorymanagement.api.dto;

import java.util.UUID;

public record ProcessResponse(UUID id, String nome, int tamanho) {
}
