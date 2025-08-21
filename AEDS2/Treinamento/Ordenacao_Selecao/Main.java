package Ordenacao_Selecao;

import java.util.Scanner;

public class Main{

    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Tamanho do array: ");
        int n = scanner.nextInt();
        int[] array = new int[n];

        for(int i = 0; i < n; i++)
        {
            System.out.print("Valor " + i + " : ");
            array[i] = scanner.nextInt();
        }
        
        ordenar(array);

        System.out.print("Valor que deseja encontrar: ");
        int x = scanner.nextInt();

        boolean resp = resposta(x, array, n);
        if(resp){System.out.print("O valor " + x + " foi encontrado!");}
        else {System.out.print("O valor " + x + " não foi encontrado!");}
        

        scanner.close();  
        
    }

    // Ordenação Por Seleção
    static void ordenar(int array[])
    {
        for(int i = 0; i < array.length - 1; i++)
        {
           int menor = i;
           // Encontra o índice do menor elemento na parte não ordenada
           for(int j = i+1; j < array.length; j++)
           {
              if(array[j] < array[menor]){menor = j;}
           }

           // Troca o menor elemento encontrado com o elemento na posição atual
           int temp = array[menor];
           array[menor] = array[i];
           array[i] = temp; 
        }
        
        /*
         for(int i = 0; i < array.length; i++)
          {
            System.out.println(array[i]);
          }
        */
        
    }

    //Pesquisa Binária
    static boolean resposta(int x , int array[] , int n)
    {
        boolean resp = false;
        int dir = n - 1, esq = 0, meio, diferença;
        while (esq <= dir) 
        {
        meio = (esq + dir) / 2;
        diferença = (x - array[meio]);
        if (diferença == 0)
        {
        resp = true;
        esq = n;
        } 
        else if (diferença > 0){ esq = meio + 1; }
         else{ dir = meio - 1; }
        }
        return resp;
    }
}


