package Quicksort;
import java.util.Scanner;

public class Main{

    static int[] array;
    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Tamanho do array: ");
        int n = scanner.nextInt();
        array = new int[n];

        for(int i = 0; i < n; i++)
        {
            System.out.print("Valor "+ i + " :");
            array[i] = scanner.nextInt();
        }

        quicksort();

        System.out.print("Valor que deseja encontrar: ");
        int x = scanner.nextInt();

        boolean resp = Pesquisa_Binaria(array , x);

        if(resp){System.out.println("O valor "+ x + " foi encontrado!");}
        else{ System.out.println("O valor "+ x + " não foi encontrado!");}

        scanner.close();

        
    }
    // Método principal do QuickSort
    static void quicksort() {
        quicksort(0, array.length - 1);
    }

    // Método recursivo do QuickSort
    static void quicksort(int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[(esq + dir) / 2]; // Escolhe o pivô como o elemento do meio

        while (i <= j) {
            // Encontra o próximo elemento à esquerda que é maior ou igual ao pivô
            while (array[i] < pivo) {
                i++;
            }
            // Encontra o próximo elemento à direita que é menor ou igual ao pivô
            while (array[j] > pivo) {
                j--;
            }
            // Se os índices não se cruzaram, troca os elementos
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        // Recursão para ordenar a parte esquerda
        if (esq < j) {
            quicksort(esq, j);
        }
        // Recursão para ordenar a parte direita
        if (i < dir) {
            quicksort(i, dir);
        }
    }

    // Método para trocar dois elementos do array
    static void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static boolean Pesquisa_Binaria(int array[], int x)
    {
          boolean resp = false;
          int dir = array.length - 1,  esq = 0 , meio , diferenca;
          while(esq <= dir)
          {
              meio = (dir+esq)/2;
              diferenca = (x - array[meio]);
              if(diferenca == 0)
              {
                resp = true;
                esq = array.length;
              }
              else if(diferenca > 0)
              {
                esq = meio+1;
              }
              else
              {
                 dir = meio+1;
              }
          }
          return resp;
    }
    
    
}