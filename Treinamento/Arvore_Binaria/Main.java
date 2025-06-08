import java.util.Scanner;

class No{
    int elemento;
    No esq, dir;

    public No(int elemento){
        this.elemento = elemento;
        this.esq = this.dir = null;
    }
}

class Arvore{
    No raiz;

    public Arvore(){this.raiz = null;}

    void inserir(int x){
        raiz = inserir(x, raiz);
    }
    No inserir(int x, No i){
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

    void inserirPai(int x)
    {
        if(raiz == null){
            raiz = new No(x);
        }
        else if(x < raiz.elemento){
            inserirPai(x, raiz.esq, raiz);
        }
        else if(x > raiz.elemento){
            inserirPai(x, raiz.dir, raiz);
        }
    }
    void inserirPai(int x, No i, No pai)
    {
        if(i == null){
            if(x < pai.elemento){
                pai.esq = new No(x);
            }
            else{
                pai.dir = new No(x);
            }
        }
        else if(x < pai.elemento){
            inserirPai(x, i.esq, i);
        }
        else if(x > pai.elemento){
            inserirPai(x, i.dir, i);
        }
    }

    boolean pesquisar(int x){
        return pesquisar(x,raiz);
    }
    boolean pesquisar(int x, No i){
        boolean resp;
        if(i == null){
            resp = false;
        }
        else if(x == i.elemento){
            resp = true;
        }
        else if(x < i.elemento){
           resp = pesquisar(x, i.esq);
        }
        else{
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

    void remover(int x){
        raiz = remover(x, raiz);
    }
    No remover(int x, No i){
        if(x < i.elemento){i.esq = remover(x, i.esq);}
        else if(x > i.elemento){i.dir = remover(x, i.dir);}
        else if(i.dir == null){i = i.esq;}
        else if(i.esq == null){i = i.dir;}
        else{i.esq = maiorEsq(i, i.esq);}

        return i;
    }
    No maiorEsq(No i, No j){
        if(j.dir == null){i.elemento = j.elemento; j = j.esq;}
        else{
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

    void caminharCentral(No i){
        if(i != null){
            caminharCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    void caminharPos(No i){
        if(i != null){
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.print(i.elemento + " ");
        }
    }

    void caminharPre(No i){
        if(i != null){
            System.out.print(i.elemento + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    int getAltura(No i, int altura){
        if(i == null){
            altura--;
        }
        else{
            int alturaEsq = getAltura(i.esq, altura + 1);
            int alturaDir = getAltura(i.dir, altura + 1);
            altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
        }
        return altura;
    }

    int getMenor(){
        int resp = 1000;
        if(raiz != null){
            No i;
            for(i = raiz; i.esq != null; i = i.esq);
            resp = i.elemento;
        }
        return resp;
        
    }

    int soma(){
        return soma(raiz);
    }

    int soma(No i){
        int resp = 0;
        if(i != null){
            resp = i.elemento + soma(i.esq) + soma(i.dir);
        }
        return resp;
    }

}

public class Main{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Arvore arvore = new Arvore();
        arvore.inserir(6);
        arvore.inserir(3);
        arvore.inserir(9);
        arvore.inserir(1);
        arvore.inserir(5);
        arvore.inserir(8);
        arvore.inserir(10);
        arvore.inserir(2);
        arvore.inserir(4);
        arvore.inserir(7);
        arvore.inserir(11);
        
        arvore.caminharCentral(arvore.raiz);

        System.out.println();

        arvore.caminharPos(arvore.raiz);
    
        System.out.println();

        arvore.caminharPre(arvore.raiz);

        System.out.println();

        System.out.println(arvore.getAltura(arvore.raiz, 0));

        System.out.println(arvore.soma());

        System.out.println();

        arvore.remover(9);

        arvore.caminharCentral(arvore.raiz);

        System.out.println();

        arvore.caminharPos(arvore.raiz);

        sc.close();
    }
}