package Alteracao_Aleatoria;

import java.util.*;

public class Main {

    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random gerador = new Random(); // Gerador de números aleatórios

        String frase = scanner.nextLine();

        gerador.setSeed(4); // Define uma semente fixa para o gerador de números aleatórios (para resultados
                            // consistentes)

        // Loop continua até que o usuário digite "FIM"
        while (finalizar(frase) == false) {

            // Gera dois caracteres aleatórios entre 'a' e 'z'
            char c1 = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));
            char c2 = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));

            /*
             * System.out.println(c1); // Para depuração: imprime o primeiro caractere
             * gerado
             * System.out.println(c2); // Para depuração: imprime o segundo caractere gerado
             */

            // Aplica a alteração aleatória na frase e imprime o resultado
            System.out.println(alteracaoAleatoria(frase, c1, c2));

            frase = scanner.nextLine();
        }

        scanner.close();
    }

    // Método para substituir todas as ocorrências de um caractere (c1) por outro
    // (c2) na frase
    static String alteracaoAleatoria(String frase, char c1, char c2) {
        char[] letra = new char[frase.length()]; // Array para armazenar os caracteres da frase

        // Percorre cada caractere da frase
        for (int i = 0; i < frase.length(); i++) {
            letra[i] = frase.charAt(i); // Copia o caractere atual para o array

            // Se o caractere atual for igual a c1, substitui por c2
            if (letra[i] == c1) {
                letra[i] = c2;
            }
        }

        String fraseFinal = ""; // String que armazenará a frase modificada

        // Constrói a frase final a partir do array de caracteres
        for (int i = 0; i < frase.length(); i++) {
            fraseFinal += letra[i];
        }

        return fraseFinal; // Retorna a frase com as substituições aplicadas
    }

    // Verifica se a frase é "FIM"
    static boolean finalizar(String frase) {
        boolean FIM = false;

        // Confere se os três primeiros caracteres são 'F', 'I' e 'M' e se a string tem
        // exatamente 3 caracteres
        if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M' &&
                frase.length() == 3) {
            FIM = true;
        }

        return FIM;
    }
}