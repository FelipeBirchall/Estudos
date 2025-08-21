package com.example;

public class Pessoa {
    private String nome;
    private int idade;
    private String genero;
    private double altura;

    public Pessoa(String nome, int idade, String genero, double altura){
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.altura = altura;
    }
    @Override
    public String toString(){
        return "Nome: " + nome + ", Idade: " + idade + ", Gênero: " + genero + ", Altura: " + altura;
    }
    
}
