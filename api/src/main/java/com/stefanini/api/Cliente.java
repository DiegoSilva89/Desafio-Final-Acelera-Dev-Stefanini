package com.stefanini.api;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Cliente {

    private String nome;
    private LocalDate dataDeNascimento;
    private String cpf;
    private String cep;



}
