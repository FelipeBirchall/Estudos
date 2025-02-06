package terceiro_codigo;
import java.util.Scanner;

public class Main{

    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int ano = N / 365;
        int mes = (N % 365) / 30;
        int dia = (N % 365) % 30 ;
        
        System.out.println(ano + "ano(s)");
        System.out.println(mes + "mes(es)");
        System.out.println(dia + "dia(s)");

        scanner.close();
    }
}