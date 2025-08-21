package RubroNegra;
import java.util.Scanner;

class No{
    No esq , dir;
    int elemento;
    boolean cor;

    public No(int elemento){
        this.elemento = elemento;
        esq = null; dir = null;
        cor = false;
    }

    public No(int elemento, boolean cor){
        this.elemento = elemento;
        this.cor = cor;
        esq = null; dir = null;
    }

}

class Arvore{
    No raiz;

    public Arvore(){
        raiz = null;
    }

    public boolean pesquisar(int elemento){
        return pesquisar(elemento, raiz);
    }
    private boolean pesquisar(int elemento, No i){
        boolean resp;
        if(i == null){
            resp = false;
        }
        else if(elemento == i.elemento){
            resp = true;
        }
        else if(elemento < i.elemento){
            resp = pesquisar(elemento, i.esq);
        }
        else{
            resp = pesquisar(elemento, i.dir);
        }
        return resp;
    }

    public void caminharCentral() {
    System.out.print("[ ");
    caminharCentral(raiz);
    System.out.println("]");
    }
    public void caminharCentral(No i){
        if(i != null){
            caminharCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    public void caminharPos() {
    System.out.print("[ ");
    caminharPos(raiz);
    System.out.println("]");
    }
    public void caminharPos(No i){
        if(i != null){
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.print(i.elemento + " ");
        }
    }

    public void caminharPre() {
    System.out.print("[ ");
    caminharPre(raiz);
    System.out.println("]");
    }
    public void caminharPre(No i){
        if(i != null){
            System.out.print(i.elemento + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }
    
    public void inserir(int elemento){
        // Se estiver vazia
        if(raiz == null){
            raiz = new No(elemento);
            System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento + ").");
        }

        // Senao, se a arvore possuir um elemento
        else if(raiz.esq == null && raiz.dir == null){
            if(elemento < raiz.elemento){
                raiz.esq = new No(elemento);
                System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e esq(" + raiz.esq.elemento + ").");
            }
            else{
                raiz.dir = new No(elemento);
                System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
        }

        // Senao, se a arvore tiver dois elementos
        else if(raiz.esq == null){
            if(elemento < raiz.elemento){
                raiz.esq = new No(elemento);
                System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
            else if(elemento < raiz.dir.elemento){
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = elemento;
                System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
            else{
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
                 System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }

            raiz.esq.cor = false; raiz.dir.cor = false;
        }
        else if(raiz.dir == null){
            if(elemento > raiz.elemento){
                raiz.dir = new No(elemento);
                System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
            else if(elemento > raiz.esq.elemento){
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = elemento;
                System.out.println("Antes, dois elementos(E). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
            else{
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
                System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }

            raiz.esq.cor = false; raiz.dir.cor = false;
        }
        else{
            System.out.println("Arvore com tres ou mais elementos...");
            inserir(elemento, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void inserir(int elemento, No bisavo, No avo, No pai, No i){
        // Encontrou onde o No deve ser inserido
        if(i == null){
            if(elemento < pai.elemento){
                i = pai.esq = new No(elemento, true); // No preto criado
            }
            else{
                i = pai.dir = new No(elemento, true); // No preto criado
            }
            if(pai.cor == true){
                balancear(bisavo, avo, pai, i);
            }
        }
        else{
            if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
                i.cor = true; // No vira preto;
                // Filhos ficam brancos
                i.esq.cor = false; 
                i.dir.cor = false;

                if(i == raiz){
                    i.cor = false; // raiz sempre deve ser branca
                }
                else if(pai.cor == true){
                    balancear(bisavo, avo, pai, i);
                }
            }
            if(elemento < i.elemento){
                inserir(elemento, avo, pai, i, i.esq);
            }            
            else if(elemento > i.elemento){
                inserir(elemento, avo, pai, i, i.dir);
            }
        }
    }

    private void balancear(No bisavo, No avo, No pai, No i){
        if (pai.cor == true) { // Só entra se pai for vermelho (violação)
            // Existem 4 tipos de desequilíbrios

            //Rotacao a esqueda ou direita-esquerda
            if(pai.elemento > avo.elemento){
                if(i.elemento > pai.elemento){
                    avo = rotacaoEsq(avo);
                }
                else{
                    avo = rotacaoDirEsq(avo);
                }
            }

            //Rotacao a direita ou esquerda-direita
            else{
                if(i.elemento < pai.elemento){
                    avo = rotacaoDir(avo);
                }
                else{
                    avo = rotacaoEsqDir(avo);
                }
            }

            // Conectar o avô rotacionado ao bisavô
            if(bisavo == null){
                raiz = avo; // Se não há bisavô, então avô vira raiz
            } 
            else if(avo.elemento < bisavo.elemento){
                bisavo.esq = avo;
            } 
            else {
                bisavo.dir = avo;
            }
            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
            System.out.println("Reestabeler cores: avo(" + avo.elemento + "->branco) e avo.esq / avo.dir("
            + avo.esq.elemento + "," + avo.dir.elemento + "-> pretos)");
        }
    }


    private No rotacaoDir(No no){
        System.out.println("Rotacao DIR(" + no.elemento + ")");
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;
        
        noEsq.dir = no;
        no.esq = noEsqDir;
        return noEsq;
    }
    private No rotacaoEsq(No no){
        System.out.println("Rotacao ESQ(" + no.elemento + ")");
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }
    private No rotacaoDirEsq(No no){
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }
    private No rotacaoEsqDir(No no){
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arvore arvore = new Arvore();

        // Inserindo elementos
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(30);
        arvore.inserir(15);
        arvore.inserir(25);
        arvore.inserir(5);
        arvore.inserir(1);

        // Teste de busca
        System.out.println("\nPesquisar 15: " + arvore.pesquisar(15));
        System.out.println("Pesquisar 50: " + arvore.pesquisar(50));

        // Caminhamentos
        System.out.println("\nCaminhar Pre-Ordem:");
        arvore.caminharPre();

        System.out.println("\n\nCaminhar Em-Ordem:");
        arvore.caminharCentral();

        System.out.println("\n\nCaminhar Pos-Ordem:");
        arvore.caminharPos();

        sc.close();
    }
    
}
