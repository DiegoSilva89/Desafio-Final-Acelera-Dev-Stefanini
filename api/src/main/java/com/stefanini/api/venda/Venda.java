package com.stefanini.api.venda;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idProduto;
    private String cpfUsuario;
    private int quantidade;
    private LocalDate dataCompra;
    private BigDecimal precoTotal;
    private String nomeProduto;
    private BigDecimal precoProduto;


    public int getAno() {
        return dataCompra.getYear();
    }

    public Venda(Long idProduto, String cpfUsuario, int quantidade, LocalDate dataCompra) {
        this.idProduto = idProduto;
        this.cpfUsuario = cpfUsuario;
        this.quantidade = quantidade;
        this.dataCompra = dataCompra;
    }

}
