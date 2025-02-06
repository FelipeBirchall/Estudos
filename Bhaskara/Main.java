package Bhaskara;
import java.util.Scanner;

public class Main{

    static public void main(String[] args){
        Scanner scanner = new Scanner(System.in);
       
        double A = scanner.nextDouble();
        double B = scanner.nextDouble();
        double C = scanner.nextDouble();

        if(A == 0){System.out.println("Impossivel calcular");}
        else 
        {
            double delta = Math.pow(B , 2) - 4*A*C;
            if(delta < 0){System.out.println("Impossivel calcular");}
            else{
                double R1 = (-B + Math.sqrt(delta)) / (2*A);
                double R2 = (-B - Math.sqrt(delta)) / (2*A);

                System.out.printf("R1 = %.5f\n" , R1);
                System.out.printf("R2 = %.5f\n" , R2); 
            }
        }
    
        scanner.close();
    }
}