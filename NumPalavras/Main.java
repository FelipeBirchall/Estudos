package NumPalavras;

import java.util.*;

public class Main {
    static public void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String frase = scanner.nextLine();

        // Loop que continua até que o usuário digite "FIM"
        while (finalizar(frase) == false) {
            // Exibe o número de palavras na frase
            System.out.println(numPalavras(frase));

            frase = scanner.nextLine();

        }

        scanner.close();
    }

    // Método que conta o número de palavras em uma frase
    static int numPalavras(String frase) {
        int cont = 0;

        // Itera sobre cada caractere da frase
        for (int i = 0; i < frase.length(); i++) {
            // Se encontrar um espaço, incrementa o contador de palavras
            if (frase.charAt(i) == ' ') {
                cont++;
            }
        }

        // Retorna o número total de palavras (número de espaços + 1)
        return cont + 1;
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