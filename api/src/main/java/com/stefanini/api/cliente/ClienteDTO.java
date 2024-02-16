package com.stefanini.api.cliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private String nome;
    private String cpf;
    private EnderecoDTO endereco;

    //Construtor sem argumentos
    public ClienteDTO() {}

    //Construtor com todos os campos
    public ClienteDTO(String nome, String cpf, EnderecoDTO endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    //Construtor para criar um ClienteDTO a partir de um Cliente
    public ClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        if (cliente.getEndereco() != null) {
            this.endereco = new EnderecoDTO(
                    cliente.getEndereco().getCep(),
                    cliente.getEndereco().getLogradouro(),
                    cliente.getEndereco().getComplemento(),
                    cliente.getEndereco().getBairro(),
                    cliente.getEndereco().getLocalidade(),
                    cliente.getEndereco().getUf()
            );
        }
    }

}
