package com.stefanini.api.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ViaCEPClient {

    public Endereco consultarEnderecoPorCep(String cep) {
        try {
            //Montar a URL da API ViaCEP
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json");

            //Abrir conexão HTTP
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            //Ler a resposta JSON
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //Verificar se o JSON contém o campo "erro"
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.toString());
            if (jsonNode.has("erro")) {
                //CEP não encontrado, retornar null ou um Endereco vazio
                return null;
            }

            //Analisar a resposta JSON e preencher o Endereco
            Endereco endereco = mapper.readValue(response.toString(), Endereco.class);

            return endereco;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}