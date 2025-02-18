package Senha_Valida;
import java.util.*;

public class Main {

    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String Senha = scanner.nextLine();

        if (senhaValida(Senha)) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }

        scanner.close();

    }

    static boolean senhaValida(String Senha) {
        boolean temMaiuscula = false;
        boolean temMinuscula = false;
        boolean temNumero = false;
        boolean temSimbolo = false;
        if (Senha.length() < 8) {
            return false;
        }
        for (int i = 0; i < Senha.length(); i++) {

            if (Senha.charAt(i) >= 'a' && Senha.charAt(i) <= 'z') {
                temMinuscula = true;
            } 
            else if (Senha.charAt(i) >= 'A' && Senha.charAt(i) <= 'Z') {
                temMaiuscula = true;
            } 
            else if (Senha.charAt(i) >= '0' && Senha.charAt(i) <= '9') {
                temNumero = true;
            } 
            else {
                temSimbolo = true;
            }
        }
        if (temMaiuscula == false || temMinuscula == false || temNumero == false || temSimbolo == false) {
            return false;
        }
        return true;
    }

}