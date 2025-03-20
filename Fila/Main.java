package Fila;
import java.util.Scanner;

class Fila{

    int[] array;
    int primeiro , ultimo;

    Fila(int tamanho)
    {
         array = new int[tamanho+1];
         primeiro = ultimo = 0;
    }

   public void inserir(int x) throws Exception
    {

        if((ultimo + 1) % array.length == primeiro)
        {
            throw new Exception("Erro!");
        }
        array[ultimo] = x;
        ultimo = (ultimo+1)% array.length;
        
    }

   public int remover() throws Exception
   {
    if(primeiro == ultimo)
    {
       throw new Exception("Erro!");
    }
    int resp =array[primeiro];
    primeiro = (primeiro+1) % array.length;

    return resp;
   }

   public void mostrar()
   {
     int i = primeiro;
     
     System.out.print("[ ");
     while(i != ultimo)
     {
        System.out.print(array[i] + " ");
        i = (i+1) % array.length;
     }
    System.out.print("]");
   }
}


public class Main{


   
    static public void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);

        Fila fila = new Fila(5);

        fila.inserir(1);
        fila.inserir(2);
        fila.inserir(3);
        System.out.println(fila.remover());
        fila.mostrar();

    }
}