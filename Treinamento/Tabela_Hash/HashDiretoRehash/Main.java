package HashDiretoRehash;
import java.util.Scanner;

// Hash direto com rehash
class Hash{
    int tabela[];
    int m;
    final int NULO = -1;
    
    public Hash(int m){
        this.m = m;
        this.tabela = new int[this.m];
        for(int i = 0; i < m; i++){
            tabela[i] = NULO;
        }
    }

    public int hash(int elemento){
        return elemento % m;
    }

    public int rehash(int elemento){
        return ++elemento % m;
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
                pos = rehash(elemento);
                if(tabela[pos] == NULO){
                    tabela[pos] = elemento;
                    resp = true;
                }
            }
        }

        return resp;
    }

    public boolean pesquisar(int elemento){
        boolean resp = false;
        int pos = hash(elemento);

        if(tabela[pos] != NULO){
            if(tabela[pos] == elemento){
                resp = true;
            }
            else{
                pos = rehash(elemento);
                if(tabela[pos] == elemento){
                resp = true;
                }
            }
        }
        return resp;
    }

    public boolean remover(int elemento){
        boolean resp = false;
        int pos = hash(elemento);

        if(tabela[pos] != NULO){
            if(tabela[pos] == elemento){
                tabela[pos] = NULO;
                resp = true;
            }
            else{
                pos = rehash(elemento);
                if(tabela[pos] == elemento){
                    tabela[pos] = NULO;
                    resp = true;
                }
            }
        }
        return resp;
    }

    public void mostrar(){
        System.out.println("Tabela: ");
        for(int i = 0; i < m; i++){
            System.out.println(tabela[i]);
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
                    if(hash.inserir(valor) == true){
                        System.out.println("Inserido");
                    } else{
                        System.out.println("Nao inserido");
                    }
                    break;
        
                case 2: 
                    valor = sc.nextInt();
                    if(hash.remover(valor) == true){
                        System.out.println("Removido");
                    } else{
                        System.out.println("Nao removido");
                    }
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