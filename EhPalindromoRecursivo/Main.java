package EhPalindromoRecursivo;
import java.util.*;

public class Main {

    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String palavra = scanner.nextLine();

        while (!palavra.equalsIgnoreCase("FIM")) {

            if (EhPalindromo(palavra, 0)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            palavra = scanner.nextLine();
        }

        scanner.close();
    }

    static boolean EhPalindromo(String palavra, int i) {
        if (i < palavra.length()) {
            
            if (palavra.charAt(i) != palavra.charAt(palavra.length() - 1 - i)) {
                return false;
            }
           return EhPalindromo(palavra, i + 1);
        }
        return true;
    }
}