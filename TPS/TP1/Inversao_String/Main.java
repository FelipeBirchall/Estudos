package Inversao_String;

import java.util.*;

public class Main {

    static public void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String palavra = scanner.nextLine();

        // Loop que continua até que o usuário digite "FIM"
        while (finalizar(palavra) == false) {
            // Chama o método Inverter para inverter a palavra
            palavra = Inverter(palavra);

            System.out.println(palavra);

            palavra = scanner.nextLine();
        }

        scanner.close();
    }

    // Método para inverter uma string
    static String Inverter(String palavra) {

        // Variável para armazenar a palavra invertida
        String palavraInvertida = "";
        // Variável temporária para armazenar cada caractere durante a inversão
        char letra;

        for (int i = 0; i < palavra.length(); i++) {
            // Obtém o caractere da posição inversa (último para o primeiro)
            letra = palavra.charAt(palavra.length() - 1 - i);

            // Atribui a letra na String
            palavraInvertida += letra;
        }

        return palavraInvertida;
    }

    // Verifica se a palavra é "FIM"
    static boolean finalizar(String palavra) {
        boolean FIM = false;

        // Confere se os três primeiros caracteres são 'F', 'I' e 'M' e se a string tem
        // exatamente 3 caracteres
        if (palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M' &&
                palavra.length() == 3) {
            FIM = true;
        }

        return FIM;
    }
}