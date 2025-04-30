package ListaFlexivelDupla;

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
    private CelulaDupla primeiro, ultimo;

    public ListaDupla()
    {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }
    public void inserirInicio(int x)
    {
        CelulaDupla tmp = new CelulaDupla(x);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo)
        {
            ultimo = tmp;
        }
        else{
            tmp.prox.ant = tmp;
        }
        tmp = null;
    }

    public void inserirFim(int x)
    {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void inserir(int x, int pos)
    {
        if(pos == 0){inserirInicio(x);}
        if(pos == tamanho()){inserirFim(x);}
        else{
            CelulaDupla i = primeiro;
            for(int j = 0; j < pos; j++ , i = i.prox);
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
    }

    public int removerInicio()
    {
        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;
        int elemento = primeiro.elemento;
        tmp.prox = primeiro.ant = null;
        tmp = null;
        return elemento;
    }

    public int removerFim()
    {
        int elemento = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        return elemento;
    }

    public int remover(int pos)
    {
        int elemento;
        if(pos == 0){elemento  = removerInicio();}
        if(pos == tamanho() - 1){elemento = removerFim();}
        else{
            CelulaDupla i = primeiro;
            for(int j = 0; j <= pos; j++ , i = i.prox);
            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            elemento = i.elemento;
            i.prox = i.ant = null;
            i = null;
        }
        return elemento;
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
       ListaDupla listadupla = new ListaDupla();
       int entrada, opcao = -1;
       int pos;
       while(opcao != 0)
        {
            System.out.println("\n------- MENU -------\n");
            System.out.println("Inserir Inicio - 7");
            System.out.println("Inserir Fim - 6");
            System.out.println("Inserir - 5");
            System.out.println("Remover Inicio - 4");
            System.out.println("Remover Fim - 3");
            System.out.println("Remover - 2");
            System.out.println("Mostrar - 1");
            System.out.println("Finalizar - 0");
            System.out.print("Opção: ");
            opcao = sc.nextInt();

            switch(opcao) {
                case 7:
                    entrada = sc.nextInt();
                    listadupla.inserirInicio(entrada);
                    break;
            
                case 6:
                    entrada = sc.nextInt();
                    listadupla.inserirFim(entrada);
                    break;
                
                case 5:
                    entrada = sc.nextInt();
                    pos = sc.nextInt();
                    listadupla.inserir(entrada, pos);
                    break;
                
                case 4:
                    System.out.println(listadupla.removerInicio());
                    break;

                case 3:
                    System.out.println(listadupla.removerFim());
                    break;
                  
                case 2:
                    pos = sc.nextInt();
                    System.out.println(listadupla.remover(pos));
                    break;

                case 1:
                    listadupla.mostrar();
                    break;

                case 0:
                    System.out.println("FINALIZADO!");
                    break;
            }
            
        }
       
       sc.close();
    }
    
}
