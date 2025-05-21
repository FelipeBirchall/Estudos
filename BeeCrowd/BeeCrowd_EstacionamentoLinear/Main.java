package BeeCrowd_EstacionamentoLinear;

import java.util.Scanner;

class Carro {
    int C, S;

    public Carro() {
    }

    public Carro(int C, int S) {
        setC(C);
        setS(S);
    }

    public void setC(int C) {
        this.C = C;
    }

    public void setS(int S) {
        this.S = S;
    }

    public int getC() {
        return C;
    }

    public int getS() {
        return S;
    }
}

class Pilha {
    int tam;
    int index = 0;
    Carro[] carro;

    public Pilha(int tam) {
        this.tam = tam;
        carro = new Carro[tam];
    }

    public void addCar(Carro c) {
        carro[index] = c;
        index++;
    }

    public void mostrar() {
        System.out.println("Carros estacionados: ");
        for (int i = 0; i < index; i++) {
            System.out.println(carro[i].getC() + " " + carro[i].getS());
        }
    }

    public void validar(Fila fila) {
        boolean ehValido = true;
        for (int i = 1; i < tam; i++) {
            if (carro[i - 1].getS() < carro[i].getS()
                    || (carro[i - 1].getS() < carro[i].getS() && carro[i - 1].getS() > carro[i].getC())) {
                ehValido = false;
                i = tam;
            }
            if (ehValido == true) {
                System.out.println("SIM");
            } else {
                System.out.println("NÃO");
            }

        }
    }
}

    class Fila {
        int tam;
        Carro[] carro;
        int index = 0;

        public Fila(int tam) {
            this.tam = tam;
            carro = new Carro[tam];
        }

        public void novoCarro(Carro c) {
            carro[index] = c;
            index++;
        }

        public void mostrar() {
            System.out.println("Carros na fila: ");
            for (int i = 0; i < index; i++) {
                System.out.println(carro[i].getC() + " " + carro[i].getS());
            }
        }
    }

public class Main {
    
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        Carro[] carro = new Carro[n];
        Pilha pilha = new Pilha(k);
        Fila fila = new Fila(n-k);
        int cont = 0;

        for(int i = 0; i < n; i++)
        {
            int C = sc.nextInt();
            int S = sc.nextInt();
            carro[i] = new Carro(C , S);
            if(cont < k){
                pilha.addCar(carro[i]);
            }
            else{
                fila.novoCarro(carro[i]);
            }
            cont++;
        }

        pilha.mostrar();

        fila.mostrar();

        pilha.validar(fila);

    }  
}
