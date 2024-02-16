package com.stefanini.api.venda;

import com.stefanini.api.cliente.ClienteDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RelatorioVendasClienteDTO {
    private ClienteDTO cliente;
    private List<ProdutoVendidoDTO> produtosVendidos;


}
