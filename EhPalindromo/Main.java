package EhPalindromo;
import java.util.Scanner;

public class Main{
    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String palavra = scanner.nextLine();
        while(!palavra.equalsIgnoreCase("FIM"))
        {
            if(EhPalindromo(palavra)){System.out.println("SIM");}
            else {System.out.println("NÃO");}
            palavra = scanner.nextLine();
        }
        scanner.close();
        
    }
    static boolean EhPalindromo(String palavra)
    {
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