package AVL;
import java.util.Scanner;

class No{
    public int elemento;
    public No esq, dir;
    public int nivel;

    public No(int elemento){
        this.elemento = elemento;
        this.esq = this.dir = null;
        this.nivel = 1;
    }

    public void setNivel(){
        this.nivel = Math.max(getNivel(esq), getNivel(dir)) + 1;
    }

    public static int getNivel(No no) {
		return (no == null) ? 0 : no.nivel;
	}
}

// Arvore AVL
class Arvore{
    No raiz;

    public Arvore(){this.raiz = null;}

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
        return balancear(i);
    }


    
    public boolean pesquisar(int x){
        return pesquisar(x,raiz);
    }
    private boolean pesquisar(int x, No i){
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

    public void remover(int x){
        raiz = remover(x, raiz);
    }
    private No remover(int x, No i){
        if(x < i.elemento){i.esq = remover(x, i.esq);}
        else if(x > i.elemento){i.dir = remover(x, i.dir);}
        else if(i.dir == null){i = i.esq;}
        else if(i.esq == null){i = i.dir;}
        else{i.esq = maiorEsq(i, i.esq);}

        return balancear(i);
    }

    private No maiorEsq(No i, No j){
        if(j.dir == null){i.elemento = j.elemento; j = j.esq;}
        else{
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

    private No balancear(No no){
        if(no != null){
            int fator = No.getNivel(no.esq) - No.getNivel(no.dir);
            // se estiver balanceada
            if(Math.abs(fator) <= 1){
                no.setNivel();
            }
            // se estiver desbalanceada para a direita
            else if(fator == 2){
                int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);
                // Se o filho a direita tambem estiver desbalanceado
                if(fatorFilhoDir == -1){
                    no.dir = rotacionarDir(no.dir);
                }
                no = rotacionarEsq(no); 
            }
            // Se desbalanceada para a esquerda
            else if(fator == -2){
                int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);
                // Se o filho a esquerda tambem estiver desbalanceado
                if(fatorFilhoEsq == 1){
                    no.esq = rotacionarEsq(no.esq);
                }
                no = rotacionarDir(no);
            }
        }
        return no;
    }

    private No rotacionarDir(No no){
        System.out.println("Rotacionar DIR(" + no.elemento + ")");
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;
        noEsq.dir = no;
        no.esq = noEsqDir;

        no.setNivel();
        noEsq.setNivel();
        return noEsq;
    }

    private No rotacionarEsq(No no){
        System.out.println("Rotacionar ESQ(" + no.elemento + ")");
        No noDir = no.dir;
        No noDirEsq = noDir.esq;
        noDir.esq = no;
        no.dir = noDirEsq;

        no.setNivel();
        noDir.setNivel();
        return noDir;
    }

    public void caminharCentral(No i){
        if(i != null){
            caminharCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    public void caminharPos(No i){
        if(i != null){
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.print(i.elemento + " ");
        }
    }

    public void caminharPre(No i){
        if(i != null){
            System.out.print(i.elemento + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }


}

public class Main{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Arvore arvore = new Arvore();

        // Inserindo elementos
        System.out.println("Inserindo elementos...");
        arvore.inserir(30);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(10);
        arvore.inserir(25);
        arvore.inserir(35);
        arvore.inserir(50);
        arvore.inserir(5);
        arvore.inserir(15);

        // Exibindo árvore em pré-ordem
        System.out.print("\nCaminhamento pré-ordem: ");
        arvore.caminharPre(arvore.raiz);

        // Exibindo árvore em in-ordem
        System.out.print("\nCaminhamento central (in-ordem): ");
        arvore.caminharCentral(arvore.raiz);

        // Exibindo árvore em pós-ordem
        System.out.print("\nCaminhamento pós-ordem: ");
        arvore.caminharPos(arvore.raiz);

        // Pesquisando elementos
        System.out.println("\n\nPesquisa:");
        System.out.println("Existe 25? " + arvore.pesquisar(25));
        System.out.println("Existe 100? " + arvore.pesquisar(100));

        // Removendo elementos
        System.out.println("\nRemovendo elementos...");
        arvore.remover(10);
        arvore.remover(40);

        // Exibindo árvore após remoções
        System.out.print("\nCaminhamento pré-ordem após remoções: ");
        arvore.caminharPre(arvore.raiz);

        System.out.print("\nCaminhamento central (in-ordem) após remoções: ");
        arvore.caminharCentral(arvore.raiz);

        System.out.print("\nCaminhamento pós-ordem após remoções: ");
        arvore.caminharPos(arvore.raiz);

        sc.close();

        sc.close();
    }
}