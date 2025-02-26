package PesquisaBinariaRecursiva;
import java.util.*;

public class Main {

    static int numComparacoes = 0;
    static public void main(String[] args)
    {

        Scanner scanner = new Scanner(System.in);

        int n = 10;

        int[] vetor = new int[n];

        for(int i = 0; i < n; i++)
        {
            System.out.print("Valor "+ (i+1) + ": ");
            vetor[i] = scanner.nextInt();
        }
        System.out.println("Valor a ser procurado: ");
        int x = scanner.nextInt();

        if(pesquisaBinaria(vetor, 0, n-1, x, n))
        {
            System.out.println("Valor encontrado");
        }
        else{
            System.out.println("Valor não encontrado");
        }

        System.out.println(numComparacoes);

        scanner.close();
    }

    static boolean pesquisaBinaria(int vetor[] , int esq , int dir ,int x , int n)
    {
        boolean encontrado = false;
        if(esq <= dir)
        {
            int meio = (dir + esq) / 2;
            if(x == vetor[meio])
            {
                numComparacoes++;
                encontrado = true; esq = n;
            }  
            else if(x > vetor[meio])
            {
                numComparacoes++;
                return pesquisaBinaria(vetor, meio + 1, dir, x, n);
            }
            else{
                return pesquisaBinaria(vetor, esq, meio - 1, x, n);
            }
        }
        return encontrado;
    }
    
    
    
}
