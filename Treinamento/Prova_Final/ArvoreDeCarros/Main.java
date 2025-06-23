package ArvoreDeCarros;
import java.util.Scanner;

class Carro{
    String placa;
    String modelo;
    String tipo;
    String chassi;

    public Carro(){}

    //SETS
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    //GETS
    public String getPlaca() {
        return placa;
    }
    public String getModelo() {
        return modelo;
    }
    public String getTipo() {
        return tipo;
    }
    public String getChassi() {
        return chassi;
    }

    public void leitura(String dados){
        String[] partes = dados.split(",");
        setPlaca(partes[0]); setModelo(partes[1]); setTipo(partes[2]); setChassi(partes[3]);
    }

    public void mostrar(){
        System.out.println(placa + " " + modelo + " " + tipo + " " + chassi);

    }
}

class No{
    Carro carro;
    No esq, dir;

    public No(){
        this.esq = this.dir = null;
    }

    public No(Carro c){
        this.carro = c;
        this.esq = this.dir = null;
    }
}

class Arvore{
    No raiz;

    public Arvore(){
        this.raiz = null;
    }

    public void inserir(Carro c){
        raiz = inserir(c,raiz);
    }
    private No inserir(Carro c, No i){
        if(i == null){
            i = new No(c);
        }
        else if(c.getPlaca().compareTo(i.carro.getPlaca()) < 0){
            i.esq = inserir(c, i.esq);
        }
        else if(c.getPlaca().compareTo(i.carro.getPlaca()) > 0) {
            i.dir = inserir(c, i.dir);
        }
        return i;
    }

    public void caminharCentral(){
        caminharCentral(raiz);
    }

    private void caminharCentral(No i){
        if(i != null){
            caminharCentral(i.esq);
            i.carro.mostrar();
            caminharCentral(i.dir);
        }
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    
        Arvore arvore = new Arvore();
        String entrada = sc.nextLine();
        while(!entrada.equals("FIM")){
            Carro tmp = new Carro();
            tmp.leitura(entrada);
            arvore.inserir(tmp);
            entrada = sc.nextLine();
        }
        arvore.caminharCentral();

        sc.close();
    }
}