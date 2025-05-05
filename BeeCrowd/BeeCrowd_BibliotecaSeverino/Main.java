package BeeCrowd_BibliotecaSeverino;

import java.util.Scanner;


class Lista{
    String[] valores;
    int n;
    int index = 0;

    public Lista(int n)
    {
        this.n = n;
        valores = new String[n];
    }

    public void setValor(String valor)
    {
        this.valores[index] = valor;
        index++;
    }

    public String getValor(int i)
    {
        String valor = "";
        valor = valores[i];
        return valor;
    }

    public void swap(int menor, int i)
    {
        String temp = valores[menor];
        valores[menor] = valores[i];
        valores[i] = temp;
    }

    public void imprimir()
    {
        for(int i = 0; i < n; i++)
        {
            System.out.println(valores[i]);
        }
    }
}


public class Main{
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Lista[] lista = new Lista[100];
        int n = -1, index = 0;

        while(n != 0)
        {
            n = sc.nextInt();
            lista[index] = new Lista(n);
            String valor = "";
    
            for(int i = 0; i < n; i++)
            {
               valor = sc.next();
               lista[index].setValor(valor);    
            }
            ordenar(n, lista[index]);
            index++;
        }
        for(int i = 0; i < index; i++)
        {
            lista[i].imprimir();
        }
        
        
    }


    static void ordenar(int n, Lista lista)
    {
        for(int i = 0; i < n-1; i++)
        {
            int menor = i;
            for(int j = i+1; j < n; j++)
            {
                if(lista.getValor(menor).compareTo(lista.getValor(j)) > 0){menor = j;}
            }
            lista.swap(menor, i);
        }
    }

   
}

