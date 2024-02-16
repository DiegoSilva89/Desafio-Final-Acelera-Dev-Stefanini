package com.stefanini.api.venda;

import com.stefanini.api.produto.Estoque;
import com.stefanini.api.produto.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RelatorioVendasService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    public RelatorioVendasDTO gerarRelatorioVendas(LocalDate data) {
        //Extrair o mês e o ano da data fornecida
        int mes = data.getMonthValue();
        int ano = data.getYear();

        //Calcular a data de início e fim do mês com base no mês e ano
        LocalDate primeiroDiaDoMes = LocalDate.of(ano, mes, 1);
        LocalDate ultimoDiaDoMes = primeiroDiaDoMes.plusMonths(1).minusDays(1);

        //Obter todas as vendas dentro do intervalo do mês
        List<Venda> vendas = vendaRepository.findByDataCompraBetween(primeiroDiaDoMes, ultimoDiaDoMes);

        //Inicializar o DTO do relatório de vendas
        RelatorioVendasDTO relatorioVendasDTO = new RelatorioVendasDTO();
        List<DetalhesVendaDTO> detalhesVendasDTO = new ArrayList<>();
        int quantidadeTotalVendida = 0;
        String status = "OK";

        //Iterar sobre as vendas para preencher os detalhes da venda
        for (Venda venda : vendas) {
            //Verificar se a quantidade vendida é maior que a quantidade disponível no estoque
            Estoque estoque = estoqueRepository.findByProdutoIdAndMesAndAno(venda.getIdProduto(), mes, ano);
            if (estoque == null || venda.getQuantidade() > estoque.getQuantidade()) {
                status = "QTD_DIVERGENTE";
            }

            //Atualizar a quantidade total vendida
            quantidadeTotalVendida += venda.getQuantidade();

            //Preencher o DTO de DetalhesVenda
            DetalhesVendaDTO detalhesVendaDTO = new DetalhesVendaDTO();
            detalhesVendaDTO.setIdProduto(venda.getIdProduto());
            detalhesVendaDTO.setCpfUsuario(venda.getCpfUsuario());
            detalhesVendaDTO.setDataCompra(venda.getDataCompra());
            detalhesVendaDTO.setQuantidadeComprada(venda.getQuantidade());
            detalhesVendaDTO.setPrecoTotal(venda.getPrecoTotal());
            detalhesVendasDTO.add(detalhesVendaDTO);
        }

        //Preencher o DTO do relatório de vendas
        relatorioVendasDTO.setQuantidadeTotalVendida(quantidadeTotalVendida);
        relatorioVendasDTO.setDetalhesVendas(detalhesVendasDTO);
        relatorioVendasDTO.setStatus(status);

        return relatorioVendasDTO;
    }
}
