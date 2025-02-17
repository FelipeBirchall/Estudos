package Exercicio6;

import java.util.*;

public class Main {


    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        // Lê a primeira linha de entrada e verifica se contém apenas vogais
        String frase1 = scanner.nextLine();
        if (soVogal(frase1)) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }

        // Lê a segunda linha de entrada e verifica se contém apenas consoantes
        String frase2 = scanner.nextLine();
        if (soConsoante(frase2)) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }

        // Lê a terceira linha de entrada e verifica se é um número inteiro
        String frase3 = scanner.nextLine();
        if (ehInteiro(frase3)) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }

        // Lê a quarta linha de entrada e verifica se é um número real
        String frase4 = scanner.nextLine();
        if (ehReal(frase4)) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }

        scanner.close();
    }

    // Método para verificar se a string contém apenas vogais
    static boolean soVogal(String frase1) {
        char[] letras = new char[frase1.length()];
        for (int i = 0; i < frase1.length(); i++) {
            letras[i] = frase1.charAt(i);
            // Verifica se o caractere atual não é uma vogal (maiúscula ou minúscula)
            if (letras[i] != 'a' && letras[i] != 'e' && letras[i] != 'i' && letras[i] != 'o' && letras[i] != 'u' &&
                letras[i] != 'A' && letras[i] != 'E' && letras[i] != 'I' && letras[i] != 'O' && letras[i] != 'U') {
                return false;
            }
        }
        return true;
    }

    // Método para verificar se a string contém apenas consoantes
    static boolean soConsoante(String frase2) {
        char[] letras = new char[frase2.length()];
        for (int i = 0; i < frase2.length(); i++) {
            letras[i] = frase2.charAt(i);
            // Verifica se o caractere atual é uma vogal (maiúscula ou minúscula)
            if (letras[i] == 'a' || letras[i] == 'e' || letras[i] == 'i' || letras[i] == 'o' || letras[i] == 'u' ||
                letras[i] == 'A' || letras[i] == 'E' || letras[i] == 'I' || letras[i] == 'O' || letras[i] == 'U') {
                return false;
            }
        }
        return true;
    }

    // Método para verificar se a string é um número inteiro
    static boolean ehInteiro(String frase3) {
        try {
            // Tenta converter a string para um número inteiro
            Integer.parseInt(frase3);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método para verificar se a string é um número real
    static boolean ehReal(String frase4) {
        try {
            // Tenta converter a string para um número real (float)
            Float.parseFloat(frase4);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}