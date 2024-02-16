package com.stefanini.api.produto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public void registrarQuantidade(Long idProduto, int quantidade, int mes, int ano) {
        //Verificar se já existe uma entrada de estoque para o produto no mês e ano especificados
        Estoque estoqueExistente = estoqueRepository.findByProdutoIdAndMesAndAno(idProduto, mes, ano);

        if (estoqueExistente != null) {
            //Se já existe uma entrada de estoque para o produto no mês e ano especificados, atualizar a quantidade
            estoqueExistente.setQuantidade(quantidade);
            estoqueRepository.save(estoqueExistente);
        } else {
            //Se não existe uma entrada de estoque para o produto no mês e ano especificados, criar uma nova
            Produto produto = new Produto();
            produto.setId(idProduto);

            Estoque novoEstoque = new Estoque();
            novoEstoque.setProduto(produto);
            novoEstoque.setQuantidade(quantidade);
            novoEstoque.setMes(mes);
            novoEstoque.setAno(ano);

            estoqueRepository.save(novoEstoque);
        }
    }
}
