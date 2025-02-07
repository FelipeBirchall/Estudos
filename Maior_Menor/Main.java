package Maior_Menor;
import java.util.Scanner;

public class Main{

    static Scanner scanner = new Scanner(System.in);
    static public void main(String[] args)
    {
        int n;
        
        System.out.print("Tamanho do vetor: ");
        n = scanner.nextInt();

        int[] vetor = new int[n];
        leitura(n, vetor);

        int maior = maiorValor(n, vetor);
        int menor = menorValor(n, vetor);
        
        System.out.print("\nMaior valor: "+ maior);
        System.out.print("\nMenor valor: "+ menor);
        
    }

    static void leitura(int n, int vetor[])
    {
        for(int i = 0; i < n; i++)
        {
           System.out.print("Valor " + i + ": " );
           vetor[i] = scanner.nextInt();
        }
    }

    static int maiorValor(int n , int vetor[])
    {
        int maior = vetor[0];
        for(int i = 1; i < n; i++)
        {
            if(vetor[i] > maior){maior = vetor[i];}
        }
        return maior;
    }

    static int menorValor(int n , int vetor[])
    {
        int menor = vetor[0];
        for(int i = 1; i < n; i++)
        {
            if(vetor[i] < menor){menor = vetor[i];}
        }
        return menor;
    }
}