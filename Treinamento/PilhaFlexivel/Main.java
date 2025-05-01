package PilhaFlexivel;
import java.util.Scanner;

class Celula{
    int elemento;
    Celula prox;

    public Celula()
    {
        this.elemento = 0;
        this.prox = null;
    }
    public Celula(int elemento)
    {
        this.elemento = elemento;
        this.prox = null;
    }   
}

class PilhaFlexivel{
    Celula topo;
    public PilhaFlexivel()
    {
        topo = null;
    }

    public Celula getTopo(){
        return topo;
    }

    public void inserir(int x)
    {
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }

    public int remover()
    {
       if(topo == null)
       {
          return -1;
       }
       int elemento = topo.elemento;
       Celula tmp = topo;
       topo = topo.prox;
       tmp.prox = null;
       tmp = null;
       return elemento;
    }

    public int somaElementos(int soma, Celula i)
    {
        /* INTERATIVA 
        int soma = 0;
        for(Celula i = topo; i != null; i = i.prox)
        {
            soma += i.elemento;
        }
        */
        // RECURSIVA
        if(i != null)
        {
            soma += i.elemento;
            return somaElementos(soma, i.prox);
        }
        return soma;
    }

    public int maiorElemento(int maior, Celula i)
    {
        
        /* 
        int maior = -1000;
        for(Celula i = topo; i != null; i = i.prox)
        {
            if(i.elemento > maior){maior = i.elemento;}
        }
        */
        //RECURSIVA
        if(i != null)
        {
            if(i.elemento > maior){maior = i.elemento;}
            return maiorElemento(maior, i.prox);
        }
        return maior;
    }

    public void mostrar()
    {
        System.out.println("--");
        for(Celula i = topo; i != null; i = i.prox)
        {
            System.out.println(i.elemento);
        }
        System.out.println("--");
    }
}


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PilhaFlexivel pilhaFlexivel = new PilhaFlexivel();
        int opcao = -1;
        int entrada;

        while(opcao != 0)
        {
            System.out.println("\n------ MENU ------");
            System.out.println("Inserir - 5");
            System.out.println("Remover - 4");
            System.out.println("Soma - 3");
            System.out.println("Maior elemento - 2");
            System.out.println("Mostrar - 1");
            System.out.println("Encerrar - 0");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            
            switch (opcao) {
                case 5:
                    entrada = sc.nextInt();
                    pilhaFlexivel.inserir(entrada);
                    break;
                
                case 4:
                    System.out.println(pilhaFlexivel.remover());
                    break;

                case 3:
                    int soma = 0;
                    System.out.println(pilhaFlexivel.somaElementos(soma , pilhaFlexivel.getTopo()));
                    break;
                
                case 2:
                    int maior = -1000;
                    System.out.println(pilhaFlexivel.maiorElemento(maior, pilhaFlexivel.getTopo()));
                    break;

                case 1:
                    pilhaFlexivel.mostrar();
                    break;
                
                case 0:
                    System.out.println("FINALIZADO!");
                    break;
            }
        }
        sc.close();
    }
    
}
