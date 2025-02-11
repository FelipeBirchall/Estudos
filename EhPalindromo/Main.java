package EhPalindromo;
import java.util.Scanner;


public class Main{
    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            String palavra = scanner.nextLine();
            if(palavra.equalsIgnoreCase("FIM")){break;}
            
            if(EhPalindromo(palavra)){System.out.println("SIM");}
            else {System.out.println("NÃO");}
        }
        scanner.close();
        
    }
    static boolean EhPalindromo(String palavra)
    {

        palavra = palavra.replaceAll("\\s+", "").toLowerCase();
        int esq = 0;
        int dir = palavra.length() - 1;
        
        while(esq < dir)
        {
            if(palavra.charAt(esq) != palavra.charAt(dir)){return false;}
            esq++;
            dir--;
        }
        return true;
        
    }
}