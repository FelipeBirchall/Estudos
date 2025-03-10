package Senha_Valida;

import java.util.*;

public class Main {

    static public void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String senha = scanner.nextLine();

        // Loop que continua até o usuário digitar "FIM"
        while (finalizar(senha) == false) {

            // Verifica se a senha é válida e exibe o resultado
            if (senhaValida(senha)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            senha = scanner.nextLine();
        }

        scanner.close();
    }

    // Método que verifica se uma senha é válida
    static boolean senhaValida(String senha) {

        // Variáveis para verificar os critérios de validação
        boolean ehValida = true;
        boolean tamanhoValido = true;
        boolean temMaiuscula = false;
        boolean temMinuscula = false;
        boolean temNumero = false;
        boolean temSimbolo = false;

        // Verifica se a senha tem pelo menos 8 caracteres
        if (senha.length() < 8) {
            tamanhoValido = false; // Senha inválida se tiver menos de 8 caracteres
        }

        // Itera sobre cada caractere da senha
        for (int i = 0; i < senha.length(); i++) {
            if (tamanhoValido == false) {
                i = senha.length();
            }
            // Verifica se o caractere é uma letra minúscula
            else if (senha.charAt(i) >= 'a' && senha.charAt(i) <= 'z') {
                temMinuscula = true;
            }
            // Verifica se o caractere é uma letra maiúscula
            else if (senha.charAt(i) >= 'A' && senha.charAt(i) <= 'Z') {
                temMaiuscula = true;
            }
            // Verifica se o caractere é um número
            else if (senha.charAt(i) >= '0' && senha.charAt(i) <= '9') {
                temNumero = true;
            }
            // Se não for letra nem número, considera como símbolo
            else {
                temSimbolo = true;
            }
        }
        // Verifica se todos os critérios foram atendidos
        if (tamanhoValido == false || temMinuscula == false || temMaiuscula == false || temNumero == false
                || temSimbolo == false) {
            ehValida = false; // Senha inválida se algum critério não for atendido
        }

        return ehValida; // Retorna true se a senha for válida, caso contrário, false
    }

    // Verifica se a palavra é "FIM"
    static boolean finalizar(String senha) {
        boolean FIM = false;

        // Confere se os três primeiros caracteres são 'F', 'I' e 'M' e se a string tem
        // exatamente 3 caracteres
        if (senha.charAt(0) == 'F' && senha.charAt(1) == 'I' && senha.charAt(2) == 'M' &&
                senha.length() == 3) {
            FIM = true;
        }

        return FIM;
    }

}