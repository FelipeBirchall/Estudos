package segundo_codigo;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int n = N;
        int[] notas = {100, 50, 20, 10, 5, 2, 1};

        System.out.println(n); 

        for (int nota : notas) {
            int quantidade = N / nota;
            N %= nota; 

            System.out.println(quantidade + " nota(s) de R$ " + nota + ",00");
        }

        scanner.close();
    }
}
