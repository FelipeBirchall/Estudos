package Notas_Moedas;
import java.util.Scanner;

public class Main{

    static public void main(String[] args){

        Scanner scanner = new Scanner(System.in);
         
        int[] notas = {100, 50, 20, 10, 5, 2};
        double[] moedas = {1.0, 0.50, 0.25, 0.10, 0.05, 0.01};

        double N = scanner.nextDouble();

        // Converte o valor de reais para centavos 
        int valorCentavos = (int) Math.round(N * 100);

        System.out.println("NOTAS: ");
        for(int nota : notas)
        {
            // Calcula quantas notas dessa denominação são necessárias
            int qntNotas = valorCentavos / (nota * 100) ;

            // Atualiza o valor restante após retirar as notas
            valorCentavos %= (nota * 100);

            System.out.printf("%d nota(s) de R$ %.2f\n" , qntNotas , (double) nota);
        }

 
        System.out.println("MOEDAS: ");
        for(double moeda : moedas)
        {
            // Calcula quantas moedas dessa denominação são necessárias
            int qntMoedas = valorCentavos / (int)(moeda * 100);

            // Atualiza o valor restante após retirar as moedas
            valorCentavos %= (int)(moeda * 100);

            System.out.printf("%d moeda(s) de R$ %.2f\n" , qntMoedas , moeda);
        }

        scanner.close();
    }
}