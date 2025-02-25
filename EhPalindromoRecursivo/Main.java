package EhPalindromoRecursivo;

import java.util.*;

public class Main {
    static public void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String palavra = scanner.nextLine();

        // Loop que continua até o usuário digitar "FIM"
        while (finalizar(palavra) == false) {

            // Verifica se a palavra é um palíndromo e exibe o resultado
            if (EhPalindromo(palavra, 0, true)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            palavra = scanner.nextLine();
        }

        scanner.close();
    }

    // Método recursivo que verifica se uma palavra é um palíndromo
    static boolean EhPalindromo(String palavra, int i, boolean palindromo) {

        // Verifica se o índice atual (i) é menor que o comprimento da palavra
        if (i < palavra.length()) {

            // Compara o caractere na posição i com o caractere na posição simétrica
            if (palavra.charAt(i) != palavra.charAt(palavra.length() - 1 - i)) {
                palindromo = false; // Se os caracteres forem diferentes, não é palíndromo
                i = palavra.length();
            }
            // Chama recursivamente a função para o próximo índice (i + 1)
            return EhPalindromo(palavra, i + 1, palindromo);
        }

        return palindromo;
    }

    // Verifica se a palavra é "FIM"
    static boolean finalizar(String palavra) {  
    boolean FIM = false;

    // Confere se os três primeiros caracteres são 'F', 'I' e 'M' e se a string tem exatamente 3 caracteres
    if (palavra.charAt(0) == 'F' &&  palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M' && 
        palavra.length() == 3)
    {  
        FIM = true;  
    }

    return FIM;  
    }
}