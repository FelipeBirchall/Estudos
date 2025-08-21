package com.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
    private static final String ARQUIVO_JSON = "dados.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Scanner sc = new Scanner(System.in);
    public static void main( String[] args )
    {
        List<Pessoa> pessoas = carregarDados();
        while(true){
            System.out.println("\n=== Menu ===");
            System.out.println("1. Cadastrar pessoa");
            System.out.println("2. Listar pessoas");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: "); 
            int opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 1:
                    cadastrarPessoa(pessoas);
                    break;
                case 2:
                    listarPessoas(pessoas);
                    break;
                case 3:
                    salvarDados(pessoas);
                    System.out.println("Dados salvos no JSON!");
                    System.out.println("PROGRAMA FINALIZADO!");
                    return;
            } 
        }
    }

    //CARREGAR DADOS DO ARQUIVO JSON
    private static List<Pessoa> carregarDados(){
        File arquivo = new File(ARQUIVO_JSON);
        if(!arquivo.exists()){
            return new ArrayList<>();
        }
        try(Reader reader = new FileReader(arquivo)){
            return gson.fromJson(reader, new TypeToken<List<Pessoa>>(){}.getType());
        } catch (IOException e){
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    //CADASTRO
    private static void cadastrarPessoa(List<Pessoa> pessoas){
        System.out.println("\n--- Cadastrar Pessoa ---");
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        
        System.out.println("Idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        System.out.println("Gênero: ");
        String genero = sc.nextLine();

        System.out.println("Altura: ");
        double altura = sc.nextDouble();

        pessoas.add(new Pessoa(nome, idade, genero, altura));
        System.out.println("Pessoa cadastrada com sucesso!");
    }

    //LISTAR
    private static void listarPessoas(List<Pessoa> pessoas){
        if(pessoas.isEmpty()){
            System.out.println("Nenhuma pessoa foi cadastrada!");
            return;
        }
        System.out.println("\n--- Pessoas Cadastradas ---");
        for(Pessoa p : pessoas){
            System.out.println(p);
        }

    }

    //SALVAR DADOS NO ARQUIVO JSON
    private static void salvarDados(List<Pessoa> pessoas){
        try(Writer writer = new FileWriter(ARQUIVO_JSON)){
            gson.toJson(pessoas, writer);
        } catch(IOException e){
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
    
}

