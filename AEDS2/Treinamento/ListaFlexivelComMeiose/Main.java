package ListaFlexivelComMeiose;
import java.util.Scanner;

class Celula{
    int elemento;
    Celula prox;
    public Celula()
    {
        this.elemento = 0;
        this.prox = null;
    }
    public Celula(int x)
    {
        this.elemento = x;
        this.prox = null;
    }
}

class Lista{
    Celula primeiro, ultimo;

    public Lista()
    {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserir(int x)
    {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }
    
    public void mostrar()
    {
        for(Celula i = primeiro.prox; i != null; i = i.prox)
        {
            System.out.print(i.elemento + " ");
        }
    }

    public void meiose()
    {
    Celula i = primeiro.prox;
    while (i != null) {
        int metade = i.elemento / 2;

        i.elemento = metade;

        Celula nova = new Celula(metade);

        nova.prox = i.prox;
        i.prox = nova;

        if (nova.prox == null) {
            ultimo = nova;
        }

        i = nova.prox;
    }
    }

    
}

public class Main {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Lista lista = new Lista();

        lista.inserir(8);
        lista.inserir(5);
        lista.inserir(6);
        lista.inserir(1);

        System.out.println("Antes da meiose: ");
        lista.mostrar();
        
        System.out.println("\nApós a meiose: ");
        lista.meiose();
        lista.mostrar();
    }

    /* 
    static void meiose(Lista lista)
    {
        Lista lista_meiose = new Lista();
        for(Celula i = lista.primeiro.prox; i != null; i = i.prox)
        {
            int meiose = i.elemento/2;
            lista_meiose.ultimo.prox = new Celula(meiose);
            lista_meiose.ultimo = lista_meiose.ultimo.prox;
            lista_meiose.ultimo.prox = new Celula(meiose);
            lista_meiose.ultimo = lista_meiose.ultimo.prox;
        }
        for(Celula j = lista_meiose.primeiro.prox; j != null; j = j.prox)
        {
            System.out.print(j.elemento + " ");
        }
    }
    */
}
