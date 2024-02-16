package com.stefanini.api.excel;

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
public class VendaExcelDTO {
    private Long codigoProduto;
    private String cpfUsuario;
    private int quantidadeComprada;
    private LocalDate dataCompra;
}
