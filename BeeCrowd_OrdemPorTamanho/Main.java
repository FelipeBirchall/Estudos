package BeeCrowd_OrdemPorTamanho;
import java.util.Scanner;


public class Main{


    static int numPalavras(String frase)
    {
        int cont =  1;
        for(int i = 0; i < frase.length(); i++)
        {
            if(frase.charAt(i) == ' ')
            {
                cont++;
            }
        }
        return cont;
    }

    static void separarPalavrasEOrdenar(String frase , int vetor)
    {
        String[] palavras = new String[vetor];
        String palavra = "";
        int index = 0;
        for(int i = 0; i < frase.length(); i++)
        {
            if(frase.charAt(i) == ' ')
            {
                palavras[index++] = palavra;
                palavra = "";
            }
            else{
                palavra += frase.charAt(i);
            }
        }
        palavras[index] = palavra;

        for(int i = 0; i < (vetor -1); i++)
        {
            int menor = i;
            for(int j = (i+1); j < vetor; j++)
            {
                if(palavras[j].length() > palavras[menor].length())
                {
                    menor = j;
                }
            }
            if(menor != i)
            {
                String temp = palavras[i];
                palavras[i] = palavras[menor];
                palavras[menor] = temp;
            }
            
        }

        for (int i = 0; i < vetor; i++) {
            System.out.print(palavras[i] + " ");
        }
    }

    static public void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);
        int N =  sc.nextInt();
        sc.nextLine();
        int[] vetor = new int[N]; // Irá guardar o numero de palavras de cada frase
        String[] frase =  new String[N];

        for(int i = 0; i < N; i++)
        {
            frase[i] = sc.nextLine();
            vetor[i] = numPalavras(frase[i]);

        }
        for(int i = 0; i < N; i++)
        {
           separarPalavrasEOrdenar(frase[i], vetor[i]);
           System.out.println();
        }
        
        sc.close();

    }
}