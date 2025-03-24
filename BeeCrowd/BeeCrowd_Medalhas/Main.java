package BeeCrowd_Medalhas;

import java.util.Scanner;

class Paises {
    String nome;
    int O, P, B;

    public Paises() {
    }

    public void inserir(String nome, int O, int P, int B) {
        this.nome = nome;
        this.O = O;
        this.P = P;
        this.B = B;
    }

    public String getNome() {
        return nome;
    }

    public int getOuro() {
        return O;
    }

    public int getPrata() {
        return P;
    }

    public int getBronze() {
        return B;
    }

    public void mostrar() {
        System.out.println(nome + " " + O + " " + P + " " + B);
    }

}

public class Main {

    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        Paises[] pais = new Paises[N];
        for (int i = 0; i < N; i++) {
            pais[i] = new Paises();
            String nome = sc.next();
            int O = sc.nextInt();
            int P = sc.nextInt();
            int B = sc.nextInt();
            pais[i].inserir(nome, O, P, B);
        }
        Ordenar(N, pais);

        System.out.println();

        for (int i = 0; i < N; i++) {
            pais[i].mostrar();
        }
        
        sc.close();
    }

    static void Ordenar(int N, Paises[] pais) {
        for (int i = 0; i < (N - 1); i++) {
            int maior = i;
            for (int j = i + 1; j < N; j++) {
                if (pais[maior].getOuro() < pais[j].getOuro()
                        || (pais[maior].getOuro() == pais[j].getOuro() && pais[maior].getPrata() < pais[j].getPrata())
                        || (pais[maior].getOuro() == pais[j].getOuro() && pais[maior].getPrata() == pais[j].getPrata()
                                && pais[maior].getBronze() < pais[j].getBronze()) || (pais[maior].getOuro() == pais[j].getOuro() && pais[maior].getPrata() == pais[j].getPrata()
                                && pais[maior].getBronze() == pais[j].getBronze() && compararNome(pais[maior].getNome(), pais[j].getNome()) == true) ) {
                    maior = j;
                }
            }

            Paises temp = pais[i];
            pais[i] = pais[maior];
            pais[maior] = temp;

        }
    }

    static boolean compararNome(String nome1, String nome2) {
        return nome1.compareTo(nome2) > 0;
    }
}
