package Cadastros;
import java.util.ArrayList;
import java.util.Scanner;

class Pessoa{

    String nome;
    int idade;
    String genero;

    public Pessoa(String nome , int idade , String genero)
    {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
    }
    
    public void exibirDados()
    {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Gênero: " + genero + "\n");
        
    }


}

public class Main{

    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Número de cadastros: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        for(int i = 0; i < n; i++)
        {
            System.out.println("\n----- CADASTRO " + (i+1) + " -----");
            
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Gênero: ");
            String genero = scanner.nextLine();

            Pessoa pessoa = new Pessoa(nome , idade , genero);
            pessoas.add(pessoa);
        }

        System.out.println("\n----- PESSOAS CADASTRADAS ------");

        for(Pessoa pessoa : pessoas)
            {
               pessoa.exibirDados();
            }

        scanner.close();

    }

}

 