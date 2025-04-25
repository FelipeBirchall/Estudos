import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        int[] vetor = new int[n];

        for(int i = 0; i < n; i++)
        {
            vetor[i] = sc.nextInt();
        }
        ordenar(vetor, n);
        for(int i = 0; i < n; i++)
        {
            System.out.print(vetor[i]+ " ");
        }
        sc.close();
    }

    static void ordenar(int[] vetor, int n)
    {
        int[] ordenado = new int[n];
        for(int i = 0; i < n; i++)
        {
            ordenado[i] = vetor[i];
        }
        //construcao
        for(int tam = 1; tam <= n; tam++)
        {
            construir(ordenado, tam);
        }
        
        //reconstrucao
        int tam = n;
        while(tam > 1)
        {
            int temp = ordenado[0];
            ordenado[0] = ordenado[tam-1];
            ordenado[tam-1] = temp;
            tam--;
            reconstruir(ordenado, tam);
        }
        for(int  i = 0; i < n; i++)
        {
            vetor[i] = ordenado[i];
        }
    }

    static void construir(int[] ordenado , int tam)
    {
        for(int i = tam; i >= 0; i/=2)
        {
            if(ordenado[i] > ordenado[(i-1)/2])
            {
                int temp = ordenado[i];
                ordenado[i] = ordenado[(i-1)/2];
                ordenado[(i-1)/2] = temp;
            }
        }

    }

    static void reconstruir(int[] ordenado, int tam)
    {
        int i = 0;
        while(possuiFilho(i,tam) == true)
        {
            int filho = maiorFilho(ordenado, i, tam);
            if(ordenado[i] < ordenado[filho])
            {
                int temp = ordenado[i];
                ordenado[i] = ordenado[filho];
                ordenado[filho] = temp;
                i = filho;
            }
            else{
                i = tam;
            }

        }
    }

    static boolean possuiFilho(int i, int tam)
    {
        boolean possui = false;
        if((2*i+1) < tam){
            possui = true;
        }
        return possui;
    }

    static int maiorFilho(int[] ordenado,int i, int tam)
    {
        int filhoEsq = 2*i+1;
        int filhoDir = 2*i+2;
        int maior = filhoEsq;
        if(filhoDir < tam)
        {
            if(ordenado[filhoDir] > ordenado[filhoEsq])
            {
                maior = filhoDir;
            }
        }

        return maior;

    }

    
}
