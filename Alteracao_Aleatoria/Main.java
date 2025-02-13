package Alteracao_Aleatoria;
import java.util.*;

public class Main{

    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Random gerador = new Random();
        String frase = scanner.nextLine();
        gerador.setSeed(4);

        while(!frase.equalsIgnoreCase("FIM"))
        {
            
           char num1 = ((char) ('a' + (Math.abs(gerador.nextInt())% 26) ) );
           char num2 = ((char) ('a' + (Math.abs(gerador.nextInt())% 26) ) );
           
           char[] letra = new char[frase.length()];
           
           for(int i = 0; i < frase.length(); i++)
            {
                letra[i] = frase.charAt(i);
                if(letra[i] == num1)
                {
                    letra[i] = num2;
                }
            }

            String fraseFinal = "";
            
            for(int i = 0; i < frase.length(); i++)
            {
                fraseFinal += letra[i];
            }
            System.out.println(fraseFinal);
            frase = scanner.nextLine();
        }

        scanner.close();
    }

  }