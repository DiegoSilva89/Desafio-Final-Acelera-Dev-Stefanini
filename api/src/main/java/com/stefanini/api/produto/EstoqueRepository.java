package com.stefanini.api.produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Estoque findByProdutoIdAndMesAndAno(Long produtoId, int mes, int ano);
}
