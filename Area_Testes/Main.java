package Area_Testes;

import java.util.Scanner;

class Equipe{

    private Duende[] membros = new Duende[3];
    private int cont = 0;

    public void adicionarMembro(Duende duende)
    {
        if(cont < 3)
        {
           membros[cont] = duende;
           cont++;
        }
    }

    public void mostrarEquipe(int numero) {
        System.out.println("Time " + numero);
        for (int i = 0; i < cont; i++) {
            membros[i].mostrar();
        }
        System.out.println();
    }



}

class Duende {
    private String nome;
    private int idade;

    public Duende(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void mostrar()
    {
        System.out.println(nome + " " + idade);
    }


}

public class Main {
    static public void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String nome;
        int idade;
        Duende[] duende = new Duende[N];
        
        for (int i = 0; i < N; i++) {
            nome = sc.next();
            idade = sc.nextInt();
            duende[i] = new Duende(nome, idade);
        }
        
        Ordenar(N, duende);
        
        int numEquipes = N/3;
        Equipe[] equipe = new Equipe[numEquipes];

        for(int i = 0; i < numEquipes; i++)
        {
            equipe[i] = new Equipe();
        }
        
        formarEquipes(N, duende ,numEquipes , equipe);

        for(int i = 0; i < numEquipes; i++)
        {
            equipe[i].mostrarEquipe(i+1);
        }

        sc.close();

    }

    static void Ordenar(int N, Duende[] duende) {
        for (int i = 0; i < N - 1; i++) {
            int maior = i;
            for (int j = i + 1; j < N; j++) {
                if (duende[maior].getIdade() < duende[j].getIdade() || (duende[maior].getIdade() == duende[j].getIdade()
                        && comparaNome(duende[maior].getNome(), duende[j].getNome()))) {
                            maior = j;
                }
            }
            Duende temp = duende[i];
            duende[i] = duende[maior];
            duende[maior] = temp;
        }
    }

    static void formarEquipes(int N , Duende[] duende , int numEquipes , Equipe[] equipe)
    {
        int index = 0;
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < numEquipes; j++)
            {
                equipe[j].adicionarMembro(duende[index]);
                index++;
            }
        }
    }

    static boolean comparaNome(String duende1, String duende2) {
        int len = Math.min(duende1.length(), duende2.length());
        boolean ehOrdenado = false;
        for (int i = 0; i < len; i++) {
            if (duende1.charAt(i) > duende2.charAt(i)) {
                ehOrdenado = true;
                i = len;
            }
        }
        return ehOrdenado;
    }

}
