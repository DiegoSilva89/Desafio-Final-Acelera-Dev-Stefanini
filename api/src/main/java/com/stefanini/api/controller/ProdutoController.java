package com.stefanini.api.controller;

import com.stefanini.api.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/{id}/quantidades")
    public ResponseEntity<String> registrarQuantidade(
            @PathVariable Long id,
            @RequestParam int quantidade,
            @RequestParam int mes,
            @RequestParam int ano) {

        produtoService.registrarQuantidade(id, quantidade, mes, ano);
        return ResponseEntity.ok("Quantidade registrada com sucesso para o produto de ID " + id);
    }
}
