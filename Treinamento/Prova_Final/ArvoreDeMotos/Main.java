package ArvoreDeMotos;
import java.util.Scanner;

class Moto{
    private String placa;
    private String marca;
    private String tipo;
    private String dono;

    public Moto(){}

    //SETS
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setDono(String dono) {
        this.dono = dono;
    }

    //GETS
    public String getPlaca() {
        return placa;
    }
    public String getMarca() {
        return marca;
    }
    public String getTipo() {
        return tipo;
    }
    public String getDono() {
        return dono;
    }
    
    public void leitura(String dados){
        String partes[] = dados.split(",");
        setPlaca(partes[0]);
        setMarca(partes[1]);
        setTipo(partes[2]);
        setDono(partes[3]);   
    }

    public void mostrar(){
        System.out.println(placa + " " + marca + " " + tipo + " " + dono);
    }
}

class No{
    Moto moto;
    No esq, dir;

    public No(){
        this.esq = this.dir = null;
    }
    public No(Moto m){
        this.moto = m;
        this.esq = this.dir = null;
    }
}

class Arvore{
    private No raiz;

    public Arvore(){
        this.raiz = null;
    }

    public void inserir(Moto m){
        raiz = inserir(m, raiz);
    }
    private No inserir(Moto m, No i){
        if(i == null){
            i = new No(m);
        }
        else if(m.getPlaca().compareTo(i.moto.getPlaca()) < 0){
            i.esq = inserir(m, i.esq);
        }
        else if(m.getPlaca().compareTo(i.moto.getPlaca()) > 0){
            i.dir = inserir(m, i.dir);
        }
        return i;
    }

    public void caminharPre(){
        caminharPre(raiz);
    }
    private void caminharPre(No i){
        if(i != null){
            i.moto.mostrar();
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arvore arvore = new Arvore();
        String entrada = sc.nextLine();
        while(!entrada.equals("FIM")){
            Moto tmp = new Moto();
            tmp.leitura(entrada);
            arvore.inserir(tmp);
            entrada = sc.nextLine();
        }
        arvore.caminharPre();
    }
}
