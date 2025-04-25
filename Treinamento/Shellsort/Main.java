package Shellsort;
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

        shellsort(n, array);

        for(int i = 0; i < n; i++)
        {
            System.out.print(array[i] + " ");
        }

        sc.close();
    }



    // Ordenação Shellsort
    static void shellsort(int n , int[] array)
    {
        int h = 1;
        do{h = (h*3) + 1;}while(h < n);
        do{
            h /= 3;
            for(int cor = 0; cor < h; cor++)
            {
                insercaoPorCor(cor, n,  h, array);
            }
        }while(h != 1);
    }
    static void insercaoPorCor(int cor, int n, int h, int[] array)
    {
        for(int i = (h+cor); i < n; i+=h)
        {
            int tmp = array[i];
            int j = i - h;
            while((j >= 0) && array[j] > tmp)
            {
                array[j+h] = array[j];
                j -= h;
            }
            array[j+h] = tmp;
        }
    }
    
}
