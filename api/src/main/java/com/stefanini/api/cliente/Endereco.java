package com.stefanini.api.cliente;

import lombok.Getter;
import lombok.Setter;

public record Endereco(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String ibge,
        String gia,
        String ddd,
        String siafi) {

    public String enderecoCompleto() {
        return logradouro + ", " + bairro + ", " + localidade + " - " + uf;
    }
}

