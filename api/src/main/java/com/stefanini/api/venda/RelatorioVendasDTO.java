package com.stefanini.api.venda;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RelatorioVendasDTO {

    private List<ProdutoVendidoDTO> produtosVendidos;
    private int quantidadeTotalVendida;
    private String status;
    private List<DetalhesVendaDTO> detalhesVendas;

}
