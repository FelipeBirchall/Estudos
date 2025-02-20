package Ciframento;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String palavra = scanner.nextLine();
        while(!palavra.equalsIgnoreCase("FIM"))
        {
             System.out.println(Ciframento(palavra));

             palavra = scanner.nextLine();
        }
        scanner.close();
    }

    static String Ciframento(String palavra)
    {
        String palavraCifrada = "";
        for(int i = 0; i < palavra.length(); i++)
        {
            char letra = palavra.charAt(i);
            if((letra >=32 && letra <=127)){
                letra = (char)(letra+3);
            }
            palavraCifrada += letra;
        }
        return palavraCifrada;
    }
}