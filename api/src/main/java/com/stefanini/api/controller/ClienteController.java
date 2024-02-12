package com.stefanini.api.controller;

import com.stefanini.api.cliente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroCliente dados) {

        //Obter o endereço pelo CEP usando a classe ViaCEPClient
        ViaCEPClient viaCEPClient = new ViaCEPClient();
        Endereco endereco = viaCEPClient.consultarEnderecoPorCep(dados.cep());

        if (endereco != null) {

            //Atualizar os dados do endereço no objeto DadosCadastroCliente
            dados = new DadosCadastroCliente(
                    dados.nome(),
                    dados.nascimento(),
                    dados.cpf(),
                    dados.cep(),
                    endereco.getLogradouro(),
                    endereco.getComplemento(),
                    endereco.getBairro(),
                    endereco.getLocalidade(),
                    endereco.getUf(),
                    endereco.getIbge(),
                    endereco.getGia(),
                    endereco.getDdd(),
                    endereco.getSiafi()
            );

            //Criar o objeto Cliente com os dados atualizados do endereço
            Cliente cliente = new Cliente(dados);

            //Salvar o cliente no banco de dados
            repository.save(cliente);

            //Exibir os dados completos
            String enderecoCompleto = endereco.enderecoCompleto();
            System.out.println("Dados completos: " + dados + ", " + enderecoCompleto);
        } else {
            System.out.println("Erro ao obter dados do endereço para o CEP: " + dados.cep());
        }
    }

    @GetMapping
    public Page<DadosListagemCliente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemCliente::new);
    }

}

