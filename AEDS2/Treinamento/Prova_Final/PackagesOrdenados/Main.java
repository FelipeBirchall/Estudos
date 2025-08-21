package PackagesOrdenados;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String entrada = sc.nextLine();
            if(entrada.equals("1")){
            String[] packages = new String[100];
            int index = 0;
            entrada = sc.nextLine();
            while(!entrada.equals("0")){
                packages[index] = entrada;
                index++;
                entrada = sc.nextLine();
            }
            for(int i = 0; i < index - 1;i++){
                int menor = i;
                for(int j = i+1; j < index; j++){
                    if(packages[j].compareTo(packages[menor]) < 0){menor = j;}
                }
                String tmp = packages[menor];
                packages[menor] = packages[i];
                packages[i] = tmp;
            }
            for(int i = 0; i < index; i++){
                System.out.println(packages[i]);
            }
            System.out.println("\n");
        }
        }
        sc.close();
    }
    
}
