import java.util.ArrayList;
import java.util.Scanner;

class Celula{
    No no;
    Celula prox;

    public Celula(No no) {
        this.no = no;
        this.prox = null;
    }
}

class Lista{
    Celula primeiro, ultimo;

    public Lista(){
        this.primeiro = this.ultimo = null;
    }

    public void inserir(No no){
        if(primeiro == null){
            primeiro = new Celula(no);
            ultimo = primeiro;
        }
        else{
            ultimo.prox = new Celula(no);
            ultimo = ultimo.prox;
        }
    }

    public Celula buscar(char c){
        Celula atual = primeiro;
        while (atual != null) {
            if (atual.no.elemento == c) {
                return atual;
            }
            atual = atual.prox;
        }
        return null;
    }
    
}

class No{
    public char elemento;
    public Lista lista;
    public boolean folha;

    public No() {
        this.elemento = ' ';
        this.lista = new Lista();        // Inicializa a lista de filhos
        this.folha = false;
    }
    
    public No(char elemento){
        this.elemento = elemento;
        lista = new Lista();
        folha = false;
    }

    public static int hash (char x){
      return (int)x;
    }
}

// Arvore TRIE
class Arvore{
    private No raiz;

    public Arvore(){
        raiz = new No();
    }

    public void inserir(String s){
        inserir(s, raiz, 0);
    }
    private void inserir(String s, No no, int i){
        char c = s.charAt(i);
        Celula filho = no.lista.buscar(c);
        if(filho == null){
            No novoNo = new No(c);
            no.lista.inserir(novoNo);
        }
        if(i == s.length() - 1){
            filho.no.folha = true;
        }
    }

    public boolean pesquisar(String s){
        return pesquisar(s, raiz, 0);
    }
    private boolean pesquisar(String s, No no, int i){
        boolean resp = true;
        if(no.prox[s.charAt(i)] == null){
            resp = false;
        }
        else if(i == s.length() - 1){
            resp = (no.prox[s.charAt(i)].folha == true);
        }
        else if(i < s.length() - 1){
            resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
        }
        return resp;
    }

    public Arvore intersecao(Arvore a1, Arvore a2){
        Arvore intercalada = new Arvore();

        ArrayList<String> palavras1 = a1.coletarPalavras();
        ArrayList<String> palavras2 = a2.coletarPalavras();

        for(String palavra : palavras1){
            intercalada.inserir(palavra);
        }
        for(String palavra : palavras2){
            intercalada.inserir(palavra);
        }

        return intercalada;
    }

    public ArrayList<String> coletarPalavras(){
        ArrayList<String> palavras = new ArrayList<>();
        coletarPalavras("", raiz, palavras);
        return palavras;
    }
    public void coletarPalavras(String s, No no, ArrayList<String> palavras){
        if(no.folha == true){
            palavras.add(s + no.elemento);
        }
        else{
            for(int i = 0; i < no.prox.length; i++){
                if(no.prox[i] != null){
                    coletarPalavras(s + no.elemento,no.prox[i], palavras);
                }
            }
        }
    }

    public void mostrar(){
        mostrar("", raiz);
    }
    private void mostrar(String s, No no){
        if(no.folha == true){
            System.out.println("Palavra: " + (s + no.elemento));
        }
        else{
            for(int i = 0; i < no.prox.length; i++){
                if(no.prox[i] != null){
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arvore trie1 = new Arvore();
        Arvore trie2 = new Arvore();

        trie1.inserir("Gato");
        trie1.inserir("Sapato");
        trie1.inserir("Sapo");
        trie1.inserir("Cachorro");
        System.out.println("Palavras da trie1: ");
        trie1.mostrar();

        trie2.inserir("Rato");
        trie2.inserir("Chinelo");
        trie2.inserir("Cobra");
        trie2.inserir("Lobo");
        System.out.println("Palavras de trie2: ");
        trie2.mostrar();

        Arvore intercala = new Arvore();
        intercala = intercala.intersecao(trie1, trie2);
        System.out.println("Palavras da intercalada: ");
        intercala.mostrar();
        

        sc.close();
    }
}