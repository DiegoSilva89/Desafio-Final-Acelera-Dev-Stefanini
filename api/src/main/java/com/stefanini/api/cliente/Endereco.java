package com.stefanini.api.cliente;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    //Método para atualizar os dados com base no JSON da API ViaCEP
    public void atualizarEnderecoViaCEP(Endereco novoEndereco) {
        this.logradouro = novoEndereco.getLogradouro();
        this.complemento = novoEndereco.getComplemento();
        this.bairro = novoEndereco.getBairro();
        this.localidade = novoEndereco.getLocalidade();
        this.uf = novoEndereco.getUf();
        this.ibge = novoEndereco.getIbge();
        this.gia = novoEndereco.getGia();
        this.ddd = novoEndereco.getDdd();
        this.siafi = novoEndereco.getSiafi();
    }

    @PutMapping
    @Transactional
    public void atualizarCep(String novoCep) {
        //atualiza os demais campos do endereço com a atualização do cep
        ViaCEPClient viaCEPClient = new ViaCEPClient();
        Endereco novoEndereco = viaCEPClient.consultarEnderecoPorCep(novoCep);
        if (novoEndereco != null) {
            this.cep = novoCep;
            this.logradouro = novoEndereco.getLogradouro();
            this.complemento = novoEndereco.getComplemento();
            this.bairro = novoEndereco.getBairro();
            this.localidade = novoEndereco.getLocalidade();
            this.uf = novoEndereco.getUf();
            this.ibge = novoEndereco.getIbge();
            this.gia = novoEndereco.getGia();
            this.ddd = novoEndereco.getDdd();
            this.siafi = novoEndereco.getSiafi();
        }
    }


    public String enderecoCompleto() {
        return "Logradouro: " + logradouro + ", " + "Bairro: " + bairro + ", " + "Cidade - uf: " + localidade + " - " + uf;
    }

}

