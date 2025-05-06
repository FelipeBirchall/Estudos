package MatrizFlexivel;

import java.util.Scanner;

class Celula{
    Celula dir, esq, inf , sup;
    int elemento;

    public Celula()
    {
        this.elemento = 0;
        this.dir = null;
        this.esq = null;
        this.inf = null;
        this.sup = null;
    }

    public Celula(int x)
    {
        this.elemento = x;
        this.dir = null;
        this.esq = null;
        this.inf = null;
        this.sup = null;
    }
}
class Matriz{
    private Celula inicio;
    private int linha, coluna;
    public Matriz()
    {
        this.linha = this.coluna = 3;
    }
    public Matriz(int linha, int coluna)
    {
        this.linha = linha;
        this.coluna = coluna;
        inicio = new Celula();
        criarMatriz();

    }
    public void criarMatriz()
    {
         Celula linhaAtual = inicio;
         Celula colunaAtual;


         for(int j = 1; j < coluna; j++)
         {
            colunaAtual = new Celula();              
            linhaAtual.dir = colunaAtual;
            colunaAtual.esq = linhaAtual;
            linhaAtual = colunaAtual;
         }

        Celula linhaCima = inicio;
        for(int i = 1; i < linha; i++)
        {
            Celula novaLinha = new Celula();

            linhaCima.inf = novaLinha;
            novaLinha.sup = linhaCima;

            linhaAtual = novaLinha;
            colunaAtual = linhaCima.dir;

            for(int j = 1; j < coluna; j++)
            {
                Celula novaCelula = new Celula();
                linhaAtual.dir = novaCelula;
                novaCelula.esq = linhaAtual;

                novaCelula.sup = colunaAtual;
                colunaAtual.inf = novaCelula;

                linhaAtual = novaCelula;
                colunaAtual = colunaAtual.dir;
            }
            linhaCima = linhaCima.inf;
        }


    }

    public void inserir(Scanner sc)
    {
        Celula linhaAtual = inicio;
        for(int i = 0; i < linha; i++)
        {
            Celula celulaAtual = linhaAtual;
            for(int j = 0; j < coluna; j++)
            {
                int x = sc.nextInt();
                celulaAtual.elemento = x;
                celulaAtual = celulaAtual.dir;
            }
            linhaAtual = linhaAtual.inf;
        }
    }

    public void mostrar()
    {
        Celula linhaAtual = inicio;
        for(int i = 0; i < linha; i++)
        {
            Celula celulaAtual = linhaAtual;
            for(int j = 0; j < coluna; j++)
            {
                System.out.print(celulaAtual.elemento + " ");
                celulaAtual = celulaAtual.dir;
            }
            System.out.println();
            linhaAtual = linhaAtual.inf;
        }
    }

    public int somarDiagonalPrincipal()
    {
        int soma = 0;
        for(Celula i = inicio; i != null; i = i.inf)
        {
            if(i != inicio){i = i.dir;}
            soma+= i.elemento;
        }
        return soma;
    }

    public int somarDiagonalSecundaria()
    {
        int soma = 0;
        Celula i;
        for(i = inicio; i.dir != null; i = i.dir);
        for(Celula j = i; j != null; j = j.inf)
        {
            if(j != i){j = j.esq;}
            soma+= j.elemento;
        }
        return soma;
    }

    public void removerUltimaColuna()
    {
       Celula topoColuna = inicio;
       while(topoColuna.dir != null)
       {
         topoColuna = topoColuna.dir;
       }
       Celula atual = topoColuna;
       for(int i = 0; i < linha; i++)
       {
         if(atual.esq != null)
         {
            atual.esq.dir = null;
         }
         if(atual.sup != null)
         {
            atual.sup.inf = null;
         }
         if(atual.inf != null)
         {
            atual.inf.sup = null;
         }
         
         Celula temp = atual.inf;

         atual.esq = atual.dir = atual.inf = atual.sup = null;

         atual = temp;
       }
       coluna--;
    }

}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int linha = sc.nextInt();
        int coluna = sc.nextInt();
        Matriz matriz = new Matriz(linha, coluna);
        matriz.inserir(sc);
        matriz.mostrar();
        System.out.println("Soma da diagonal principal: " + matriz.somarDiagonalPrincipal());
        System.out.println("Soma da diagonal secundária: " + matriz.somarDiagonalSecundaria());
        matriz.removerUltimaColuna();
        matriz.mostrar();
    }
    
}
