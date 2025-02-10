package Area_Testes;
import java.util.Scanner;

public class Main{

    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        int a = 10;
        int b = 10;
        int c = 10;
        int d = 10;
        int e = 10;
        int f = 10;
        int i = 1;
        int n = 32;
        while(i < n)
        {
            i *= 2;
            a--; b--; c--; d--; e--; f--;
        }

        System.out.println(a);
        

        scanner.close();  
        
    }
}


// Nº OPERAÇÕES LOGARITMICAS //

 /* 
        int n = scanner.nextInt();
        int a = 1;
        int mult = 0;
        for(int i = n; i > 0 ; i/= 2)
        {
            a *= 2;
            mult++;
        }
        System.out.println(a);
        System.out.println("Multiplicações executadas: "+mult);
*/




// 3n + 2n² //
/*
        int a = 1000;
        int b = 1000;
        int c = 1000;
        int i = 0;
        int n = 10;
        while(i < n)
        {
           i++;
           a--; 
           b--; 
           c--;
          
        }
        for(i = 0 ; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                 a--;
                 b--;
            }
        }
        System.out.println(a);
        
        System.out.println(b);

        System.out.println(c);
        
 */


// 5n + 4n³ // 
/*
        int a = 1000;
        int b = 1000;
        int c = 1000;
        int d = 1000;
        int e = 1000;
        int i = 0;
        int n = 10;
        while(i < n)
        {
           i++;
           a--; 
           b--; 
           c--;
           d--;
           e--;
        }
        for(i = 0 ; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
               for(int k = 0; k < n; k++)
               {
                 a--;
                 b--;
                 c--;
                 d--;
               }
            }
        }
*/


// lg(n) + n //
/*
        int a = 100;
        int i = 1;
        int n = 32;
        while(i < n)
        {
           i*=2;
           a--; 
        }
        for(i = 0; i < n; i++)
        {
                 a--;        
        }
        System.out.println(a); 
*/


// 2n³ + 5 //
/*
        int a = 1000;
        int b = 1000;
        int c = 1000;
        int d = 1000;
        int e = 1000;
        int i = 0;
        int n = 10;
        for(i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                for(int k = 0; k < n+5 ; k++, a-- ,b--);
            }     
        }
        a--; b--; c--; d--; e--;
 */


 // 2n^4 + 2n² + n/2 //
 /*
        int a = 1000;
        int b = 1000;
        int e = 1000;
        
        int i = 0;
        int n = 4;
        for(i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                a--;
                b--;
                for(int k = 0; k < n+5 ; k++)
                {
                    for(int l = 0; l < n; l++ , a-- , b--);
                }
            }     
        }

        if(i % 2 == 0) e--;

*/


// lg(n) + 5lg(n) // 
/* 
  int a = 10;
        int b = 10;
        int c = 10;
        int d = 10;
        int e = 10;
        int f = 10;
        int i = 1;
        int n = 32;
        while(i < n)
        {
            i *= 2;
            a--; b--; c--; d--; e--; f--;
        }
*/