package TreinamentoPratica2;
import java.util.*;

class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {setDia(dia); setMes(mes); setAno(ano);}

    public void setDia(int dia) {this.dia = dia;}
    public int getDia() {return dia;}

    public void setMes(int mes) {this.mes = mes;}
    public int getMes() {return mes;}

    public void setAno(int ano) {this.ano = ano;}
    public int getAno() {return ano;}
}

class Jogador {
    private String nome;
    private String foto;
    private Data nascimento;
    private int id;
    private int[] times;

    public Jogador() {}

    public Jogador(String nome, String foto, Data nascimento, int id, int[] times) {
        setNome(nome); setFoto(foto); setData(nascimento); setId(id); setTimes(times);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setData(Data nascimento) {
        this.nascimento = nascimento;
    }

    public Data getData() {
        return nascimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTimes(int[] t) {
        times = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            times[i] = t[i];
        }
    }

    public void getTimes() {
        
        System.out.print("(");

        for(int i = 0; i < times.length-1; i++)
        {
            System.out.print(times[i] + ", ");
        }
        System.out.println(times[times.length-1]+")");
    }

    //LEITURA
    public void leitura(String entrada) {
        List<String> partes = new ArrayList<>();
        boolean dentroAspas = false;
        StringBuilder atual = new StringBuilder();
        int inicio = 0;

        // pular o codigo no inicio
        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);
            if (c == ',') {
                inicio = i + 1;
                i = entrada.length();
            }
        }

        for (int i = inicio; i < entrada.length(); i++) {
            char c = entrada.charAt(i);
            if (c == '"') {
                dentroAspas = !dentroAspas;
            } else if (c == ',' && !dentroAspas) {
                partes.add(atual.toString().trim());
                atual.setLength(0);
            } else {
                atual.append(c);
            }
        }
        partes.add(atual.toString().trim());

        setNome(partes.get(0));
        setFoto(partes.get(1));

        String[] elementos = partes.get(2).split("/");
        int[] tmp = new int[elementos.length];
        for (int i = 0; i < elementos.length; i++) {
            tmp[i] = Integer.parseInt(elementos[i].trim());
        }
        Data d = new Data(tmp[0], tmp[1], tmp[2]);
        setData(d);

        setId(Integer.parseInt(partes.get(4)));

        String temp = partes.get(5).replace("[", "").replace("]", "");
        String[] valores = temp.split(",");
        int[] times = new int[valores.length];
        for (int i = 0; i < valores.length; i++) {
            times[i] = Integer.parseInt(valores[i].trim());
        }
        setTimes(times);
    }

    public void imprimir()
    {
        System.out.print(getId()+" "+getNome()+" "+nascimento.getDia()+"/0"+ nascimento.getMes()+"/"+nascimento.getAno()+" "+getFoto()+" ");
        getTimes();
    }

}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Jogador[] jogador = new Jogador[100];
        int index = 0;
        String entrada = sc.nextLine();
        while (!entrada.equals("FIM")) {
            jogador[index] = new Jogador();
            jogador[index].leitura(entrada);
            index++;
            entrada = sc.nextLine();
        }
        for(int i = 0; i < index;i++)
        {
            jogador[i].imprimir();
        }

        sc.close();

    }
}
