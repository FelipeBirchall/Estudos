package TreinamentoPratica2;
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
    String nome;
    Data nascimento;
    String email;
    String codigo;
    String[] materias;

    public Pessoa(){}


    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    
    public void setNascimento(Data nascimento) {
        this.nascimento = nascimento;
    }
    public Data getNascimento() {
        return nascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setMaterias(String[] m) {
        materias = new String[m.length];
        for(int i = 0; i < materias.length; i++)
        {
            materias[i] = m[i].trim();
        }
    }
    public void getMaterias() {
        System.out.print("( ");
        for(int i = 0; i < materias.length-1; i++)
        {
            System.out.print(materias[i] + ", ");
        }
        System.out.print(materias[materias.length-1] + " )");    
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
        int[] temp = new int[elementos.length];
        for(int i = 0; i < elementos.length; i++)
        {
            temp[i] = Integer.parseInt(elementos[i].trim());
        }
        Data d = new Data(temp[0], temp[1], temp[2]);
        setNascimento(d);

        setEmail(partes.get(2));
        setCodigo(partes.get(3).trim());

        String frase = partes.get(4).replace("[", "").replace("]", "");
        setMaterias(frase.split(","));
    }

    public void imprimir()
    {
        System.out.print(getNome() + " " + nascimento.getDia() + "/0" + nascimento.getMes() + "/" + nascimento.getAno() + " " + getEmail() + " " + getCodigo() + " ");
        getMaterias();
    }

}

public class Main3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Pessoa[] pessoa = new Pessoa[100];
        String entrada = sc.nextLine();
        int index = 0;
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

        sc.close();

    }
    
}
