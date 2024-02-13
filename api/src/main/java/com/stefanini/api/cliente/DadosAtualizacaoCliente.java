package com.stefanini.api.cliente;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizacaoCliente(
        @NotNull
        Long id,
        String nome,
        LocalDate nascimento,
        String cpf,
        String cep) {
}
