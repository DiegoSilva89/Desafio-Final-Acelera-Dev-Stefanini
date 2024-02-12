package com.stefanini.api.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
@Validated
public record DadosCadastroCliente(
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,
        @NotBlank
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate nascimento,
        @NotBlank
        String cpf,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String ibge,
        String gia,
        String ddd,
        String siafi) {

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
