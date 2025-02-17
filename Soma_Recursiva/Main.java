package Soma_Recursiva;

import java.util.*;

public class Main{

    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        int num = scanner.nextInt();

        int soma = Soma(num, 0);

        System.out.println(soma);
        
        scanner.close();
    }

    static int Soma(int num , int soma)
    {
        if(num > 0)
        {
            soma = soma + (num % 10);
            num = num / 10;
            return Soma(num , soma); 
        }
        return soma;
    }
}