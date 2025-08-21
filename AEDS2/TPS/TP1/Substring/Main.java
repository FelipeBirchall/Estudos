package Substring;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String palavra = scanner.nextLine(); 
        
        // Continua processando entradas até que "FIM" seja digitado
        while(!finalizar(palavra)) { 
            System.out.println(maiorSubstring(palavra)); // Exibe o tamanho da maior substring sem caracteres repetidos
            palavra = scanner.nextLine();
        }

        scanner.close();
    }

    // Função que calcula o tamanho da maior substring sem caracteres repetidos
    static int maiorSubstring(String palavra) {
        int maxSubstring = 0; // Armazena o maior valor encontrado

        // Percorre a string inteira
        for(int i = 0; i < palavra.length(); i++) { // Alterado de i = 1 para i = 0
            int tamanho = 1; // Reseta o tamanho para 1 ao começar uma nova verificação
            
            // Compara o caractere atual com os anteriores
            for(int j = i - 1; j >= 0; j--) {
                if (palavra.charAt(j) == palavra.charAt(i)) {
                    j = -1; // Se for diferente, aumenta o tamanho da substring
                } else {
                    tamanho++; // Interrompe o loop ao encontrar um caractere repetido
                }
            }

            // Atualiza o maior tamanho de substring encontrado
            if (tamanho > maxSubstring) {
                maxSubstring = tamanho;
            }
        }

        return maxSubstring;
    }
    
    // Verifica se a palavra é "FIM"
    static boolean finalizar(String palavra) {
        return palavra.equals("FIM");
    }
}
