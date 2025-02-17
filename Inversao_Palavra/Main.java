package Inversao_Palavra;
import java.util.*;

public class Main{

    static public void main(String[] args)
    {
     
       Scanner scanner = new Scanner(System.in);

       String palavra = scanner.nextLine();
        
       palavra = Inverter(palavra);

       System.out.println(palavra);

       scanner.close();

        
    }

    static String Inverter(String palavra)
    {
        String palavraInvertida = "";
        char letra;
        for(int i = 0; i < palavra.length(); i++)
        {
              letra = palavra.charAt(palavra.length()-1-i);

             palavraInvertida += letra;
        }
        return palavraInvertida;
    }
}