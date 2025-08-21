package BeeCrowd_FilaAeroporto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       Scanner sc = new Scanner(System.in);
        
       String[] oeste = new String[100]; //-1
       int indexO = 0;
       String[] norte = new String[100]; //-3
       int indexN = 0;
       String[] sul = new String[100]; //-2
       int indexS = 0;
       String[] leste = new String[100]; // -4
       int indexL = 0;

       String entrada = sc.next();
       String direcaoAtual = "";
       while(!entrada.equals("0"))
       {
        if(entrada.startsWith("-"))
        {
            direcaoAtual = entrada;
        }
        else{
            switch (direcaoAtual) {
                case "-4":
                    leste[indexL++] = entrada;
                    break;
            
                case "-3":
                    norte[indexN++] = entrada;
                    break;

                case "-2":
                    sul[indexS++] = entrada;
                    break;
                
                case "-1":
                    oeste[indexO++] = entrada;
                    break;
                
                default:
                    break;
            }
        }
            entrada = sc.next();
       }
       impressaoFila(norte, sul, leste, oeste);

    }


    
    static void impressaoFila(String[] norte , String [] sul, String[] leste, String[] oeste)
    {
        // -3      // -2      // -4      // -1      
        int n = 0; int s = 0; int l = 0; int o = 0;
        while((n != tamanho(norte)) || (s != tamanho(sul)) || (l != tamanho(leste)) || (o != tamanho(oeste))){
    
            if(o != tamanho(oeste))
            {
                System.out.print(oeste[o]+ " ");
                o++;
            }
            if(n != tamanho(norte))
            {
                System.out.print(norte[n] + " ");
                n++;
            }
            if(s != tamanho(sul))
            {
                System.out.print(sul[s] + " ");
                s++;
            }
            if(l != tamanho(leste))
            {
                System.out.print(leste[l] + " ");
                l++;
            }
        }
    }

    static int tamanho(String[] vetor)
    {
       int tam = 0;
       int index = 0;
       while(vetor[index++] != null)
       {
            tam++;
       }
       return tam;
    }
}
