package BeeCrowd_Parenteses;
import java.util.Scanner;

public class Main{


    static boolean Verificar(String entrada)
    {
        boolean correct = true;
        int contador = 0;
        for (int i = 0; i < entrada.length(); i++) {
            if (entrada.charAt(i) == '(') {
                contador++;
            } else if (entrada.charAt(i) == ')') {
                contador--;
                if (contador < 0) { 
                    correct = false; // Fecha um parêntese antes de abrir
                    i = entrada.length();
                }
            }
        }
        if(contador != 0)
        {
            correct = false;
        }

        return correct;
    }


    static public void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();

        String entrada;
        for(int i = 0; i < N; i++)
        {
            entrada = sc.nextLine();
            if(Verificar(entrada) == true)
            {
                System.out.println("correct");
            }
            else{
                System.out.println("incorrect");
            }
        }

        sc.close();
    }
}
