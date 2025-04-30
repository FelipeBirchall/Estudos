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

// Class Lista
class Lista{
    private Celula primeiro, ultimo;

    public Lista()
    {
        ultimo = primeiro = new Celula();
    }

    // Inserir no final
    public void inserirFim(int x)
    {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }
    
    // Inserir no início
    public void inserirInicio(int x)
    {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo)
        {
            ultimo = tmp;
        }
        tmp = null;
    }

    // Inserir de acordo com a posição que o usuário deseja
    public void inserir(int x , int pos)
    {
        if(pos == 0){inserirInicio(x);}
        else if(pos == tamanho()){inserirFim(x);}
        else{
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = null;
        }

    }

    // Remover no final
    public int removerFim()
    {
        Celula i;
        for(i = primeiro; i.prox != ultimo; i = i.prox);
        int elemento = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;
        return elemento; 
    }

    // Remover no Início
    public int removerInicio()
    {
        Celula tmp = primeiro.prox;
        primeiro.prox = primeiro.prox.prox;
        int elemento = tmp.elemento;
        tmp.prox = null;
        tmp = null;
        return elemento;

    }

    // Remover de acordo com a posição que o usuário deseja
    public int remover(int pos)
    {
        int elemento;
        if(pos == 0){elemento = removerInicio();}
        else if(pos == tamanho() - 1){elemento = removerFim();}
        else{
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = i.prox;
            elemento = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            tmp = null;
        }
        return elemento;
    }

    // Verificar o tamanho da Lista
    public int tamanho() {
        int tam = 0;
        for(Celula i = primeiro; i != ultimo; i = i.prox, tam++);
        return tam;
     }

    // Soma dos elementos
    public int somaElementos()
    {
        int soma = 0;
        for(Celula i = primeiro.prox; i != null; i = i.prox)
        {
            int elemento = i.elemento;
            soma += elemento;
        }
        return soma;
    }

    //Maior elemento
    public int maiorElemento()
    {
        int maior = -1;
        for(Celula i = primeiro; i != null; i = i.prox)
        {
            int elemento = i.elemento;
            if(elemento > maior){maior = elemento;}
        }

        return maior;

    }


    // Mostrar terceiro elemento, se existir
    public int terceiroElemento()
    {
        int elemento = 0;
        Celula i = primeiro;
        if(tamanho() > 3)
        {
            for(int j = 0; j < 3; j++, i = i.prox);
            elemento = i.elemento;
        }
        return elemento;
    }

    // Ordenar a lista
    public void ordenar()
    {
        for(Celula i = primeiro.prox; i != null; i = i.prox)
        {
            Celula menor = i;
            for(Celula j = i.prox; j != null; j = j.prox)
            {
                if(j.elemento < menor.elemento){menor = j;}
            }
            if(menor != i)
            {
                int temp = i.elemento;
                i.elemento = menor.elemento;
                menor.elemento = temp;
            }
        }
    }

    // Impressão dos elementos presentes
    public void mostrar()
    {
        System.out.println();
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
        Lista lista = new Lista();
        int entrada, opcao = -1;
        int pos;

        while(opcao != 0)
        {
            System.out.println("\n------- MENU -------\n");
            System.out.println("Inserir Inicio - 10");
            System.out.println("Inserir Fim - 9");
            System.out.println("Inserir - 8");
            System.out.println("Remover Inicio - 7");
            System.out.println("Remover Fim - 6");
            System.out.println("Remover - 5");
            System.out.println("Soma - 4");
            System.out.println("Maior Elemento - 3");
            System.out.println("Ordenar - 2");
            System.out.println("Mostrar - 1");
            System.out.println("Finalizar - 0");
            System.out.print("Opção: ");
            opcao = sc.nextInt();

            switch(opcao) {
                case 10:
                    entrada = sc.nextInt();
                    lista.inserirInicio(entrada);
                    break;
            
                case 9:
                    entrada = sc.nextInt();
                    lista.inserirFim(entrada);
                    break;
                
                case 8:
                    entrada = sc.nextInt();
                    pos = sc.nextInt();
                    lista.inserir(entrada, pos);
                    break;
                
                case 7:
                    System.out.println(lista.removerInicio());
                    break;

                case 6:
                    System.out.println(lista.removerFim());
                    break;
                  
                case 5:
                    pos = sc.nextInt();
                    System.out.println(lista.remover(pos));
                    break;

                case 4:
                    System.out.println("\n" + lista.somaElementos());
                    break;
                
                case 3:
                    System.out.println("\n" + lista.maiorElemento());
                    break;

                case 2:
                    lista.ordenar();
                    System.out.println("Ordenado!\n");
                    break;

                case 1:
                    lista.mostrar();
                    break;

                case 0:
                    System.out.println("FINALIZADO!");
                    break;
            }
            
        }

        sc.close();

    }
    
}
