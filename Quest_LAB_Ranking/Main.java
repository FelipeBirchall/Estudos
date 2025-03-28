import java.util.*;

class Pessoa {
    private int id;

    public Pessoa() {}

    public void inserir(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void mostrar() {
        System.out.println(id);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[][] valores = new int[N][M];
        Pessoa[] pessoa = new Pessoa[N * M];
        int index = 0;

        while(N != 0 && M != 0)
        {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    valores[i][j] = sc.nextInt();
    
                    // Verificar se o valor já foi inserido
                    boolean jaExiste = false;
                    for (int k = 0; k < index; k++) {
                        if (valores[i][j] == pessoa[k].getId()) {
                            jaExiste = true;
                            k = index;  // Já encontramos o valor, não precisa continuar
                        }
                    }
    
                    // Se não existir, adicionar ao array
                    if (!jaExiste) {
                        pessoa[index] = new Pessoa();
                        pessoa[index].inserir(valores[i][j]);
                        index++;
                    }
                }
            }
    
            // Mostrar valores únicos inseridos
            for (int i = 0; i < index; i++) {
                pessoa[i].mostrar();
            }
            N = sc.nextInt();
            M = sc.nextInt();
            index = 0;
        }

     }

        
}
