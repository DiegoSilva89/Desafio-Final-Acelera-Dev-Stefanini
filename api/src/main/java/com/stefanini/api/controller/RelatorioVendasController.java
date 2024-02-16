package com.stefanini.api.controller;

import com.stefanini.api.venda.RelatorioVendasDTO;
import com.stefanini.api.venda.RelatorioVendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/relatorio-vendas")
public class RelatorioVendasController {

    @Autowired
    private RelatorioVendasService relatorioVendasService;

    @GetMapping("/{data}")
    public ResponseEntity<RelatorioVendasDTO> consultarRelatorioVendas(@PathVariable String data) {
        //Converter a String data para LocalDate no formato correto (dd/MM/yyyy)
        LocalDate dataConsulta = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        //Gerar o relatório de vendas para a data fornecida
        RelatorioVendasDTO relatorioVendasDTO = relatorioVendasService.gerarRelatorioVendas(dataConsulta);

        //Definir o status HTTP com base no status do relatório
        HttpStatus status = HttpStatus.OK;
        if ("QTD_DIVERGENTE".equals(relatorioVendasDTO.getStatus())) {
            status = HttpStatus.BAD_REQUEST;
        } else if ("BAIXA_DEMANDA".equals(relatorioVendasDTO.getStatus())) {
            status = HttpStatus.NOT_ACCEPTABLE;
        }

        //Retornar a resposta com o relatório de vendas e o status HTTP correspondente
        return ResponseEntity.status(status).body(relatorioVendasDTO);
    }
}
