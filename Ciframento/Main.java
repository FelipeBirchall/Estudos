package Ciframento;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String palavra = scanner.nextLine();
        
        // Loop continua até que o usuário digite "FIM" 
        while(finalizar(palavra) == false)
        {
            // Aplica o ciframento na palavra e imprime o resultado
            System.out.println(Ciframento(palavra));

            
            palavra = scanner.nextLine();
        }
        scanner.close();
    }

    // Método para cifrar uma palavra
    static String Ciframento(String palavra)
    {
        String palavraCifrada = ""; // String que armazenará o resultado cifrado
        
        // Percorre cada caractere da palavra
        for(int i = 0; i < palavra.length(); i++)
        {
            char letra = palavra.charAt(i); // Obtém o caractere atual
            
            // Verifica se o caractere está dentro do intervalo de caracteres imprimíveis ASCII (32 a 127)
            if((letra >= 32 && letra <= 127)){
                // Desloca o caractere 3 posições à frente na tabela ASCII
                letra = (char)(letra + 3);
            }
            // Adiciona o caractere cifrado à string de resultado
            palavraCifrada += letra;
        }
        // Retorna a palavra cifrada
        return palavraCifrada;
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