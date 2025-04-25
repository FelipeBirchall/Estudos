package Countingsort;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] array = new int[n];

        for(int i = 0; i < n; i++)
        {
            array[i] = sc.nextInt();
        }

        countingsort(n, array);



    }

    static int getMaior(int[] array, int n)
    {
        int maior = 0;
        for(int i = 0; i < n; i++)
        {
            if(array[i] > maior){maior = array[i];}
        }
        return maior;
    }


    static void countingsort(int n, int[] array)
    {
        int[] count = new int[getMaior(array, n) + 1];
        int[] ordenado = new int[n];

        //Inicializar o vetor com posições zeradas
        for(int i = 0; i < count.length; count[i] = 0, i++);

        //fazer o contador
        for(int i = 0; i < n; count[array[i]]++, i++);

        for(int i = 1; i < count.length; count[i] += count[i-1], i++);

        for(int i = n-1; i >= 0; ordenado[count[array[i]]-1] = array[i], count[array[i]]--, i--);

        for(int i = 0; i < n; i++)
        {
            System.out.print(ordenado[i] + " ");
        }
    }


    
}
