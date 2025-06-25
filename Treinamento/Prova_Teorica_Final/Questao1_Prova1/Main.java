package Questao1_Prova1;
import java.util.Scanner;

class No{
    int elemento;
    No esq, dir;

    public No(int elemento){
        this.elemento = elemento;
        this.esq = this.dir = null;
    }
}

class Celula{
    int elemento;
    Celula prox;

    public Celula(){}

    public Celula(int elemento){
        this.elemento = elemento;
        this.prox = null;
    }
}

class HashT2{
    int tabela[];
    Celula primeiroT3, ultimoT3;
    No raizT3;

    public HashT2(int tam1){
        tabela = new int[tam1];
        for(int i = 0; i < tam1; i++){
            tabela[i] = -1;
        }
        primeiroT3 = new Celula();
        ultimoT3 = primeiroT3;
        raizT3 = null;
    }

    public void inserirArvore(int x){
        raizT3 = inserirArvore(x, raizT3);
    }
    private No inserirArvore(int x, No i){
        if(i == null){
            i = new No(x);
        }
        else if(x < i.elemento){
            i.esq = inserirArvore(x, i.esq);
        }
        else if(x > i.elemento){
            i.dir = inserirArvore(x, i.dir);
        }
        return i;
    }
    public void inserirLista(int x){
        ultimoT3.prox = new Celula(x);
        ultimoT3 = ultimoT3.prox;
    }

    public int hashT2(int elemento){
        return elemento % tabela.length;
    }
    public int rehashT2(int elemento){
        return ++elemento % tabela.length;
    }
    public int hashT3(int elemento){
        return elemento % 2;
    }

    public void inserir(int elemento){
        int pos = hashT2(elemento);
        if(tabela[pos] == -1){
            tabela[pos] = elemento;
        }
        else{
            pos = rehashT2(elemento);
            if(tabela[pos] == -1){
                tabela[pos] = elemento;
            }
            else{
                if(hashT3(elemento) == 0){
                    inserirLista(elemento);
                }
                else{
                    inserirArvore(elemento);
                }
            }
        }
    }
}

class Doidona{
    CelulaT1[] t1;

    public Doidona(){
        t1 = new CelulaT1[10];
    }

    public int hashT1(int elemento){
        return elemento % t1.length;
    }
    public void inserir(int elemento){
        int pos = hashT1(elemento);
        if(t1[pos] == null){
            t1[pos] = new CelulaT1(elemento);
        }
        else{
            t1[pos].t2.inserir(elemento);
        }
    }
}

class CelulaT1{
    int elemento;
    HashT2 t2;

    public CelulaT1(int elemento){
        this.elemento = elemento;
        t2 = new HashT2(10);
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Doidona tabela = new Doidona();
        
        // Exemplo: inserir alguns elementos
        System.out.println("Digite a quantidade de elementos a inserir: ");
        int qtd = sc.nextInt();
        
        System.out.println("Digite os elementos: ");
        for (int i = 0; i < qtd; i++) {
            int elemento = sc.nextInt();
            tabela.inserir(elemento);
        }
        
        // Verificar a posição 5 de T1 (onde os elementos foram inseridos)
        if (tabela.t1[5] != null) {
            HashT2 t2 = tabela.t1[5].t2;
            System.out.println("Tabela T2 (posição 5 de T1): ");
            for (int i = 0; i < t2.tabela.length; i++) {
                System.out.println("Posição " + i + ": " + t2.tabela[i]);
            }
            
            // Exibir a lista T3, se existir
            Celula atual = t2.primeiroT3.prox;
            System.out.println("Lista T3: ");
            while (atual != null) {
                System.out.println(atual.elemento);
                atual = atual.prox;
            }
        } else {
            System.out.println("Nenhum elemento inserido na posição 5 de T1.");
        }
        
        sc.close();
    }
}