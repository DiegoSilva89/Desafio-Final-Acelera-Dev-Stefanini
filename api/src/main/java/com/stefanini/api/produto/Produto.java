package com.stefanini.api.produto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Produto {

    private Long id;
    private String nome;
    private BigDecimal preco;
}
