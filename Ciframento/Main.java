package Ciframento;
import java.util.Scanner;

public class Main{

    static public void main(String[] args)
    {
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
            if (letra >= 'a' && letra <= 'z') {
                letra = (char) (letra + 3); 
                if (letra > 'z') {
                    letra -= 26; 
                }
            }

            else if (letra >= 'A' && letra <= 'Z') {
                letra = (char) (letra + 3); 
                if (letra > 'Z') {
                    letra -= 26; 
                }
            }
            palavraCifrada += letra;
        }
        return palavraCifrada;
    }
  }