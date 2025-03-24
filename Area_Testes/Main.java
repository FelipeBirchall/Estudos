package Area_Testes;
import java.util.Scanner;


public class Main{


    static void Ordenar(int[] vetor , int N)
    {
        for(int i = 0; i < N-1; i++)
        {
            int menor = i;
            for(int j = i+1; j < N; j++)
            {
                if(vetor[menor] > vetor[j])
                {
                    menor = j;
                }
            }
            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }


    }
    static public void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] vetor = new int[N];

        for(int i = 0; i < N;i++)
        {
            vetor[i] = sc.nextInt();
        }
        Ordenar(vetor, N);
        for(int i = 0; i < N; i++)
        {
            System.out.print(vetor[i] + " ");
        }
    }
}