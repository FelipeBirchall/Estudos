package Exercicio20;
import java.util.*;

public class Main {

    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String palavra = scanner.nextLine();

        // Continua até o usuário digitar "FIM"
        while(finalizar(palavra) == false) {

           // Verifica se a palavra contém apenas vogais
           if (soVogais(palavra, 0, true)) {
            System.out.print("SIM ");
        } else {
            System.out.print("NAO ");
        }

        // Verifica se a palavra contém apenas consoantes
        if (soConsoantes(palavra, 0, true)) {
            System.out.print("SIM ");
        } else {
            System.out.print("NAO ");
        }

        // Verifica se a palavra é um número inteiro
        if (ehInteiro(palavra, 0, true)) {
            System.out.print("SIM ");
        } else {
            System.out.print("NAO ");
        }

        // Verifica se a palavra é um número real
        if (ehReal(palavra, 0, false, true)) {
            System.out.print("SIM\n");
        } else {
            System.out.print("NAO\n");
        }

        palavra = scanner.nextLine();
    }

    scanner.close();

    }

    // Verifica se a palavra contém apenas vogais
    static boolean soVogais(String palavra, int i , boolean vogais) {
        if(i < palavra.length()){
            if(palavra.charAt(i) != 'a' && palavra.charAt(i) != 'e' && palavra.charAt(i) != 'i'
                    && palavra.charAt(i) != 'o'
                    && palavra.charAt(i) != 'u' && palavra.charAt(i) != 'A' && palavra.charAt(i) != 'E'
                    && palavra.charAt(i) != 'I' && palavra.charAt(i) != 'O' && palavra.charAt(i) != 'U'){
                vogais = false;
            }
            return soVogais(palavra, i + 1 , vogais);
        }

        return vogais;
    }

    // Verifica se a palavra contém apenas consoantes
    static boolean soConsoantes(String palavra, int i , boolean consoantes){
        if(i < palavra.length()){
            if (palavra.charAt(i) == 'a' || palavra.charAt(i) == 'e' || palavra.charAt(i) == 'i'
                    || palavra.charAt(i) == 'o'
                    || palavra.charAt(i) == 'u' || palavra.charAt(i) == 'A' || palavra.charAt(i) == 'E'
                    || palavra.charAt(i) == 'I' || palavra.charAt(i) == 'O' || palavra.charAt(i) == 'U'
                    || (palavra.charAt(i) >= '0' && palavra.charAt(i) <= '9') || palavra.charAt(i) == '.'
                    || palavra.charAt(i) == ','){
                consoantes = false;
            }
            return soConsoantes(palavra, i + 1, consoantes);
        }
        return consoantes;
    }

    // Verifica se a palavra contém apenas números inteiros
    static boolean ehInteiro(String palavra, int i , boolean inteiro){
        if(i < palavra.length()){
            if(palavra.charAt(i) < '0' || palavra.charAt(i) > '9'){
                inteiro = false;
            }
            return ehInteiro(palavra, i + 1, inteiro);
        }
        return inteiro;
    }

    // Verifica se a palavra contém apenas números reais
    static boolean ehReal(String palavra, int i, boolean pontoOuVirgula, boolean Real) {
        if(i < palavra.length()){
            if(palavra.charAt(i) == '.' || palavra.charAt(i) == ','){
                if(pontoOuVirgula){
                    Real = false;
                }
                pontoOuVirgula = true;
            } else if(palavra.charAt(i) < '0' || palavra.charAt(i) > '9'){
                Real = false;
            }
            return ehReal(palavra, i + 1, pontoOuVirgula, Real);
        }
        return Real;
    }

    // Verifica se a palavra é "FIM"
    static boolean finalizar(String palavra) {
        boolean FIM = false;

        // Confere se os três primeiros caracteres são 'F', 'I' e 'M' e se a string tem exatamente 3 caracteres
        if (palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M' &&
                palavra.length() == 3) {
            FIM = true;
        }

        return FIM;
    }

    
}