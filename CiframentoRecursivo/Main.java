package CiframentoRecursivo;
import java.util.*;

public class Main{

    static public void main(String[] args)
    {
       Scanner scanner = new Scanner(System.in);

    
       String palavra = scanner.nextLine();

       System.out.println(Ciframento(palavra,"",0));

       scanner.close();

    }

    static String Ciframento(String palavra ,String palavraCifrada, int i)
    {
        
      if(i < palavra.length())
      {
        char letra = palavra.charAt(i);
        if(letra >= 'a' && letra <= 'z')
        {
            letra = (char) (letra + 3);
            if(letra > 'z')
            {
                letra -= 26;
            }
        }
        else if(letra >= 'A' && letra <= 'Z')
        {
            letra = (char) (letra + 3);
            if(letra > 'Z')
            {
                letra -= 26;
            }
        }

        palavraCifrada += letra;
        return Ciframento(palavra,palavraCifrada, i+1);
      }

       return palavraCifrada;
    }

}
