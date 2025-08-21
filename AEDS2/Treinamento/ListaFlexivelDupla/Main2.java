import java.util.Scanner;

class CelulaDupla{
    public int elemento;
    public CelulaDupla prox, ant;

    public CelulaDupla()
    {
        this.elemento = 0;
        this.prox = this.ant = null;
    }
    public CelulaDupla(int x)
    {
        this.elemento = x;
        this.prox = this.ant = null;
    }
}

class ListaDupla{
    private CelulaDupla primeiro, ultimo;

    public ListaDupla()
    {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }
    public void inserirInicio(int x)
    {
        CelulaDupla tmp = new CelulaDupla(x);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo)
        {
            ultimo = tmp;
        }
        else{
            tmp.prox.ant = tmp;
        }
        tmp = null;
    }

    public void inserir(int x)
    {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void quicksort()
    {

    }

    public void mostrar()
    {
        System.out.print("[ ");
        for(CelulaDupla i = primeiro.prox; i != null; i = i.prox)
        {
            System.out.print(i.elemento + " ");
        }
        System.out.print("]");

    }

    public int tamanho() {
        int tam = 0;
        for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tam++);
        return tam;
     }
}


public class Main2 {
    public static void main(String[] args) {
        
       Scanner sc = new Scanner(System.in);
       ListaDupla listadupla = new ListaDupla();
       
       listadupla.inserir(8);
       listadupla.inserir(1);
       listadupla.inserir(3);
       listadupla.inserir(7);
       listadupla.inserir(2);

       listadupla.mostrar();
       sc.close();
    }
    
}
