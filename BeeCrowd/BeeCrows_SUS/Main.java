package BeeCrows_SUS;
import java.util.Scanner;

class Paciente{
    int H;
    int M;
    int C;

    public Paciente(int H, int M , int C)
    {
        setHora(H); setMinuto(M); setCritico(C);
    }

    public void setHora(int H)
    {
        this.H = H;
    }

    public void setMinuto(int M)
    {
        this.M = M;
    }

    public void setCritico(int C)
    {
        this.C = C;
    }

    public int getHora()
    {
        return H;
    }

    public int getMinuto()
    {
        return M;
    }

    public int getCritico()
    {
        return C;
    }

    public int HorarioEmMin()
    {
        return (H*60) + M;
    }

}


public class Main{


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Paciente[] paciente = new Paciente[1000];
        int tam = -1;
        int tamAnt = 0;

        while(tam != 0)
        {
           tam = sc.nextInt();
           for(int i = tamAnt ; i < tam + tamAnt; i++)
           {
              int H = sc.nextInt();
              int M = sc.nextInt();
              int C = sc.nextInt();
              paciente[i] = new Paciente(H, M, C);
           }
           System.out.println(numCriticos(paciente, tam, tamAnt));
           tamAnt = tam;
        }

        sc.close();
        
    }

    static int numCriticos(Paciente[] paciente, int tam , int tamAnt)
    {
        int tempoAtual = 420;
        int cont = 0;

        for(int i = tamAnt; i < tam + tamAnt; i++)
        {
            int chegada = paciente[i].HorarioEmMin();
            int inicioAtendimento = Math.max(tempoAtual ,chegada);
            int espera = inicioAtendimento - chegada;
            if(espera > paciente[i].getCritico())
            {
                cont++;
            }
            
            tempoAtual = inicioAtendimento + 30;
        }
        

        return cont;

    }
}