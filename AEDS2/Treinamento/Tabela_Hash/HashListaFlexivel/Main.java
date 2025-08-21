package HashListaFlexivel;
import java.util.Scanner;

class Celula {
    int elemento;
    Celula prox;

    public Celula(){}

    public Celula(int elemento){
        this.elemento = elemento;
        this.prox = null;
    }
}

class Lista {
    private Celula primeiro;
    private Celula ultimo;

    public Lista(){
        primeiro = new Celula(-1);
        ultimo = primeiro;
    }
    
    // sempre irá inserir no final
    public void inserir(int x){
        if(primeiro.elemento == -1){
            primeiro.elemento = x;
        }
        else{
            Celula tmp = new Celula(x);
            ultimo.prox = tmp;
            ultimo = ultimo.prox;
        }
        
    }

    
    public int remover(int x){
        int resp = -1;
        Celula tmp = null;
        if(primeiro.elemento == x){
          resp = primeiro.elemento;
          tmp = primeiro;
          primeiro = tmp.prox;
          tmp.prox = null;   
        }
        else{
            for(Celula i = primeiro; i != null; i = i.prox){
                if(i.elemento == x){
                    resp = i.elemento;
                    tmp = i;
                    break;
                }
            }
            Celula tmp2 = primeiro;
            while(tmp2.prox != tmp){
                tmp2 = tmp2.prox;
            }
            tmp2.prox = tmp.prox;
            tmp.prox = null;
        }
        
        return resp;

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

    public void mostrar(){
        for(Celula i = primeiro; i != null; i = i.prox){
            System.out.print(i.elemento + " -> ");
        }
    }


}

// Hash indireta com Lista Flexivel
class Hash{
    Lista tabela[];
    int tam;
    final int NULO = -1;

    public Hash(int tam){
        this.tam = tam;
        this.tabela = new Lista[tam];
        for(int i = 0; i < tam; i++){
            tabela[i] = new Lista();
        }
    }

    public int hash(int elemento){
        return elemento % tam;
    }

    public void inserir(int elemento){
        int pos = hash(elemento);
        tabela[pos].inserir(elemento);
    }

    public int remover(int elemento){
        int pos = hash(elemento);
        int resp = tabela[pos].remover(elemento);
        return resp;
    }

    public boolean pesquisar(int elemento){
        int pos = hash(elemento);
        return tabela[pos].pesquisar(elemento);
    }

    public void mostrar(){
        for(int i = 0; i < tam; i++){
            System.out.println();
            tabela[i].mostrar();
        }
    }
    

}






public class Main{

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        Hash hash = new Hash(7);

        int opcao = 0;
        while(opcao != 5)
        {
            System.out.println("\nInserir - 1");
            System.out.println("Remover - 2");
            System.out.println("Listar - 3");
            System.out.println("Pesquisar - 4");
            System.out.println("Sair - 5");
            System.out.print("Opcao: ");
            opcao = sc.nextInt();
            int valor;
            switch (opcao) {
                case 1:
                    valor = sc.nextInt();
                    hash.inserir(valor);
                    break;
        
                case 2:
                    valor = sc.nextInt();
                    System.out.println("Valor " + hash.remover(valor) + " removido");
                    break;

                case 3:
                    hash.mostrar();
                    break;
                
                case 4:
                    valor = sc.nextInt();
                    if(hash.pesquisar(valor) == true){
                        System.out.println("Encontrado");
                    } else{
                        System.out.println("Nao encontrado");
                    }
                    break;

                case 5:
                    System.out.println("\nFINALIZADO");
                    break;
            }
        }
        sc.close();
    }

}