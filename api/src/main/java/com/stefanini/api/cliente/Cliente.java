package com.stefanini.api.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate nascimento;
    @NotBlank
    @Column(unique = true)
    private String cpf;
    @Embedded //Adiciona essa anotação para indicar que é um atributo embutido
    private Endereco endereco;

    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.nascimento = dados.nascimento();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.cep(), dados.logradouro(), dados.complemento(), dados.bairro(),
                dados.localidade(), dados.uf(), dados.ibge(), dados.gia(), dados.ddd(), dados.siafi());
    }

    public void atualizarInformacoes(DadosAtualizacaoCliente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.nascimento() != null) {
            this.nascimento = dados.nascimento();
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
        if (dados.cep() != null && this.endereco != null) {
            this.endereco.atualizarCep(dados.cep());
        }

    }
}
