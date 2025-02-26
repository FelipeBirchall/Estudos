
import java.util.Scanner;

public class Main {
    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String palavra = scanner.nextLine();

        // Loop continua até que o usuário digite "FIM"
        while (finalizar(palavra) == false) {

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

    // Função para verificar se uma palavra é um palíndromo
    static boolean EhPalindromo(String palavra) {
        boolean palindromo = true;
        int esq = 0; 
        int dir = palavra.length() - 1; 

        // Compara os caracteres da esquerda para a direita e da direita para a esquerda
        while (esq < dir) {
           
            // Se os caracteres nas posições atuais não forem iguais, a palavra não é um palíndromo
            if (palavra.charAt(esq) != palavra.charAt(dir)) {
                palindromo = false;
                esq = palavra.length();
            }
            // Move os índices para o próximo par de caracteres enquanto não houver diferença
            esq++;
            dir--;
        }
        // Retorna true se for palíndromo, caso contrário, false
        return palindromo;

    }

        // Verifica se a palavra é "FIM"
    static boolean finalizar(String palavra) {  
    boolean FIM = false;

    // Confere se os três primeiros caracteres são 'F', 'I' e 'M' e se a string tem exatamente 3 caracteres
    if (palavra.charAt(0) == 'F' &&  palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M' && 
        palavra.length() == 3)
    {  
        FIM = true;  
    }

    return FIM;  
}
}