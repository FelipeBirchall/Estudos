package EhAnagrama;
import java.util.*;

public class Main {

    static public void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String frase1 = scanner.nextLine();
        String frase2 = scanner.nextLine();

        if(ehAnagrama(frase1, frase2)){
            System.out.println("SIM");
        }
        else{
            System.out.println("NÃO");
        }

        scanner.close();

    }

    static boolean ehAnagrama(String frase1 ,String frase2)
    {
        boolean anagrama = true;
        int n = frase1.length();
        int cont = 0;
        if(n != frase2.length())
        {
            return false;
        }
        char[] letra1 = new char[n];
        char[] letra2 = new char[n];

        for (int i = 0; i < n; i++){
            letra1[i] = frase1.charAt(i);
            letra2[i] = frase2.charAt(i);
        }
    
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(letra1[i] == letra2[j]){
                    cont++;
                    j = n;
                }
            }
           if(cont < 1){anagrama = false;}
           cont = 0;
        }
        return anagrama;
    }

}