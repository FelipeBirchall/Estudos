import java.util.*;


public class Main{

    static Scanner scanner = new Scanner(System.in);
    static public void main(String[] args)
    {

        String frase1 = scanner.nextLine();
        if(soVogal(frase1)){System.out.println("SIM");}
        else{System.out.println("NAO");}

        String frase2 = scanner.nextLine();
        if(soConsoante(frase2)){System.out.println("SIM");}
        else{System.out.println("NAO");}

        scanner.close();

    }

    static boolean soVogal(String frase1)
    {
         char[] letras = new char[frase1.length()];
         for(int i = 0; i < frase1.length(); i++)
         {
              letras[i] = frase1.charAt(i);
              if(letras[i] != 'a' && letras[i] != 'e' && letras[i] != 'i' && letras[i] != 'o' && letras[i] != 'u' &&
              letras[i] != 'A' && letras[i] != 'E' && letras[i] != 'I' && letras[i] != 'O' && letras[i] != 'U')
              {
                 return false;
              }
         }
         return true;
    }


    static boolean soConsoante(String frase2)
    {
        char[] letras = new char[frase2.length()];
        for(int i = 0; i < frase2.length(); i++)
        {
            letras[i] = frase2.charAt(i);
            if(letras[i] == 'a' || letras[i] == 'e' || letras[i] == 'i' || letras[i] == 'o' || letras[i] == 'u' ||
              letras[i] == 'A' || letras[i] == 'E' || letras[i] == 'I' || letras[i] == 'O' || letras[i] == 'U')
              {
                 return false;
              }
        }
        return true;
    }
}