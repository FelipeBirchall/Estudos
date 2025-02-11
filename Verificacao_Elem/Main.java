package Verificacao_Elem;
import java.util.Scanner;


public class Main{
   
   static Scanner scanner = new Scanner(System.in);
  static public void main(String[] args)
  {
      int n = tamanho();
      int[] array = new int[n];
      leitura(array, n);
      int x = numDesejado();      
      boolean resposta = procurar(array, n, x);
      if(resposta)
      {
         System.out.println("O número " + x + " foi encontrado!");
      }
      else{System.out.println("O número não foi encontrado!");} 
  }

  static int tamanho() //tamanho do vetor//
  {
    System.out.print("\nDigite o tamanho do vetor: ");
    return scanner.nextInt();
  }

  static void leitura(int array[] , int n) //leitura dos valores//
  {
     for(int i = 0; i < n; i++)
     {
        System.out.println("\nDigite o valor " + i + ":");
        array[i] = scanner.nextInt(); 
     }
  }

  static int numDesejado()
  {
    System.out.println("\nNúmero que deseja procurar: ");
    return scanner.nextInt();
  }

  static boolean procurar(int array[] , int n , int x)
  {
     for(int i = 0; i < n; i++)
     {
      if(array[i] == x)
      {
         return true;
      }
     }
     return false;

  }

}