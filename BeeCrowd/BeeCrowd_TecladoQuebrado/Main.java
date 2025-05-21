package BeeCrowd_TecladoQuebrado;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);


        String frase = sc.nextLine();

        ArrayList<String> reconstruida = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        boolean inicio = false;

        for(int i = 0; i < frase.length(); i++)
        {
            char c = frase.charAt(i);
            if(c == '[' || c == ']')
            {
                
                if(inicio)
                {
                    reconstruida.add(0, sb.toString());
                }
                else{
                    reconstruida.add(sb.toString());
                }
                sb.setLength(0);
                inicio = (c == '[');
            }
            else{
                sb.append(c);
            }
        }
         // Adiciona o texto restante
            if (inicio) {
               reconstruida.add(0,sb.toString());
            } else {
               reconstruida.add(sb.toString());
            }
         for (String s : reconstruida) {
                System.out.print(s);
            }
    }
    
}
