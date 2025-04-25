package Bubble_Sort;
import java.util.Scanner;


public class Main{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] vetor = new int[n];
        
        for(int i = 0; i < n; i++)
        {
            vetor[i] = sc.nextInt();
        }

        boolean teveTroca = true;

        for(int i = 0; i < n-1 && teveTroca; i++)
        {
            for(int b = 0; b < n-(i+1); b++)
            {
                teveTroca = false;
                if(vetor[b] > vetor[b+1])
                {
                    int temp = vetor[b];
                    vetor[b] = vetor[b+1];
                    vetor[b+1] = temp;
                    teveTroca = true;
                }

            }
        }

        for(int i = 0; i < n; i++)
        {
            System.out.print(vetor[i] + " ");
        }
        sc.close();
    }
}
