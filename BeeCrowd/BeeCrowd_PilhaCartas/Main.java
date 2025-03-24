package BeeCrowd_PilhaCartas;
import java.util.Scanner;


class Pilha{
    int[] cartas;
    int[] cartasRemovidas;
    int n;
    int t;

    public Pilha(int tamanho)
    {
        cartas = new int[tamanho];
        cartasRemovidas = new int[tamanho-1];
        n = 0;
        t = tamanho;
    }
    public void inserir()
    {
        while(n < cartas.length)
        {
            cartas[n] = t - n;
            n++;
        }
        
    }
    public void deletar()
    {
        int index = 0;    
        while(n > 1)
        {
            cartasRemovidas[index] = cartas[n-1];
            index++;

            int temp = cartas[n-2];
            n--;
            for(int j = (n-1); j >= 0; j--)
            {
                cartas[j+1] = cartas[j];
            }
            cartas[0] = temp;
        }
       
    }
    public void cartaRestante()
    {
        System.out.println();
        System.out.println("Remaining card: " + cartas[0]);
    }
    public void mostrarRemovidas()
    {
        System.out.print("Discarded cards: ");
        for(int i = 0; i < cartasRemovidas.length; i++)
        {
            if((i + 1) - cartasRemovidas.length != 0)
            {
                System.out.print(cartasRemovidas[i] +", ");
            }
            else{
                System.out.print(cartasRemovidas[i]);
            }
        }
    }

    
}

public class Main{


    static public void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Pilha[] pilhas = new Pilha[50];
        int cont = 0;
        while(n != 0)
        {
            pilhas[cont] = new Pilha(n);
            pilhas[cont].inserir();
            pilhas[cont].deletar();
            cont++;
            n = sc.nextInt();
        }
        for(int i = 0; i < cont; i++)
        {
            pilhas[i].mostrarRemovidas();
            pilhas[i].cartaRestante();

        }
        sc.close();
    }
}