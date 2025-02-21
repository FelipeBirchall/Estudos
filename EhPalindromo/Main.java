import java.util.Scanner;

public class Main {
    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String palavra = scanner.nextLine();

        // Loop continua até que o usuário digite "FIM"
        while (!palavra.equalsIgnoreCase("FIM")) {

            // Verifica se a palavra é um palíndromo e imprime "SIM" ou "NAO" conforme o resultado
            if (EhPalindromo(palavra)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            palavra = scanner.nextLine();
        }
        scanner.close();

    }

    // Método para verificar se uma palavra é um palíndromo
    static boolean EhPalindromo(String palavra) {
        boolean palindromo = true;
        int esq = 0; 
        int dir = palavra.length() - 1; 

        // Compara os caracteres da esquerda para a direita e da direita para a esquerda
        while (esq < dir) {
           
            if (palavra.charAt(esq) != palavra.charAt(dir)) {
                palindromo = false;
            }
            // Adiciona +1 na esq e -1 na dir enquanto não houver diferença
            esq++;
            dir--;
        }
       
        return palindromo;

    }
}