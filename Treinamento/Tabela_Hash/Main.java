import java.util.Scanner;

class Hashtable{
    Pessoa[] pessoas;
    int index;

    public Hashtable()
    {
        pessoas = new Pessoa[366];
        int index = 0;
    }

    public void add(int pos, Pessoa p)
    {
        pessoas[pos] = p;
        index++;
    }

    public void remove(int pos){
        pessoas[pos] = null;
        index--;
    }

    public boolean busca(int pos)
    {
        boolean existe = false;
        if(pessoas[pos] != null){existe = true;}
        return existe;
    }
}


class Data{
    int dia, mes, ano;

    public Data(int dia, int mes, int ano)
    {
        setDia(dia); setMes(mes); setAno(ano);
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }
    public int getMes() {
        return mes;
    }
    public int getAno() {
        return ano;
    }
}

class Pessoa{
    String nome;
    Data dataNascimento;

    public Pessoa(){}

    public Pessoa(String nome, Data dataNascimento){
        setNome(nome); setDataNascimento(dataNascimento);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }
    public Data getDataNascimento() {
        return dataNascimento;
    }
    
    public void mostrar()
    {
        System.out.println("Nome: " + getNome());
        System.out.println("Nascimento: " + dataNascimento.getDia() + "/" + dataNascimento.getMes() + "/" + dataNascimento.getAno());
    }

    public int hash()
    {
        return hash(0, 0);
    }

    public int hash(int dia, int mes)
    {
        mes = dataNascimento.getMes();
        dia = dataNascimento.getDia();
        int resp;
        if(mes == 1){ resp = dia -1;}
        else if(mes == 2){resp = 31 + dia -1;}
        else if(mes == 3){resp = 60 + dia -1;}
        else if(mes == 4){resp = 91 + dia -1;}
        else if(mes == 5){resp = 121 + dia -1;}
        else if(mes == 6){resp = 152 + dia -1;}
        else if(mes == 7){resp = 182 + dia -1;}
        else if(mes == 8){resp = 213 + dia -1;}
        else if(mes == 9){resp = 244 + dia -1;}
        else if(mes == 10){resp = 274 + dia -1;}
        else if(mes == 11){resp = 305 + dia -1;}
        else{resp = 335 + dia -1;}

        return resp;
    }
}



public class Main{

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        Hashtable ht = new Hashtable();

        int opcao = 0;
        while(opcao != 5)
        {
            Pessoa pessoa = new Pessoa();
            System.out.println("\nInserir pessoa - 1");
            System.out.println("Remover pessoa - 2");
            System.out.println("Listar pessoas - 3");
            System.out.println("Pesquisar pessoa - 4");
            System.out.println("Sair - 5");
            System.out.print("Opcao: ");
            opcao = sc.nextInt();
            int dia;
            int mes;
            
            switch (opcao) {
                case 1:
                    sc.nextLine();
                    String nome = sc.nextLine();
                    pessoa.setNome(nome);
                    dia = sc.nextInt();
                    mes = sc.nextInt();
                    int ano = sc.nextInt();
                    Data data = new Data(dia, mes, ano);
                    pessoa.setDataNascimento(data);
                    ht.add(pessoa.hash(), pessoa);
                    break;
        
                case 2:
                    dia = sc.nextInt();
                    mes = sc.nextInt();
                    Pessoa temp = new Pessoa();
                    temp.setDataNascimento(new Data(dia, mes, 2000));
                    ht.remove(temp.hash());
                    break;

                case 3:
                    System.out.println("\nMostrar Hashtable:");
                    for (int i = 0; i < ht.pessoas.length; i++) {
                        if (ht.pessoas[i] != null) {
                            ht.pessoas[i].mostrar();
                        }
                    }
                    System.out.println();
                    break;
                
                case 4:
                    dia = sc.nextInt();
                    mes = sc.nextInt();
                    Pessoa temp2 = new Pessoa();
                    temp2.setDataNascimento(new Data(dia, mes, 2000));
                    boolean resp = ht.busca(temp2.hash());
                    if(resp == true){System.out.println("EXISTE!");}
                    else{System.out.println("NÃO EXISTE!");}
                    break;

                case 5:
                    System.out.println("\nFINALIZADO");
                    break;
            }
        }
        sc.close();
    }

}