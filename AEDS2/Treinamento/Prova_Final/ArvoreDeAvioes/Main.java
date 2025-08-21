package ArvoreDeAvioes;
import java.util.Scanner;


class Aviao{
    String codigo;
    String cidade_origem;
    String cidade_destino;
    String data;
    String horario;

    public Aviao(){}

    //SETS
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setCidade_origem(String cidade_origem) {
        this.cidade_origem = cidade_origem;
    }
    public void setCidade_destino(String cidade_destino) {
        this.cidade_destino = cidade_destino;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }

    //GETS
    public String getCodigo() {
        return codigo;
    }
    public String getCidade_origem() {
        return cidade_origem;
    }
    public String getCidade_destino() {
        return cidade_destino;
    }
    public String getData() {
        return data;
    }
    public String getHorario() {
        return horario;
    }

    //Outras funcoes
    public void leitura(String dados){
        String[] partes = dados.split(",");
        setCodigo(partes[0]); 
        setCidade_origem(partes[1]); 
        setCidade_destino(partes[2]);
        setData(partes[3]);
        setHorario(partes[4]);
    }

    public void mostrar(){
        System.out.println(codigo + " " + cidade_origem + " " + cidade_destino + " " + data + " " + horario);
    }
}

class No{
    Aviao aviao;
    No esq, dir;

    public No(){
        this.esq = this.dir = null;
    }
    public No(Aviao a){
        this.aviao = a;
        this.esq = this.dir = null;    
    }
}

class Arvore{
    No raiz;

    public Arvore(){
        this.raiz = null;
    }

    public void inserir(Aviao a){
        raiz = inserir(a, raiz);
    }
    private No inserir(Aviao a, No i){
        if(i == null){
            i = new No(a);
        }
        else if(a.getCodigo().compareTo(i.aviao.getCodigo()) < 0){
            i.esq = inserir(a, i.esq);
        }
        else if(a.getCodigo().compareTo(i.aviao.getCodigo()) > 0){
            i.dir = inserir(a, i.dir);
        }
        return i;
    }

    public void caminharPos(){
        caminharPos(raiz);
    }
   void caminharPos(No i){
        if(i != null){
            caminharPos(i.esq);
            caminharPos(i.dir);
            i.aviao.mostrar();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arvore arvore = new Arvore();
        String entrada = sc.nextLine();
        while(!entrada.equals("FIM")){
            Aviao tmp = new Aviao();
            tmp.leitura(entrada);
            arvore.inserir(tmp);
            entrada = sc.nextLine();
        }
        arvore.caminharPos();
        sc.close();
    }

    
}
