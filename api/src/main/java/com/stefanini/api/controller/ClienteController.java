package com.stefanini.api.controller;

import com.stefanini.api.cliente.DadosCadastroCliente;
import com.stefanini.api.cliente.Endereco;
import com.stefanini.api.cliente.ViaCEPClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroCliente dados) {
        //Obter o endereço pelo CEP usando a classe ViaCEPClient
        ViaCEPClient viaCEPClient = new ViaCEPClient();
        Endereco endereco = viaCEPClient.consultarEnderecoPorCep(dados.cep());

        //Adicionando o endereço ao objeto DadosCadastroCliente
        DadosCadastroCliente dadosComEndereco = new DadosCadastroCliente(
                dados.nome(),
                dados.nascimento(),
                dados.cpf(),
                dados.cep()
        );

        // Obter o endereço completo do objeto endereco usando o método enderecoCompleto()
        String enderecoCompleto = endereco.enderecoCompleto();

        System.out.println("Dados completos: " + dadosComEndereco + ", " + enderecoCompleto);


    }
}