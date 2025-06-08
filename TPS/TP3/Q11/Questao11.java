package Q11;
import java.util.Scanner;

class Celula {
    Celula dir, esq, inf, sup;
    int elemento;

    public Celula() {
        this.elemento = 0;
        this.dir = null;
        this.esq = null;
        this.inf = null;
        this.sup = null;
    }

    public Celula(int x) {
        this.elemento = x;
        this.dir = null;
        this.esq = null;
        this.inf = null;
        this.sup = null;
    }
}

class Matriz {
    private Celula inicio;
    private int linha, coluna;

    public Matriz() {
        this.linha = this.coluna = 3;
        inicio = new Celula();
        criarMatriz();
    }

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        inicio = new Celula();
        criarMatriz();
    }

    public void criarMatriz() {
        Celula linhaAtual = inicio;
        Celula colunaAtual;

        for (int j = 1; j < coluna; j++) {
            colunaAtual = new Celula();
            linhaAtual.dir = colunaAtual;
            colunaAtual.esq = linhaAtual;
            linhaAtual = colunaAtual;
        }

        Celula linhaCima = inicio;
        for (int i = 1; i < linha; i++) {
            Celula novaLinha = new Celula();
            linhaCima.inf = novaLinha;
            novaLinha.sup = linhaCima;

            linhaAtual = novaLinha;
            colunaAtual = linhaCima.dir;

            for (int j = 1; j < coluna; j++) {
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

    public void inserir(Scanner sc) {
        Celula linhaAtual = inicio;
        for (int i = 0; i < linha; i++) {
            Celula celulaAtual = linhaAtual;
            for (int j = 0; j < coluna; j++) {
                int x = sc.nextInt();
                celulaAtual.elemento = x;
                celulaAtual = celulaAtual.dir;
            }
            linhaAtual = linhaAtual.inf;
        }
    }

    public void mostrar() {
        Celula linhaAtual = inicio;
        for (int i = 0; i < linha; i++) {
            Celula celulaAtual = linhaAtual;
            for (int j = 0; j < coluna; j++) {
                System.out.print(celulaAtual.elemento + " ");
                celulaAtual = celulaAtual.dir;
            }
            System.out.println();
            linhaAtual = linhaAtual.inf;
        }
    }

    public void mostrarDiagonalPrincipal() {
        Celula atual = inicio;
        for (int i = 0; i < Math.min(linha, coluna); i++) {
            System.out.print(atual.elemento + " ");
            atual = atual.inf != null ? atual.inf.dir : null;
        }
        System.out.println();
    }

    public void mostrarDiagonalSecundaria() {
        Celula atual = inicio;
        for (int i = 0; i < coluna - 1; i++) {
            atual = atual.dir;
        }
        for (int i = 0; i < Math.min(linha, coluna); i++) {
            System.out.print(atual.elemento + " ");
            atual = atual.inf != null ? atual.inf.esq : null;
        }
        System.out.println();
    }

    public Matriz soma(Matriz outra) {
        if (this.linha != outra.linha || this.coluna != outra.coluna) {
            return null;
        }
        Matriz resultado = new Matriz(this.linha, this.coluna);
        Celula linhaAtual1 = this.inicio;
        Celula linhaAtual2 = outra.inicio;
        Celula linhaAtualRes = resultado.inicio;

        for (int i = 0; i < linha; i++) {
            Celula celulaAtual1 = linhaAtual1;
            Celula celulaAtual2 = linhaAtual2;
            Celula celulaAtualRes = linhaAtualRes;
            for (int j = 0; j < coluna; j++) {
                celulaAtualRes.elemento = celulaAtual1.elemento + celulaAtual2.elemento;
                celulaAtual1 = celulaAtual1.dir;
                celulaAtual2 = celulaAtual2.dir;
                celulaAtualRes = celulaAtualRes.dir;
            }
            linhaAtual1 = linhaAtual1.inf;
            linhaAtual2 = linhaAtual2.inf;
            linhaAtualRes = linhaAtualRes.inf;
        }
        return resultado;
    }

    public Matriz multiplicacao(Matriz outra) {
        if (this.coluna != outra.linha) {
            return null;
        }
        Matriz resultado = new Matriz(this.linha, outra.coluna);
        Celula linhaAtualRes = resultado.inicio;

        for (int i = 0; i < this.linha; i++) {
            Celula celulaAtualRes = linhaAtualRes;
            for (int j = 0; j < outra.coluna; j++) {
                int soma = 0;
                Celula celulaAtual1 = this.inicio;
                for (int k = 0; k < i; k++) {
                    celulaAtual1 = celulaAtual1.inf;
                }
                Celula celulaAtual2 = outra.inicio;
                for (int k = 0; k < j; k++) {
                    celulaAtual2 = celulaAtual2.dir;
                }
                for (int k = 0; k < this.coluna; k++) {
                    soma += celulaAtual1.elemento * celulaAtual2.elemento;
                    celulaAtual1 = celulaAtual1.dir;
                    celulaAtual2 = celulaAtual2.inf;
                }
                celulaAtualRes.elemento = soma;
                celulaAtualRes = celulaAtualRes.dir;
            }
            linhaAtualRes = linhaAtualRes.inf;
        }
        return resultado;
    }
}

public class Questao11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int casos = sc.nextInt();

        for (int t = 0; t < casos; t++) {
            int l1 = sc.nextInt();
            int c1 = sc.nextInt();
            Matriz matriz1 = new Matriz(l1, c1);
            matriz1.inserir(sc);

            int l2 = sc.nextInt();
            int c2 = sc.nextInt();
            Matriz matriz2 = new Matriz(l2, c2);
            matriz2.inserir(sc);

            matriz1.mostrarDiagonalPrincipal();
            matriz1.mostrarDiagonalSecundaria();

            Matriz soma = matriz1.soma(matriz2);
            if (soma != null) {
                soma.mostrar();
            }

            Matriz mult = matriz1.multiplicacao(matriz2);
            if (mult != null) {
                mult.mostrar();
            }
        }
        sc.close();
    }
}