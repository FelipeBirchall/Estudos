package Quicksort;
import java.util.Scanner;

public class Main{

    static int[] array;
    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int n = 100;
        
        int[] vetor = new int[n];

        int val = n;
        
        for(int i = 0; i < n; i++)
        {
            vetor[i] = val;
            val--;
        }
        for(int i = 0 ; i < n; i++)
        {
            System.out.print(vetor[i] + " ");
        }
        System.out.println("\n");
        quicksort(0, n-1, vetor);
        for(int i = 0 ; i < n; i++)
        {
            System.out.print(vetor[i] + " ");
        }


        scanner.close();

        
    }


    static void quicksort(int esq, int dir , int[] vetor)
    {
        int i = esq , j = dir, pivo = vetor[(dir+esq)/2];
        while(i <= j)
        {
            while(vetor[i] < pivo)
            {
                i++;
            }
            while(vetor[j] > pivo)
            {
                j--;
            }
            if(i <= j)
            {
                int temp = vetor[j];
                vetor[j] = vetor[i];
                vetor[i] = temp;
                i++;
                j--;
            }
            if(esq < j)
            {
                quicksort(esq, j, vetor);
            }
            if(dir > i)
            {
                quicksort(i, dir, vetor);
            }
        }
    }
    
    
}