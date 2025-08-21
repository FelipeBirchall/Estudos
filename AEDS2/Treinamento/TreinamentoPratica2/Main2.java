package TreinamentoPratica2;
import java.util.ArrayList;
import java.util.*;


class Data{
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia , int mes, int ano)
    {
        setDia(dia); setMes(mes); setAno(ano);
    }

    public void setDia(int dia){this.dia = dia;}
    public int getDia(){return dia;}

    public void setMes(int mes){this.mes = mes;}
    public int getMes(){return mes;}

    public void setAno(int ano){this.ano = ano;}
    public int getAno(){return ano;}
}

class Pessoa{
    private String nome;
    private Data nascimento;
    private String cpf;
    private String[] responsaveis;

    public Pessoa(){}

    public void setNome(String nome){this.nome = nome;}
    public String getNome(){return nome;}

    public void setData(Data nascimento){this.nascimento = nascimento;}
    public Data getData(){return nascimento;}

    public void setCpf(String cpf){this.cpf = cpf;}
    public String getCpf(){return cpf;}

    public void setResponsaveis(String[] r){
        responsaveis = new String[r.length];
        for(int i = 0; i < r.length; i++)
        {
            responsaveis[i] = r[i].trim();
        }
    }
    public void getResponsaveis()
    {
        System.out.print("(");
        for(int i = 0; i < responsaveis.length-1; i++)
        {
            System.out.print(responsaveis[i] + ", ");
        }
        System.out.println(responsaveis[responsaveis.length-1]+ ")");
    }

    public void leitura(String entrada)
    {
        List<String> partes = new ArrayList<>();
        StringBuilder atual = new StringBuilder();
        boolean dentroAspas = false;
        for(int i = 0; i < entrada.length(); i++)
        {
            char c = entrada.charAt(i);
            if(c == '"')
            {
                dentroAspas = !dentroAspas;
            }
            else if(c == ',' && !dentroAspas){
                partes.add(atual.toString().trim());
                atual.setLength(0);
            }
            else{
                atual.append(c);
            }
        }
        partes.add(atual.toString().trim());

        setNome(partes.get(0));

        String[] elementos = partes.get(1).split("/");
        int[] tmp = new int[elementos.length];
        for(int i = 0; i < tmp.length; i++){
            tmp[i] = Integer.parseInt(elementos[i].trim());
        }
        Data d = new Data(tmp[0], tmp[1], tmp[2]);
        setData(d);

        setCpf(partes.get(2).trim());

        String frase = partes.get(3).replace("[", "").replace("]", "");
        setResponsaveis(frase.split(","));
    }

    public void imprimir()
    {
        System.out.print(getNome() + " " + nascimento.getDia() + "/0" + nascimento.getMes() + "/" + nascimento.getAno() + " " + getCpf() + " ");
        getResponsaveis();
    }


}


public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Pessoa[] pessoa = new Pessoa[100];
        int index = 0;
        String entrada = sc.nextLine();

        while(!entrada.equals("FIM"))
        {
            pessoa[index] = new Pessoa();
            pessoa[index].leitura(entrada);
            index++;
            entrada = sc.nextLine();
        }
        for(int i = 0; i < index; i++)
        {
            pessoa[i].imprimir();
        }
    }
}
