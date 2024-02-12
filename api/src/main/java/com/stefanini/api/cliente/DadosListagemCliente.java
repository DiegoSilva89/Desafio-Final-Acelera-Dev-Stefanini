package com.stefanini.api.cliente;

import java.time.LocalDate;


public record DadosListagemCliente(String nome, LocalDate nascimento, String cpf, Endereco endereco)  {

    public DadosListagemCliente(Cliente cliente) {
        this(cliente.getNome(), cliente.getNascimento(), cliente.getCpf(), cliente.getEndereco());
    }

}