package CiframentoRecursivo;

import java.util.*;

public class Main {

    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String palavra = scanner.nextLine();

        // Continua até o usuário digitar "FIM"
        while(finalizar(palavra) == false)
        {
            System.out.println(Ciframento(palavra, "", 0));

            palavra = scanner.nextLine();
        }

        

        scanner.close();

    }

    // Cifra a palavra deslocando cada caractere em +3 na tabela ASCII
    static String Ciframento(String palavra, String palavraCifrada, int i) {

        if (i < palavra.length()) {
            char letra = palavra.charAt(i);
            if (letra >= 32 && letra <= 127) {
                // Se o elemento atender aos requisitos, ocorrerá o deslocamento
                letra = (char) (letra + 3);
            }
            palavraCifrada += letra;
            return Ciframento(palavra, palavraCifrada, i + 1);
        }

        return palavraCifrada;
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
