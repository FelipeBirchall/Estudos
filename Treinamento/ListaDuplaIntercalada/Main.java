package ListaDuplaIntercalada;

import java.util.Scanner;

class CelulaDupla{
    public int elemento;
    public CelulaDupla prox, ant;

    public CelulaDupla()
    {
        this.elemento = 0;
        this.prox = this.ant = null;
    }
    public CelulaDupla(int x)
    {
        this.elemento = x;
        this.prox = this.ant = null;
    }
}

class ListaDupla{
    public CelulaDupla primeiro, ultimo;

    public ListaDupla()
    {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    public void inserir(int x)
    {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void mostrar()
    {
        System.out.print("[ ");
        for(CelulaDupla i = primeiro.prox; i != null; i = i.prox)
        {
            System.out.print(i.elemento + " ");
        }
        System.out.print("]");

    }

    public int tamanho() {
        int tam = 0;
        for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tam++);
        return tam;
     }
}


public class Main {
    public static void main(String[] args) {
        
       Scanner sc = new Scanner(System.in);
       ListaDupla[] listadupla = new ListaDupla[2];
       int entrada, opcao = -1;
       listadupla[0] = new ListaDupla();
       listadupla[1] = new ListaDupla();
       while(opcao != 0)
        {
            System.out.println("\n------- MENU -------\n");
            System.out.println("Inserir 3");
            System.out.println("Intercalar - 2");
            System.out.println("Mostrar - 1");
            System.out.println("Finalizar - 0");
            System.out.print("Opção: ");
            opcao = sc.nextInt();

            switch(opcao) {
                case 3:
                    System.out.println("Lista 1");
                    System.out.println("Lista 2");
                    System.out.print("Opção: ");
                    int subopcao = sc.nextInt();

                    switch (subopcao) {
                        case 1:
                            entrada = sc.nextInt();
                            listadupla[0].inserir(entrada);
                            break;
                    
                        case 2:
                            entrada = sc.nextInt();
                            listadupla[1].inserir(entrada);
                            break;
                    }
                    break;
                
                case 2:
                    Intercalar(listadupla[0], listadupla[1]);
                    break;
                case 1:
                    System.out.print("Lista 1: ");
                    listadupla[0].mostrar();
                    System.out.println();
                    System.out.print("Lista 2: ");
                    listadupla[1].mostrar();
                    break;

                case 0:
                    System.out.println("FINALIZADO!");
                    break;
            }
            
        }
       
       sc.close();
    }


    static void Intercalar(ListaDupla lista1 , ListaDupla lista2)
    {
        ListaDupla listaIntercalada = new ListaDupla();
        
        CelulaDupla i = lista1.ultimo;
        CelulaDupla j = lista2.ultimo;
        
        while((i != lista1.primeiro) || (j != lista2.primeiro))
        {
            if(i != lista1.primeiro)
            {
                listaIntercalada.inserir(i.elemento);
                i = i.ant;
            }
            if(j != lista2.primeiro)
            {
                listaIntercalada.inserir(j.elemento);
                j = j.ant;
            }
        }
        System.out.println("Lista intercalada: ");
        for(CelulaDupla k = listaIntercalada.primeiro.prox; k != null; k = k.prox)
        {
            System.out.print(k.elemento + " ");
        }
    }
    
}
