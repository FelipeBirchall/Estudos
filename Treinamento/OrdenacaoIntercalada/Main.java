package OrdenacaoIntercalada;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
     
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        int[] vet1 = new int[n1];
        for(int i = 0; i < n1; i++)
        {
            vet1[i] = sc.nextInt();
        }
        ordenar(vet1, n1);

        int n2 = sc.nextInt();
        int[] vet2 = new int[n2];
        for(int i = 0; i < n2; i++)
        {
            vet2[i] = sc.nextInt();
        }
        ordenar(vet2, n2);

        System.out.print("\nVetor 1: ");
        for(int i = 0; i < n1; i++)
        {
            System.out.print(vet1[i] + " ");
        }
        System.out.print("\nVetor 2: ");
        for(int i = 0; i < n2; i++)
        {
            System.out.print( vet2[i] + " ");
        }

        ordenacaoIntercalada(vet1, vet2);

    }

    static void ordenar(int[] vetor, int n)
    {
        for(int i = 0; i < n-1; i++)
        {
            int maior = i;
            for(int j = i+1; j < n; j++)
            {
                if(vetor[maior] < vetor[j]){maior = j;}
            }
            int temp = vetor[maior];
            vetor[maior] = vetor[i];
            vetor[i] = temp;
        }
    }

    static void ordenacaoIntercalada(int[] vet1, int[] vet2)
    {
        int tamanho = vet1.length + vet2.length;
        int[] vetorIntercalado = new int[tamanho];

        int i = vet1.length-1;
        int j = vet2.length-1;
        int k = 0;
        
        while(i >= 0 && j >= 0)
        {
            if(vet1[i] < vet2[j])
            {
                vetorIntercalado[k++] = vet1[i--];
            }
            else{
                vetorIntercalado[k++] = vet2[j--];
            }
        }
        while(i >= 0)
        {
            vetorIntercalado[k++] = vet1[i];
            i--;
        }
        while(j >= 0)
        {
            vetorIntercalado[k++] = vet2[j];
            j--;
        }
        System.out.print("\nVetor intercalado: ");
        for(int l = 0; l < tamanho; l++)
        {
            System.out.print(vetorIntercalado[l] + " ");
        }
    }
}
