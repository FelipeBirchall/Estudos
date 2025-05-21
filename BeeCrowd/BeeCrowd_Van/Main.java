package BeeCrowd_Van;

import java.util.Scanner;


class Aluno{
    String nome;
    char regiao;
    int custo;

    public Aluno(String nome, char regiao , int custo)
    {
        setNome(nome);
        setRegiao(regiao);
        setCusto(custo);
    }

    public Aluno(){}

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setRegiao(char regiao)
    {
        this.regiao = regiao;
    }

    public void setCusto(int custo)
    {
        this.custo = custo;
    }

    public String getNome()
    {
        return nome;
    }

    public char getRegiao()
    {
        return regiao;
    }

    public int getCusto()
    {
        return custo;
    }

}

class Van{
    Aluno[] aluno;
    int index = 0;

    public Van(int num)
    {
       aluno = new Aluno[num];
    }

    public void getAluno(Aluno a)
    {
        aluno[index] = new Aluno();
        aluno[index] = a;
        index++;
    }

    public void ordenarAlunos()
    {
        for(int i = 0; i < aluno.length-1; i++)
        {
            int menor = i;
            for(int j = i+1; j < aluno.length; j++)
            {
                if(aluno[menor].getCusto() > aluno[j].getCusto() || (aluno[menor].getCusto() == aluno[j].getCusto() && aluno[menor].getRegiao() > aluno[j].getRegiao()) || (aluno[menor].getCusto() == aluno[j].getCusto() && aluno[menor].getRegiao() == aluno[j].getRegiao() && aluno[menor].getNome().compareToIgnoreCase(aluno[j].getNome()) > 0))
                {
                    menor = j;
                }
            }
            Aluno temp = aluno[i];
            aluno[i] = aluno[menor];
            aluno[menor] = temp;
        }
    }

    public void mostrarAluno()
    {
        System.out.println();
        for(int i = 0; i < index; i++)
        {
            System.out.println(aluno[i].getNome());
        } 
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        Van van = new Van(n);
        Aluno[] aluno = new Aluno[n];

        for(int i = 0; i < n; i++)
        {
            String nome = sc.next();
            char regiao = sc.next().charAt(0);
            int custo = sc.nextInt();
            aluno[i] = new Aluno(nome, regiao, custo);
            van.getAluno(aluno[i]);
        }

        van.ordenarAlunos();

        van.mostrarAluno();
    }
    
}
