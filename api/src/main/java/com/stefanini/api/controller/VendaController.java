package com.stefanini.api.controller;

import com.stefanini.api.cliente.Cliente;
import com.stefanini.api.cliente.ClienteDTO;
import com.stefanini.api.cliente.ClienteRepository;
import com.stefanini.api.venda.ProdutoVendidoDTO;
import com.stefanini.api.venda.RelatorioVendasClienteDTO;
import com.stefanini.api.venda.Venda;
import com.stefanini.api.venda.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;
    private final ClienteRepository clienteRepository;

    @Autowired
    public VendaController(VendaService vendaService, ClienteRepository clienteRepository) {
        this.vendaService = vendaService;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/importar-vendas")
    public ResponseEntity<String> importarVendasDoExcel(@RequestParam("file") MultipartFile file) {
        try {
            vendaService.importarVendasDoExcel(file);
            return ResponseEntity.ok("Vendas importadas com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao importar vendas.");
        }
    }

    @GetMapping("/relatorio/{cpf}")
    public ResponseEntity<RelatorioVendasClienteDTO> consultarRelatorioVendasPorCliente(@PathVariable String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if (cliente == null) {
            // Cliente não encontrado, retornar erro 404
            return ResponseEntity.notFound().build();
        }

        RelatorioVendasClienteDTO relatorio = new RelatorioVendasClienteDTO();
        relatorio.setCliente(new ClienteDTO(cliente));

        // Consultar endereço via CEP
        // Código de consulta ao CEP

        // Consultar vendas do cliente pelo CPF
        List<ProdutoVendidoDTO> produtosVendidos = vendaService.consultarProdutosVendidosPorCliente(cliente.getCpf());
        relatorio.setProdutosVendidos(produtosVendidos);

        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("/relatorio/mes/{mes}/{ano}")
    public ResponseEntity<List<Venda>> consultarVendasPorMes(@PathVariable int mes, @PathVariable int ano) {
        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = dataInicio.plusMonths(1).minusDays(1);

        List<Venda> vendasDoMes = vendaService.consultarVendasPorPeriodo(dataInicio, dataFim);

        return ResponseEntity.ok(vendasDoMes);
    }
}

