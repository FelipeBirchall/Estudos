package TRIE;
import java.util.Scanner;


class No{
    public char elemento;
    public final int tamanho = 256;
    public No[] prox;
    public boolean folha;

    public No(){
        this.elemento = ' ';
        prox = new No[tamanho];
        for(int i = 0; i < tamanho; i++){
            prox[i] = null;
        }
        folha = false;
    }
    public No(char elemento){
        this.elemento = elemento;
        prox = new No[tamanho];
        for(int i = 0; i < tamanho; i++){prox[i] = null;}
        folha = false;
    }

    public static int hash (char x){
      return (int)x;
    }
}

// Arvore TRIE
class Arvore{
    private No raiz;

    public Arvore(){
        raiz = new No();
    }

    public void inserir(String s){
        inserir(s, raiz, 0);
    }
    private void inserir(String s, No no, int i){
        System.out.println("\nEM NO(" + no.elemento + ") (" + i + ")");
        if(no.prox[s.charAt(i)] == null){
            System.out.print("--> criando filho(" + s.charAt(i) + ")");
            no.prox[s.charAt(i)] = new No(s.charAt(i));

            if(i == s.length() - 1){
                System.out.print("(folha)");
                no.prox[s.charAt(i)].folha = true;
            }
            else{
                inserir(s, no.prox[s.charAt(i)], i + 1);
            }
        }
        else if(no.prox[s.charAt(i)].folha == false && i < s.length() - 1){
            inserir(s, no.prox[s.charAt(i)], i + 1);
        }
    }

    public boolean pesquisar(String s){
        return pesquisar(s, raiz, 0);
    }
    private boolean pesquisar(String s, No no, int i){
        boolean resp = true;
        if(no.prox[s.charAt(i)] == null){
            resp = false;
        }
        else if(i == s.length() - 1){
            resp = (no.prox[s.charAt(i)].folha == true);
        }
        else if(i < s.length() - 1){
            resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
        }
        return resp;
    }

    public void mostrar(){
        mostrar("", raiz);
    }
    private void mostrar(String s, No no){
        if(no.folha == true){
            System.out.println("Palavra: " + (s + no.elemento));
        }
        else{
            for(int i = 0; i < no.prox.length; i++){
                if(no.prox[i] != null){
                    System.out.println("ESTOU EM (" + no.elemento + ") E VOU PARA (" + no.prox[i].elemento + ")");
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arvore trie = new Arvore();

        int opcao = -1;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Inserir palavra");
            System.out.println("2 - Pesquisar palavra");
            System.out.println("3 - Mostrar todas as palavras");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite a palavra para inserir: ");
                    String palavraInserir = sc.nextLine();
                    trie.inserir(palavraInserir);
                    System.out.println("\nPalavra inserida com sucesso!");
                    break;

                case 2:
                    System.out.print("Digite a palavra para pesquisar: ");
                    String palavraPesquisar = sc.nextLine();
                    boolean encontrado = trie.pesquisar(palavraPesquisar);
                    if (encontrado) {
                        System.out.println("A palavra \"" + palavraPesquisar + "\" foi encontrada na árvore.");
                    } else {
                        System.out.println("A palavra \"" + palavraPesquisar + "\" NÃO foi encontrada na árvore.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Palavras na árvore ---");
                    trie.mostrar();
                    break;

                case 0:
                    System.out.println("Encerrando programa.");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        sc.close();
    }
}
