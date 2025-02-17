package NumPalavras;
import java.util.*;

public class Main{

    static public void main(String[] args)
    {

        Scanner scanner = new Scanner(System.in);

        String frase = scanner.nextLine();

        System.out.println(numPalavras(frase));

        scanner.close();

    }

    static int numPalavras(String frase)
    {
        int cont = 0;
        for(int i = 0; i < frase.length(); i++)
        {
            if(frase.charAt(i) == ' ')
            {
                cont++;
            }
        }
        return cont + 1;
    }
}
