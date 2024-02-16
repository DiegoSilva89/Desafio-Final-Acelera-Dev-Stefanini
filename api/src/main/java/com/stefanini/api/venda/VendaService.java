package com.stefanini.api.venda;

import com.stefanini.api.excel.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public void importarVendasDoExcel(MultipartFile file) throws IOException {
        List<Venda> vendas = ExcelReader.lerVendasDoExcel(file);
        vendaRepository.saveAll(vendas);
    }

    public List<ProdutoVendidoDTO> consultarProdutosVendidosPorCliente(String cpfUsuario) {
        return vendaRepository.findProdutosVendidosByClienteId(cpfUsuario);
    }

    public List<Venda> consultarVendasPorMes(int mes, int ano) {
        LocalDate primeiroDiaDoMes = LocalDate.of(ano, mes, 1);
        LocalDate ultimoDiaDoMes = primeiroDiaDoMes.plusMonths(1).minusDays(1);

        return vendaRepository.findByDataCompraBetween(primeiroDiaDoMes, ultimoDiaDoMes);
    }

    public List<Venda> consultarVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return vendaRepository.findByDataCompraBetween(dataInicio, dataFim);
    }
}