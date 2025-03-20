package BeeCrowd_EtiquetaNatal;
import java.util.Scanner;

public class Main {

    static boolean Ehigual(String linguas , String traducoesRequeridas)
    {
        boolean igual = true;
        if(linguas.length() != traducoesRequeridas.length())
        {
            igual = false;
        }
        for(int i = 0; i < linguas.length(); i++)
        {
            if(igual == false)
            {
                i = linguas.length();
            }
            else if(linguas.charAt(i) != traducoesRequeridas.charAt(i))
            {
                igual = false;
                i = linguas.length();
            }
        }
        return igual;
    }

    static void etiquetas(int M, String[] linguas, String[] traducoes) {

        String[] criancas = new String[M];
        String[] traducoesRequeridas = new String[M];

        for (int i = 0; i < M; i++) {

            criancas[i] = sc.nextLine();
            traducoesRequeridas[i] = sc.nextLine(); 
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < linguas.length; j++) {
                if (Ehigual(linguas[j], traducoesRequeridas[i])) {

                    System.out.println(criancas[i]);
                    System.out.println(traducoes[j] + "\n");

                    j = linguas.length; 
                }
            }
        }
    }

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int N = sc.nextInt();
        sc.nextLine();

        String[] linguas = new String[N];
        String[] traducoes = new String[N];

        for (int i = 0; i < N; i++) {
            linguas[i] = sc.nextLine();
            traducoes[i] = sc.nextLine();
        }

        int M = sc.nextInt();
        sc.nextLine();

        etiquetas(M, linguas, traducoes);
        
        sc.close();
    }
}
