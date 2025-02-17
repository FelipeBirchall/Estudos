package EhAnagrama;
import java.util.*;

public class Main {

    static public void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String frase1 = scanner.nextLine();
        String frase2 = scanner.nextLine();

        if(ehAnagrama(frase1, frase2)){
            System.out.println("SIM");
        }
        else{
            System.out.println("NÃO");
        }

        scanner.close();

    }

    static boolean ehAnagrama(String frase1, String frase2) {

        if (frase1.length() != frase2.length()) {
            return false;
        }

        // Converte as strings em arrays de caracteres e os ordena
        char[] array1 = frase1.toCharArray();
        char[] array2 = frase2.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);

        // Compara os arrays ordenados
        return Arrays.equals(array1, array2);
    }
}