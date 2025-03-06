package EhAnagrama;
import java.util.*;

public class Main {

    static public void main(String[] args) {

        Scanner scanner = new Scanner(System.in); 
        String frase = scanner.nextLine();

        // Loop que continua até que o usuário digite "FIM"
        while (finalizar(frase) == false) {

            // Verifica se a frase é um anagrama
            if (ehAnagrama(frase)) {
                System.out.println("SIM"); // Se for anagrama, imprime "SIM"
            } else {
                System.out.println("N" + (char) ('a' + 'b') + "O"); // Se não for, imprime "NÃO"
            }

            frase = scanner.nextLine();
        }

        scanner.close(); 
    }

    // Função para verificar se duas partes de uma string são anagramas
    static boolean ehAnagrama(String frase) {
        boolean anagrama = true; // Assume que é um anagrama inicialmente
        String frase2 = ""; // String para armazenar a segunda parte da frase (após o '-')
        int n = frase.length(); // Tamanho da frase original
        int posicao = 0; // Posição do caractere '-' na frase

        // Encontra a posição do caractere '-' na frase
        for (int i = 0; i < n; i++) {
            if (frase.charAt(i) == '-') {
                posicao = i; // Armazena a posição do '-'
                i = n; 
            }
        }

        // Copia a parte da frase após o '-' para frase2
        for (int i = posicao + 2; i < n; i++) {
            char letra = frase.charAt(i);
            frase2 += letra; // Atribui cada character em frase2
        }

        // Reconstruir a primeira parte da frase (antes do '-')
        String frase1 = "";
        for (int i = 0; i < posicao - 1; i++) {
            frase1 += frase.charAt(i);// Atribui cada character em frase1
        }

        // Converte ambas as partes para minúsculas para evitar problemas com maiúsculas/minúsculas
        frase1 = frase1.toLowerCase();
        frase2 = frase2.toLowerCase();

        n = frase1.length(); // Atualiza o tamanho da primeira parte
        int cont = 0; // Contador para verificar correspondências de caracteres

        // Se as duas partes tiverem tamanhos diferentes, não podem ser anagramas
        if (n != frase2.length()) {
            anagrama = false;
        }

        // Verifica se as duas partes são anagramas
        if (anagrama == true) {
            // Compara cada caractere de frase1 com os caracteres de frase2
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (frase1.charAt(i) == frase2.charAt(j)) {
                        cont++; // Incrementa o contador se encontrar uma correspondência
                        j = n;
                    }
                }
                // Se nenhuma correspondência for encontrada, não é um anagrama
                if (cont < 1) {
                    anagrama = false;
                }
                cont = 0; // Reinicia o contador para o próximo caractere
            }
        }

        return anagrama; 
    }

    // Função para verificar se a palavra é "FIM"
    static boolean finalizar(String frase) {
        boolean FIM = false; // Assume que não é "FIM" inicialmente

        // Confere se os três primeiros caracteres são 'F', 'I' e 'M' e se a string tem exatamente 3 caracteres
        if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M' &&
                frase.length() == 3) {
            FIM = true; // Se for "FIM", retorna true
        }

        return FIM; 
    }
}