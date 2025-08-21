package NosNaoBalanceados;
import java.util.Scanner;

class No{
    int elemento;
    No esq, dir;
    int nivel;

    public No(){
        this.esq = this.dir = null;
    }
    public No(int elemento){
        this.elemento = elemento;
        this.esq = this.dir = null;
    }
}

class Arvore{
    No raiz;

    public Arvore(){
        this.raiz = null;
    }

    public void inserir(int x){
        raiz = inserir(x, raiz); 
    }
    private No inserir(int x, No i){
        if(i == null){
            i = new No(x);
        }
        else if(x < i.elemento){
            i.esq = inserir(x, i.esq);
        }
        else if(x > i.elemento){
            i.dir = inserir(x, i.dir);
        }
        return i;
    }


    // INICIO
    private int altura(No i){
        if(i == null){
            return 0;
        }
        int altEsq = altura(i.esq);
        int altDir = altura(i.dir);

        return Math.max(altEsq, altDir) + 1;
    }

    public int contarNosNaoBalanceados(){
        return contarNosNaoBalanceados(raiz);
    }
    public int contarNosNaoBalanceados(No i){
       if(i == null){
        return 0;
       }
       int altEsq = altura(i.esq);
       int altDir = altura(i.dir);
       int fator = altEsq - altDir;

       int cont = 0;
       if(Math.abs(fator) > 1){
            cont = 1;
       }
       cont += contarNosNaoBalanceados(i.esq);
       cont += contarNosNaoBalanceados(i.dir);

       return cont;
    }
    // FIM
}


public class Main{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);        
    }
}