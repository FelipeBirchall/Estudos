package FilaFlexivel;
import java.util.Scanner;


// Class Célula
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


class FilaFlexivel{
    public Celula primeiro, ultimo;
    
    public FilaFlexivel(){
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserir(int x)
    {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public int remover()
    {
        if(primeiro == ultimo)
        {
            return -1;
        }
        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        int elemento = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
        return elemento;
    }

    public void mostrar()
    {
        System.out.print("[ ");
        for(Celula i = primeiro.prox; i != null; i = i.prox)
        {
            System.out.print(i.elemento + " ");
        }
        System.out.print("]");
    }

}


public class Main {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int opcao = -1;
        int entrada;
        FilaFlexivel filaFlexivel = new FilaFlexivel();
        while(opcao != 0)
        {
            System.out.println("\n------ MENU ------");
            System.out.println("Inserir - 3");
            System.out.println("Remover - 2");
            System.out.println("Mostrar - 1");
            System.out.println("Encerrar - 0");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            
            switch (opcao) {
                case 3:
                    entrada = sc.nextInt();
                    filaFlexivel.inserir(entrada);        
                    break;

                case 2:
                    System.out.println(filaFlexivel.remover());
                    break;

                case 1:
                    filaFlexivel.mostrar();
                    break;

                case 0:
                    System.out.println("FINALIZADO!");
                    break;
            }
        }
        sc.close();
    }
    
}
