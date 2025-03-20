import java.util.Scanner;

class Lista {
    private int[] array;
    private int n;

    Lista() {
        array = new int[6];
    }

    Lista(int tamanho) {
        array = new int[tamanho];
        n = 0;
    }

    public void inserirInicio(int x) throws Exception {
        if (n >= array.length) {
            throw new Exception("\nLista cheia!");
        }
        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = x;
        n++;
    }

    public void inserirFim(int x) throws Exception {
        if (n >= array.length) {
            throw new Exception("\nLista cheia!");
        }
        array[n] = x;
        n++;
    }

    public void inserir(int x, int pos) throws Exception {

        if (n >= array.length || pos < 0 || pos > n) {
            throw new Exception("\nLista cheia ou posição inválida!");
        }
        for (int i = n; i > pos; i--) {
            array[i] = array[i - 1];
        }
        array[pos] = x;
        n++;
    }

    public int removerInicio() throws Exception {
        if (n == 0) {
            throw new Exception("\nLista Vazia!");
        }
        int resp = array[0];
        n--;
        for (int i = 0; i < n; i++) {
            array[i] = array[i + 1];
        }
        return resp;
    }

    public int removerFim() throws Exception {
        if (n == 0) {
            throw new Exception("\nLista vazia!");
        }
        return array[--n];
    }

    public int remover(int pos) throws Exception {
        if (n == 0 || pos < 0 || pos > n) {
            throw new Exception("\nLista vazia ou posição inválida!");
        }
        int resp = array[pos];
        n--;

        for (int i = pos; i < n; i++) {
            array[i] = array[i + 1];
        }
        return resp;
    }

    public void mostrar() throws Exception {
        if (n == 0) {
            throw new Exception("\nArray vazio!");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("[" + array[i] + "] ");
        }
        System.out.println();
    }
}

public class Main {

    static public void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Lista lista = new Lista(n);
        int opcao = -1;
        int x;
        int pos;
        int subopcao;
        while (opcao != 0) {
            System.out.println("\n-----  LISTA  -----");
            System.out.println("Inserir - 3");
            System.out.println("Remover - 2");
            System.out.println("Listar - 1");
            System.out.println("Encerrar - 0");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {

                case 3:
                    System.out.println("\n-----   INSERINDO  -----");
                    System.out.println("Inserir no início - 3");
                    System.out.println("Inserir no fim - 2");
                    System.out.println("Inserir e selecionar posição - 1");
                    System.out.println("Retornar - 0");
                    System.out.print("Opção: ");
                    subopcao = scanner.nextInt();
                    switch (subopcao) {
                        case 3:
                            x = scanner.nextInt();
                            lista.inserirInicio(x);
                            break;

                        case 2:
                            x = scanner.nextInt();
                            lista.inserirFim(x);
                            break;

                        case 1:
                            x = scanner.nextInt();
                            pos = scanner.nextInt();
                            lista.inserir(x, pos);
                            break;

                        case 0:
                            System.out.println("Retornando...");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("\n-----   REMOVENDO  -----");
                    System.out.println("Remover no início - 3");
                    System.out.println("Remover no fim - 2");
                    System.out.println("Remover e selecionar posição - 1");
                    System.out.println("Retornar - 0");
                    System.out.print("Opção: ");
                    subopcao = scanner.nextInt();
                    switch (subopcao) {
                        case 3:
                            System.out.println("\n" + lista.removerInicio());
                            break;

                        case 2:
                            System.out.println("\n" + lista.removerFim());
                            break;

                        case 1:
                            pos = scanner.nextInt();
                            System.out.println(lista.remover(pos));
                            break;

                        case 0:
                            System.out.println("Retornando...");
                            break;
                    }
                    break;

                case 1:
                    lista.mostrar();
                    break;

                case 0:
                    System.out.println("PROGRAMA ENCERRADO");
                    break;
            }

        }
        scanner.close();
    }
}