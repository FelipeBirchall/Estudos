import java.util.Scanner;

class Celula{
    int elemento;
    Celula prox;

    public Celula(){}

    public Celula(int elemento){
        this.elemento = elemento;
        this.prox = null;
    }
}
class Lista{
    private Celula primeiro, ultimo;

    public Lista(){
        primeiro = new Celula(-1);
        ultimo = primeiro;
    }

    public void inserir(int x){
        if(primeiro.elemento == -1){
            primeiro.elemento = x;
        }
        else{
            ultimo.prox = new Celula(x);
            ultimo = ultimo.prox;
        }
    }

    public boolean pesquisar(int x){
        boolean resp = false;
        for(Celula i = primeiro; i != null; i = i.prox){
            if(i.elemento == x){
                resp = true;
                break;
            }
        }
        return resp;
    }
}

class No{
    No esq , dir;
    int elemento;

    public No(){}

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
    private No inserir(int x, No no){
        if(no == null){
            no = new No(x);
        }
        else if(x < no.elemento){
            no.esq = inserir(x, no.esq);
        }
        else if(x > no.elemento){
            no.dir = inserir(x, no.dir);
        }
        return no;
    }

    public boolean pesquisar(int x){
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(int x, No no){
        boolean resp;
        if(no == null){
            resp = false;
        }
        else if(x == no.elemento){
            resp = true;
        }
        else if(x < no.elemento){
            resp = pesquisar(x, no.esq);
        }
        else{
            resp = pesquisar(x, no.dir);
        }
        return resp;
    } 
}

class T3{
    int tabela[];
    int tam;
    Arvore arvore;
    final int NULO = -1;

    public T3(){
        this.tam = 9;
        tabela = new int[tam];
        for(int i = 0; i < 9; i++){
            tabela[i] = NULO;
        }
        arvore = new Arvore();
    }

    public int hash(int elemento){
        return elemento % tam;
    }

    public boolean inserir(int elemento){
        boolean resp = false;
        if(elemento > NULO){
            int pos = hash(elemento);
            if(tabela[pos] == NULO){
                tabela[pos] = elemento;
                resp = true;
            }
            else{
                arvore.inserir(elemento);
                resp = true;
            }
        }
        return resp;
    }

    public boolean pesquisar(int x){
        boolean resp = false;
        int pos = hash(x);
        if(tabela[pos] == x){
            resp = true;
        }
        else if(tabela[pos] != NULO){
            resp = arvore.pesquisar(x);
        }
        return resp; 
    }


}


// Hash direto com area de reserva
class Hash{
    int tam;
    int T1[];
    T3 t3;
    Lista listaT2;
    Arvore arvoreT2;
    final int NULO = -1;

    public Hash(){
        this.tam = 7;
        T1 = new int[tam];
        for(int i = 0; i < tam; i++){
            T1[i] = NULO;
        }
        t3 = new T3();
        listaT2 = new Lista();
        arvoreT2 = new Arvore();
    }

    public int hash(int elemento){
        return elemento % tam;
    }

    public boolean inserir(int elemento){
        boolean resp = false;
        if(elemento > NULO){
            int pos = hash(elemento);
            if(T1[pos] == NULO){
                T1[pos] = elemento;
                resp = true;
            }
            else{
                if(elemento % 3 == 0){
                    resp = t3.inserir(elemento);
                }
                else if(elemento % 3 == 1){
                    listaT2.inserir(elemento);
                    resp = true;
                }
                else{
                    arvoreT2.inserir(elemento);
                    resp = true;
                }
            }
            
        }
        return resp;
    }

    public boolean pesquisar(int elemento){
        boolean resp = false;
        int pos = hash(elemento);
        if(T1[pos] == elemento){
            resp = true;
        }
        else if(T1[pos] != NULO){
            if(elemento % 3 == 0){
                resp = t3.pesquisar(elemento);
            }
            else if(elemento % 3 == 1){
                resp = listaT2.pesquisar(elemento);
            }
            else{
                resp = arvoreT2.pesquisar(elemento);
            }
        }
        return resp;
    }


    
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hash hash = new Hash();

        
        sc.close();
    }

}