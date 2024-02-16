package com.stefanini.api.venda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    @Query("SELECT new com.stefanini.api.venda.ProdutoVendidoDTO(v.idProduto, v.quantidade, v.precoTotal) " +
            "FROM Venda v WHERE v.cpfUsuario = :cpfUsuario")
    List<ProdutoVendidoDTO> findProdutosVendidosByClienteId(@Param("cpfUsuario") String cpfUsuario);

    List<Venda> findByDataCompraBetween(LocalDate startOfMonth, LocalDate endOfMonth);

    @Query("SELECT v FROM Venda v WHERE YEAR(v.dataCompra) = :year AND MONTH(v.dataCompra) = :month")
    List<Venda> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
}
