package com.stefanini.api.venda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalhesVendaDTO {
    private Long idProduto;
    private String cpfUsuario;
    private LocalDate dataCompra;
    private int quantidadeComprada;
    private BigDecimal precoTotal;
}
