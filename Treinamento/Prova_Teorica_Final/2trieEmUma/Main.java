import java.util.ArrayList;
import java.util.Scanner;


class No{
    public char elemento;
    public final int tamanho = 256;
    public No[] prox;
    public boolean folha;

    public No(){
        this.elemento = ' ';
        prox = new No[tamanho];
        for(int i = 0; i < tamanho; i++){
            prox[i] = null;
        }
        folha = false;
    }
    public No(char elemento){
        this.elemento = elemento;
        prox = new No[tamanho];
        for(int i = 0; i < tamanho; i++){prox[i] = null;}
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
        if(no.prox[s.charAt(i)] == null){
            no.prox[s.charAt(i)] = new No(s.charAt(i));
            if(i == s.length() -1){
                no.prox[s.charAt(i)].folha = true;
            }
            else{
                inserir(s, no.prox[s.charAt(i)], i + 1);
            }
        }
        else if(no.prox[s.charAt(i)].folha == false && i < s.length() - 1){
            inserir(s, no.prox[s.charAt(i)], i + 1);
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
                    coletarPalavras(s + no.elemento, no.prox[i], palavras);
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