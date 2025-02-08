package Area_Testes;
import java.util.Scanner;

public class Main{

    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDigite um valor: ");
        int A = scanner.nextInt();
        int dobro = A * 2;
        System.out.print("\nDobro do valor" + A + "é: " + dobro);

        scanner.close();
    }
}