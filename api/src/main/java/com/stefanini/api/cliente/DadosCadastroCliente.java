package com.stefanini.api.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DadosCadastroCliente(
        String nome,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate nascimento,
        String cpf,
        String cep) {

    // Método toString personalizado para exibir os dados no formato desejado
    @Override
    public String toString() {
        return "Nome: " + nome + ", Nascimento: " + nascimento + ", CPF: " + cpf + ", CEP: " + cep;
    }

    public Endereco obterEnderecoViaCEP() {
        // Chamar o método consultarEnderecoPorCep do ViaCEPClient
        ViaCEPClient viaCEPClient = new ViaCEPClient();
        return viaCEPClient.consultarEnderecoPorCep(cep);
    }



}
