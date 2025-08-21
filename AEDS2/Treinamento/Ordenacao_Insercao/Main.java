package Ordenacao_Insercao;
import java.util.Scanner;

public class Main{

    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Tamanho do array: ");
        int n = scanner.nextInt();
        int[] array = new int[n];

        for(int i = 0 ; i < n; i++)
        {
          System.out.print("Valor " +i + " : ");
          array[i]= scanner.nextInt();   
        }

        ordenar(array);

        System.out.print("Valor que deseja encontrar: ");
        int x = scanner.nextInt();

        boolean resp = Pesquisa_Binaria(array, x);

        if(resp){ System.out.println("O valor " + x + " foi encontrado!");}
        else{ System.out.println("O valor "+ x + " não foi encontrado!");}

        scanner.close();
        
    }

    //Ordenação Por Inserção
    static void ordenar(int array[])
    {
        for(int i = 1; i < array.length; i++)
        {
            int tmp = array[i];
            int j = i-1;
            while((j >= 0) && array[j] > tmp)
            {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = tmp;
        }
        
        /*
        for(int i = 0; i < array.length; i++)
        {
            System.out.println(array[i]);
        } 
        */
        
    }

    //Pesquisa Binária
    static boolean Pesquisa_Binaria(int array[] , int x)
    {
        boolean resp = false;
        int dir = array.length - 1, esq = 0 , meio , diferenca;
        while(esq <= dir)
        {
            meio = (esq+dir) / 2;
            diferenca = (x - (array[meio]));
            if(diferenca == 0){
                resp = true;
                esq = array.length;
            }
            else if(diferenca > 0)
            {
                esq = meio+1;
            }
            else{
                dir = meio-1;
            }
        }
        return resp;
    }
    
}