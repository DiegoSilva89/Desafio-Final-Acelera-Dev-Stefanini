package com.stefanini.api.controller;

import com.stefanini.api.venda.RelatorioVendasClienteDTO;
import com.stefanini.api.venda.RelatorioVendasClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorio-vendas-cliente")
public class RelatorioVendasClienteController {

    @Autowired
    private RelatorioVendasClienteService relatorioVendasClienteService;

    @GetMapping("/{cpf}")
    public RelatorioVendasClienteDTO consultarRelatorioVendasPorCliente(@PathVariable String cpf) {
        return relatorioVendasClienteService.consultarRelatorioVendasPorCliente(cpf);
    }
}
