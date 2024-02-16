package com.stefanini.api.produto;

import com.stefanini.api.infra.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    public void registrarQuantidade(Long idProduto, int quantidade, int mes, int ano) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + idProduto));

        //Verifica se já existe uma entrada de estoque para o produto no mês e ano especificados
        Estoque estoqueExistente = estoqueRepository.findByProdutoIdAndMesAndAno(idProduto, mes, ano);

        if (estoqueExistente != null) {
            //Se já existe uma entrada de estoque para o produto no mês e ano especificados, atualiza a quantidade
            estoqueExistente.setQuantidade(quantidade);
            estoqueRepository.save(estoqueExistente);
        } else {
            //Se não existe uma entrada de estoque para o produto no mês e ano especificados, cria uma nova
            Estoque novoEstoque = new Estoque();
            novoEstoque.setProduto(produto);
            novoEstoque.setQuantidade(quantidade);
            novoEstoque.setMes(mes);
            novoEstoque.setAno(ano);

            estoqueRepository.save(novoEstoque);
        }
    }
}
