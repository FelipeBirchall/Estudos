package Exercicio20;
import java.util.*;

public class Main {

    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String palavra = scanner.nextLine();

        while(!palavra.equalsIgnoreCase("FIM")) {

            if(soVogais(palavra, 0 , true)){
                System.out.print("SIM ");
            } else{
                System.out.print("NAO ");
            }

            if(soConsoantes(palavra, 0, true)) {
                System.out.print("SIM ");
            } else{
                System.out.print("NAO ");
            }

            if(ehInteiro(palavra, 0, true)) {
                System.out.print("SIM ");
            } else{
                System.out.print("NAO ");
            }

            if(ehReal(palavra, 0, false, true)) {
                System.out.print("SIM\n");
            } else{
                System.out.print("NAO\n");
            }

            palavra = scanner.nextLine();

        }

        scanner.close();

    }


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


    static boolean soConsoantes(String palavra, int i , boolean consoantes){
        if(i < palavra.length()){
            if (palavra.charAt(i) == 'a' || palavra.charAt(i) == 'e' || palavra.charAt(i) == 'i'
                    || palavra.charAt(i) == 'o'
                    || palavra.charAt(i) == 'u' || palavra.charAt(i) == 'A' || palavra.charAt(i) == 'E'
                    || palavra.charAt(i) == 'I' || palavra.charAt(i) == 'O' || palavra.charAt(i) == 'U'
                    || palavra.charAt(i) >= '0' || palavra.charAt(i) <= '9' || palavra.charAt(i) == '.'
                    || palavra.charAt(i) == ','){
                consoantes = false;
            }
            return soConsoantes(palavra, i + 1, consoantes);
        }
        return consoantes;
    }


    static boolean ehInteiro(String palavra, int i , boolean inteiro){
        if(i < palavra.length()){
            if(palavra.charAt(i) < '0' || palavra.charAt(i) > '9'){
                inteiro = false;
            }
            return ehInteiro(palavra, i + 1, inteiro);
        }
        return inteiro;
    }


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
}