package SomaDeDigitosRecursivo;

import java.util.*;

public class Main {

    static public void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String num = scanner.nextLine();

        // Loop que continua até que o usuário digite "FIM"
        while (finalizar(num) == false) {

            // Chama a função recursiva Soma para calcular a soma dos dígitos da string
            int soma = Soma(num, 0, 0);

            System.out.println(soma);

            num = scanner.nextLine();
        }

        scanner.close();
    }

    // Método recursivo para calcular a soma dos dígitos de uma string
    static int Soma(String num, int soma, int i) {

        if (i < num.length()) {
            // Converte o caractere atual para o valor numérico correspondente e adiciona à
            // soma
            // Subtraindo o valor ASCII de '0' (48) do caractere atual, obtemos o valor
            // numérico.
            soma += (num.charAt(i) - '0');

            return Soma(num, soma, i + 1);
        }

        return soma;
    }

    // Verifica se a palavra é "FIM"
    static boolean finalizar(String num) {
        boolean FIM = false;

        // Confere se os três primeiros caracteres são 'F', 'I' e 'M' e se a string tem
        // exatamente 3 caracteres
        if (num.charAt(0) == 'F' && num.charAt(1) == 'I' && num.charAt(2) == 'M' &&
                num.length() == 3) {
            FIM = true;
        }

        return FIM;
    }
}