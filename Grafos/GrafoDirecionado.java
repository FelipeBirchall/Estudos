import java.io.*;
import java.util.*;

class Celula{
    
    int elemento;
    Celula prox;

    public Celula(){
        this.elemento = 0;
        this.prox = null;
    }

    public Celula(int elemento){
        this.elemento = elemento;
        this.prox = null;
    }
}

class Lista {

    private Celula primeiro, ultimo;

    public Lista() {
        ultimo = primeiro = new Celula();
    }

    public void inserir(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public void mostrar() {
        for(Celula i = primeiro.prox; i != null; i = i.prox){
            System.out.print(i.elemento + " ");
        }
    }

    public int tamanho(){
        int count = 0;
        for(Celula i = primeiro.prox; i != null; i = i.prox){
            count++;
        }
        return count;
    }
}

public class GrafoDirecionado {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = input.nextLine().trim();

        System.out.print("Digite o vertice: ");
        int verticeConsulta = input.nextInt();

        try {

            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));

            String linha = br.readLine();
            String[] primeira = linha.trim().split("\\s+");

            int n = Integer.parseInt(primeira[0]);
            int m = Integer.parseInt(primeira[1]);

            Lista[] adjOut = new Lista[n + 1];
            Lista[] adjIn = new Lista[n + 1];

            for(int i = 1; i <= n; i++){
                adjOut[i] = new Lista();
                adjIn[i] = new Lista();
            }

            for(int i = 0; i < m; i++){

                linha = br.readLine();
                String[] partes = linha.trim().split("\\s+");

                int origem = Integer.parseInt(partes[0]);
                int destino = Integer.parseInt(partes[1]);

                adjOut[origem].inserir(destino);
                adjIn[destino].inserir(origem);
            }

            br.close();

            System.out.println("\nVertice: " + verticeConsulta);

            System.out.println("Grau de saida: " + adjOut[verticeConsulta].tamanho());
            System.out.println("Grau de entrada: " + adjIn[verticeConsulta].tamanho());

            System.out.print("Sucessores: ");
            adjOut[verticeConsulta].mostrar();

            System.out.print("\nPredecessores: ");
            adjIn[verticeConsulta].mostrar();

            System.out.println();

        } catch(Exception e){
            System.out.println("Erro ao ler arquivo.");
        }
        input.close();
    }
}