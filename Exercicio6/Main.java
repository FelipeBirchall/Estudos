package Exercicio6;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String palavra = scanner.nextLine();

        // Loop que continua até que o usuário digite "FIM"
        while (finalizar(palavra) == false) {

            // Verifica se a palavra contém apenas vogais e imprime "SIM" ou "NAO"
            if (soVogal(palavra)) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }

            // Verifica se a palavra contém apenas consoantes e imprime "SIM" ou "NAO"
            if (soConsoante(palavra)) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }

            // Verifica se a palavra é um número inteiro e imprime "SIM" ou "NAO"
            if (ehInteiro(palavra)) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }

            // Verifica se a palavra é um número real e imprime "SIM" ou "NAO"
            if (ehReal(palavra)) {
                System.out.print("SIM\n");
            } else {
                System.out.print("NAO\n");
            }

            palavra = scanner.nextLine();
        }

        scanner.close();
    }

    // Método para verificar se a string contém apenas vogais
    static boolean soVogal(String palavra) {
        boolean vogais = true;
        char[] letras = new char[palavra.length()];
        for (int i = 0; i < palavra.length(); i++) {
            letras[i] = palavra.charAt(i);
            // Verifica se o caractere atual não é uma vogal (maiúscula ou minúscula)
            if (letras[i] != 'a' && letras[i] != 'e' && letras[i] != 'i' && letras[i] != 'o' && letras[i] != 'u' &&
                    letras[i] != 'A' && letras[i] != 'E' && letras[i] != 'I' && letras[i] != 'O' && letras[i] != 'U') {
                vogais = false;
            }
        }
        return vogais;
    }

    // Método para verificar se a string contém apenas consoantes
    static boolean soConsoante(String palavra) {
        boolean consoantes = true;
        char[] letras = new char[palavra.length()];
        for (int i = 0; i < palavra.length(); i++) {
            letras[i] = palavra.charAt(i);
            // Verifica se o caractere atual é uma vogal (maiúscula ou minúscula) ou um
            // número ou um ponto/vírgula
            if (letras[i] == 'a' || letras[i] == 'e' || letras[i] == 'i' || letras[i] == 'o' || letras[i] == 'u' ||
                    letras[i] == 'A' || letras[i] == 'E' || letras[i] == 'I' || letras[i] == 'O' || letras[i] == 'U'
                    || letras[i] >= '0' && letras[i] <= '9' || letras[i] == '.' || letras[i] == ',') {
                consoantes = false;
            }
        }
        return consoantes;
    }

    // Método para verificar se a string é um número inteiro
    static boolean ehInteiro(String palavra) {
        boolean inteiro = true;
        for (int i = 0; i < palavra.length(); i++) {
            // Verifica se o caractere atual não é um dígito
            if (palavra.charAt(i) < '0' || palavra.charAt(i) > '9') {
                inteiro = false;
            }
        }
        return inteiro;
    }

    // Método para verificar se a string é um número real
    static boolean ehReal(String palavra) {
        boolean pontoOuVirgula = false;
        boolean Real = true;
        for (int i = 0; i < palavra.length(); i++) {
            // Verifica se o caractere atual é um ponto ou vírgula
            if (palavra.charAt(i) == '.' || palavra.charAt(i) == ',') {
                // Se já houve um ponto ou vírgula anteriormente, não é um número real válido
                if (pontoOuVirgula) {
                    Real = false;
                }
                pontoOuVirgula = true;
            }
            // Verifica se o caractere atual não é um dígito
            else if (palavra.charAt(i) < '0' || palavra.charAt(i) > '9') {
                Real = false;
            }
        }
        return Real;
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