package BeeCrowd_RevisaoDeContrato;
import java.util.Scanner;

public class Main {

    static public void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int digitoFalho = sc.nextInt();
        String valor = sc.next();

        imprimir(digitoFalho, valor);

        sc.close();

    }

    static void imprimir(int digitoFalho, String valor) {
        char digito = (char) ('0' + digitoFalho);
        String caracteresImpressos = ""; // String para armazenar caracteres já impressos
        
        for (int i = 0; i < valor.length(); i++) {
            char currentChar = valor.charAt(i);
            
            // Verifica se o caractere não é o digitoFalho e ainda não foi impresso
            if (currentChar != digito && !caracteresImpressos.contains(String.valueOf(currentChar))) {
                System.out.print(currentChar); // Imprime o caractere
                caracteresImpressos += currentChar; // Adiciona o caractere à string de impressos
            }
        }
    }
    
}