package BeeCrowd_FilaAeroporto;

import java.util.Scanner;

class Fila{
    
}

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

       String entrada = "-5";
       while(!entrada.equals("0"))
       {
         entrada = sc.nextLine();
         switch (entrada){
            case "-4":
                String subEntrada = sc.nextLine();
                
                break;
         
            default:
                break;
         }
         // atribuindo avioes do leste
         if(entrada.equals("-4"))
         {
            boolean posAlterada = false;
            while(posAlterada == false)
            {
                String temp = sc.next();
                if(temp.startsWith("-") || temp.equals("0"))
                {
                    entrada = temp;
                    posAlterada = true;
                }
                else{
                    leste[indexL] = temp;
                    indexL++;
                    
                }
            }
         }

        // atribuindo avioes do norte
        else if(entrada.equals("-3"))
        {
           boolean posAlterada = false;
           while(posAlterada == false)
           {
               String temp = sc.next();
               if(temp.startsWith("-") || temp.equals("0"))
               {
                   entrada = temp;
                   posAlterada = true;
               }
               else{
                   norte[indexN] = temp;
                   indexN++;
               }
           }
        }

         // atribuindo avioes do sul
         else if(entrada.equals("-2"))
         {
            boolean posAlterada = false;
            while(posAlterada == false)
            {
                String temp = sc.next();
                if(temp.startsWith("-") || temp.equals("0"))
                {
                    entrada = temp;
                    posAlterada = true;
                }
                else{
                    sul[indexS] = temp;
                    indexS++;
                }
            }
         }

         // atribuindo avioes do oeste
         else if(entrada.equals("-1"))
         {
            boolean posAlterada = false;
            while(posAlterada == false)
            {
                String temp = sc.next();
                if(temp.startsWith("-") || temp.equals("0"))
                {
                    entrada = temp;
                    posAlterada = true;
                }
                else{
                    oeste[indexO] = temp;
                    indexO++;
                }
            }
         }
       }
       System.out.println("LESTE = -4");
       for(int i = 0; i < indexL; i++)
       {
         System.out.println(leste[i]);
       }

       System.out.println("NORTE = -3");
       for(int i = 0; i < indexN; i++)
       {
         System.out.println(norte[i]);
       }

       System.out.println("SUL = -2");
       for(int i = 0; i < indexS; i++)
       {
         System.out.println(sul[i]);
       }

       System.out.println("OESTE = -1");
       for(int i = 0; i < indexO; i++)
       {
         System.out.println(oeste[i]);
       }
    }
}
