package app;

import java.util.Scanner;


import model.Data;
import model.Funcionario;

public class Main{

    static public void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
   
            String nome = sc.nextLine();  
            String genero = sc.nextLine();

            String dataN = sc.nextLine();
            String[] partes = dataN.split("/");
            int[] dados = new int[3];
            for(int j = 0; j < 3; j++){
                dados[j] = Integer.parseInt(partes[j]);
            }
            Data data = new Data(dados[0], dados[1], dados[2]);            
            
            String email = sc.nextLine();
            String endereco = sc.nextLine();

            String cargo = sc.nextLine();
            
            float salario = sc.nextFloat();

            Funcionario funcionario = new Funcionario(nome, genero, data, email, endereco, cargo, salario);

            funcionario.exibirDados();

        
        sc.close();

    }

}

 