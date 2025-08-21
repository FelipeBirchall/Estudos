package com.treino1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o CEP (Ex: 01001-000): ");
        String cep = sc.nextLine();
        cep = cep.replaceAll("\\D", "");
        if(cep.length()!= 8){
            System.out.println("CEP invalido!");
            return;
        }

        try{
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != 200){
                System.out.println("Falha na requisição: HTTP " + response.statusCode());
                return;
            }
            String json = response.body();
            ObjectMapper mapper = new ObjectMapper();
            ViaCepResponse dados = mapper.readValue(json, ViaCepResponse.class);

            if(Boolean.TRUE.equals(dados.getErro())){
                System.out.println("CEP não encontrado!");
                return;
            }
            System.out.println("CEP:" + safe(dados.getCep()));
            System.out.println("Logradouro:" + safe(dados.getLogradouro()));
            System.out.println("Complemento:" + safe(dados.getComplemento()));
            System.out.println("Bairro:" + safe(dados.getBairro()));
            System.out.println("Cidade:" + safe(dados.getLocalidade()));
            System.out.println("UF:" + safe(dados.getUf()));
            System.out.println("DDD:" + safe(dados.getDdd()));
        }
        catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
        sc.close();
    }

    static String safe(String s){
        return (s == null) ? "-" : s;
    }
}
