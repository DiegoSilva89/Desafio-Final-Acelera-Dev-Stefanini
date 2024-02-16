package com.stefanini.api.venda;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoVendidoDTO {

    private Long idProduto;
    private String nomeProduto;
    private String cpfUsuario;
    private int quantidadeVendida;
    private String dataCompra;
    private BigDecimal precoProduto;


}
