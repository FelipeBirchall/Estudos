package BeeCrowd_MenorDaPilha;
import java.util.*;

class Main {
    public static void main (String[] agrs) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] presentes = new int[N];
        String entrada;
        int cont = 0; //numero de valores inseridos
        sc.nextLine();

        for(int i = 0; i < N; i++)
        {
            entrada = sc.nextLine();
            int V = 0;
            if(entrada.equals("MIN"))
            {
                if(cont > 0)
                {
                    int menor = 1000;
                    for(int j = 0; j < cont; j++)
                    {
                        if(presentes[j] < menor)
                        {
                            menor = presentes[j];
                        }
                    }
                    System.out.println(menor);
                }
                else{
                    System.out.println("EMPTY");
                }
            }
            else if(entrada.equals("POP"))
            {
                if(cont > 0)
                {
                    cont--;
                }
                else{
                    System.out.println("EMPTY");
                }
            }
            else{
                V = Integer.parseInt(entrada.substring(5));
                presentes[cont] = V;
                cont++;
            }

        }

        sc.close();

    }
}