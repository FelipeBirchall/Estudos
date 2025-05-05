package FilaDePilhasFlexivel;

import java.util.Scanner;


class CelulaLista{
    CelulaPilha topo;
    CelulaLista prox;

    public CelulaLista()
    {
        this.topo = null;
        this.prox = null;
    }
}

class CelulaPilha{
    int elemento;
    CelulaPilha prox;

    public CelulaPilha()
    {
        this.elemento = 0;
        this.prox = null;  
    }
    public CelulaPilha(int x)
    {
        this.elemento = x;
        this.prox = null;
    }
}

class Lista{
    CelulaLista primeiro, ultimo;
    public Lista()
    {
        primeiro = new CelulaLista();
        ultimo = primeiro;
    }

    public void inserirPilhaVazia()
    {
        ultimo.prox = new CelulaLista();
        ultimo = ultimo.prox;
    }

    public void inserirNaPilha(int index, int x)
    {
        CelulaLista aux = primeiro.prox;
        for(int i = 0; i < index && aux != null;i++ , aux = aux.prox);
        if(aux != null)
        {
            CelulaPilha nova = new CelulaPilha(x);
            nova.prox = aux.topo;
            aux.topo = nova;
            nova = null;
            aux = null;
        }
        else{
            System.out.println("Índice inválido!");
        }
    }

    public void imprimir() {
        CelulaLista auxLista = primeiro.prox;
        int contador = 0;

        while (auxLista != null) {
            System.out.print("Pilha " + contador + ": ");
            CelulaPilha auxPilha = auxLista.topo;
            while (auxPilha != null) {
                System.out.print(auxPilha.elemento + " ");
                auxPilha = auxPilha.prox;
            }
            System.out.println();
            contador++;
            auxLista = auxLista.prox;
        }
    }

    public int tamanhoLista()
    {
        int tamanho = 0;
        for(CelulaLista i = primeiro.prox; i != null; i = i.prox)
        {
            tamanho++;
        }
        return tamanho;
    }
    
    public int maiorPilha()
    {
        int tam = tamanhoLista();
        CelulaLista i = primeiro.prox;
        int[] pilhas = new int[tam];
        for(int j = 0; j < tam && i != null;j++, i = i.prox)
        {
            int tamPilha = 0;
            for(CelulaPilha k = i.topo; k != null; k = k.prox)
            {
                tamPilha++;
            }
            pilhas[j] = tamPilha;
        }
        int maior = 0;
        for(int j = 0; j < tam; j++)
        {
            if(pilhas[j] > maior){maior = j;}
        }
        return maior;
    }
}

class Pilha{
    int elemento;
    CelulaPilha topo;

    public Pilha()
    {
        this.elemento = 0;
        this.topo = null;
    }
    public Pilha(int x)
    {
        this.elemento = x;
        this.topo = null;
    }
    public void inserir(int x)
    {
        CelulaPilha tmp = new CelulaPilha(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }
}

public class Main {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Lista lista = new Lista();

        // Cria 3 pilhas
        lista.inserirPilhaVazia();
        lista.inserirPilhaVazia();
        lista.inserirPilhaVazia();

        // Insere valores nas pilhas
        lista.inserirNaPilha(0, 10);
        lista.inserirNaPilha(0, 20);
        lista.inserirNaPilha(1, 30);
        lista.inserirNaPilha(2, 40);
        lista.inserirNaPilha(2, 50);
        lista.inserirNaPilha(2, 60);

        System.out.println("Maior Pilha: " + lista.maiorPilha());
        // Exibe as pilhas
        lista.imprimir();

        sc.close();

    }
    
}
