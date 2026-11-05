package br.furb.memorymanagement.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AllocateProcessRequest(
        @NotBlank(message = "é obrigatório") String nome,
        @Positive(message = "deve ser maior que zero") int tamanho) {
}
