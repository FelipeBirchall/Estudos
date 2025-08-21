package HashDiretoReserva;
import java.util.Scanner;

// Hash direto com area de reserva
class Hash{
    int tabela[];
    int m1, m2, m, reserva;
    final int NULO = -1;

    public Hash(int m1, int m2){
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        this.tabela = new int[this.m];
        for(int i = 0; i < this.m; i++){
            tabela[i] = NULO;
        }
        reserva = 0;
    }

    public int h(int elemento){
        return elemento % m1;
    }
   
    public boolean inserir(int elemento){
        boolean resp = false;
        if(elemento > NULO){
            int pos = h(elemento);
            if(tabela[pos] == NULO){
                tabela[pos] = elemento; 
                resp = true;
            } else if(reserva < m2){
                tabela[m1 + reserva] = elemento;
                reserva++;
                resp = true;
            }
        }
        return resp;
    }

    public boolean pesquisar(int elemento){
        boolean resp = false;
        int pos = h(elemento);
        if(tabela[pos] == elemento){resp = true;}
        else if(tabela[pos] != NULO){
            for(int i = 0; i < reserva; i++){
                if(tabela[m1 + i] == elemento){
                    resp = true;
                    i = reserva;
                }
            }
        }
        return resp;
    }

    public boolean remover(int elemento){
        boolean resp = false;
        int pos = h(elemento);
        if(tabela[pos] == elemento){
            tabela[pos] = NULO;
            resp = true;
            }
        else{
            for(int i = 0; i < reserva; i++){
                if(tabela[m1 + i] == elemento){
                    tabela[m1 + i] = tabela[m1 + reserva - 1];
                    tabela[m1 + reserva - 1] = NULO;
                    reserva--;
                    resp = true;
                    i = reserva;
                }
            }
        }
        return resp;
    }

    public void mostrar(){
        System.out.println("Tabela: ");
        for(int i = 0; i < m1; i++){
            System.out.println(tabela[i]);
        }
        System.out.println("Reserva: ");
        for(int i = 0; i < reserva; i++){
            System.out.println(tabela[m1+ i]);
        }
    }

}






public class Main{

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        Hash ht = new Hash(7, 3);

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
                    if(ht.inserir(valor) == true){
                        System.out.println("Inserido");
                    } else{
                        System.out.println("Nao inserido");
                    }
                    break;
        
                case 2:
                    valor = sc.nextInt();
                    if(ht.remover(valor) == true){
                        System.out.println("Removido");
                    } else{
                        System.out.println("Nao removido");
                    }
                    break;

                case 3:
                    ht.mostrar();
                    break;
                
                case 4:
                    valor = sc.nextInt();
                    if(ht.pesquisar(valor) == true){
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