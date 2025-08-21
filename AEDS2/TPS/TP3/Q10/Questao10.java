package Q10;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;



class Celula{
    Celula prox, ant;
    SHOW show;

    public Celula(){}

    public Celula(SHOW show)
    {
        this.show = show;
        this.prox = this.ant = null;
    }
}

class ListaDupla{
    Celula primeiro, ultimo;
    int comparacoes = 0;
    public ListaDupla()
    {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public int getComparacoes() {
        return comparacoes;
    }
    
    public void inserir(SHOW show)
    {
        ultimo.prox = new Celula(show);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public int tamanho()
    {
        int tam = 0;
        for(Celula i = primeiro.prox; i != null; i = i.prox){tam++;}
        return tam;
    }

    public void mostrar()
    {
        for(Celula i = primeiro.prox; i != null; i = i.prox)
        {
            i.show.imprimir();
        }
    }

    // QuickSort para ordenar por DATE_ADDED e TITLE como desempate
    public void quickSort() {
        quickSortRec(primeiro.prox, ultimo);
    }

    private void quickSortRec(Celula inicio, Celula fim) {
        if (inicio != null && fim != null && inicio != fim && inicio.ant != fim) {
            Celula pivo = particiona(inicio, fim);
            if (pivo != null && pivo.ant != null) {
                quickSortRec(inicio, pivo.ant);
            }
            if (pivo != null && pivo.prox != null) {
                quickSortRec(pivo.prox, fim);
            }
        }
    }

    private Celula particiona(Celula inicio, Celula fim) {
        SHOW pivo = fim.show;
        Celula i = inicio.ant;

        for (Celula j = inicio; j != fim; j = j.prox) {
            if (comparar(j.show, pivo) <= 0) {
                i = (i == null) ? inicio : i.prox;
                trocar(i, j);
            }
        }
        i = (i == null) ? inicio : i.prox;
        trocar(i, fim);
        return i;
    }

    private void trocar(Celula i, Celula j) {
        if (i != j) {
            SHOW temp = i.show;
            i.show = j.show;
            j.show = temp;
        }
    }

    private int comparar(SHOW s1, SHOW s2) {
        // Comparação por DATE_ADDED
        if (s1.getDATE_ADDED() == null && s2.getDATE_ADDED() == null) {
            comparacoes++;
            return s1.getTITLE().compareTo(s2.getTITLE());
        }
        if (s1.getDATE_ADDED() == null) {
            comparacoes++;
            return -1;
        }
        if (s2.getDATE_ADDED() == null) {
            comparacoes++;
            return 1;
        }
        int cmp = s1.getDATE_ADDED().compareTo(s2.getDATE_ADDED());
        // Se as datas forem iguais, usa TITLE como desempate
        if (cmp == 0) {
            comparacoes++;
            return s1.getTITLE().compareTo(s2.getTITLE());
        }
        return cmp;
    }
   
}


class SHOW {
    private String SHOW_ID;
    private String TYPE;
    private String TITLE;
    private String DIRECTOR[];
    private String[] CAST;
    private String COUNTRY;
    private Date DATE_ADDED;
    private int RELEASE_YEAR;
    private String RATING;
    private String DURATION;
    private String[] LISTED_IN;

    public SHOW() {
    }

    public SHOW(String SHOW_ID, String TYPE, String TITLE, String[] DIRECTOR, String[] CAST, String COUNTRY,
            Date DATE_ADDED, int RELEASE_YEAR, String RATING, String DURATION, String[] LISTED_IN) {

        setID(SHOW_ID);
        setTYPE(TYPE);
        setTITLE(TITLE);
        setDIRECTOR(DIRECTOR);
        setCAST(CAST);
        setCOUNTRY(COUNTRY);
        setDATE_ADDED(DATE_ADDED);
        setRELEASE_YEAR(RELEASE_YEAR);
        setRATING(RATING);
        setDURATION(DURATION);
        setLISTED_IN(LISTED_IN);
    }

    public SHOW clone() {
        SHOW clonado = new SHOW();

        clonado.setID(this.SHOW_ID);
        clonado.setTYPE(this.TYPE);
        clonado.setTITLE(this.TITLE);
        clonado.setDIRECTOR(this.DIRECTOR);
        clonado.setCAST(this.CAST);
        clonado.setCOUNTRY(this.COUNTRY);
        clonado.setRATING(this.RATING);
        clonado.setDATE_ADDED(this.DATE_ADDED);
        clonado.setRELEASE_YEAR(this.RELEASE_YEAR);
        clonado.setDURATION(this.DURATION);
        clonado.setLISTED_IN(this.LISTED_IN);

        return clonado;

    }

    // FUNÇÕES SET

    public void setID(String SHOW_ID) {
        this.SHOW_ID = SHOW_ID;
    }

    private void setTYPE(String TYPE) {
        if (TYPE == "") {
            TYPE = "NaN";
        }
        this.TYPE = TYPE;
    }

    private void setTITLE(String TITLE) {
        if (TITLE == "") {
            TITLE = "NaN";
        }
        this.TITLE = TITLE;
    }

    private void setDIRECTOR(String[] DIRECTOR) {
        if (DIRECTOR[0] == "") {
            DIRECTOR[0] = "NaN";
        }
        this.DIRECTOR = new String[DIRECTOR.length];
        for (int i = 0; i < DIRECTOR.length; i++) {
            this.DIRECTOR[i] = DIRECTOR[i].trim();
        }
    }

    private void setCAST(String[] CAST) {
        for (int i = 0; i < CAST.length; i++) {
            CAST[i] = CAST[i].trim();
        }
        ordenar(CAST);
        if (CAST[0] == "") {
            CAST[0] = "NaN";
        }
        this.CAST = new String[CAST.length];
        for (int i = 0; i < CAST.length; i++) {
            this.CAST[i] = CAST[i].trim();
        }
    }

    private void setCOUNTRY(String COUNTRY) {
        if (COUNTRY == "") {
            COUNTRY = "NaN";
        }
        this.COUNTRY = COUNTRY;
    }

    private void setDATE_ADDED(Date DATE_ADDED) {
        if (DATE_ADDED == null) {
            this.DATE_ADDED = null;
        } else {
            this.DATE_ADDED = DATE_ADDED;
        }

    }

    private void setRELEASE_YEAR(int RELEASE_YEAR) {
        this.RELEASE_YEAR = RELEASE_YEAR;
    }

    private void setRATING(String RATING) {
        if (RATING == "") {
            RATING = "NaN";
        }
        this.RATING = RATING;
    }

    private void setDURATION(String DURATION) {
        if (DURATION == "") {
            DURATION = "NaN";
        }
        this.DURATION = DURATION;
    }

    private void setLISTED_IN(String[] LISTED_IN) {
        for (int i = 0; i < LISTED_IN.length; i++) {
            LISTED_IN[i] = LISTED_IN[i].trim();
        }
        ordenar(LISTED_IN);
        if (LISTED_IN[0] == "") {
            LISTED_IN[0] = "NaN";
        }
        this.LISTED_IN = new String[LISTED_IN.length];
        for (int i = 0; i < LISTED_IN.length; i++) {
            this.LISTED_IN[i] = LISTED_IN[i].trim();
        }
    }

    // FUNÇÕES GET

    public String getID() {
        return SHOW_ID;
    }

    public String getTYPE() {
        return TYPE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void getDIRECTOR() {
        for (int i = 0; i < DIRECTOR.length - 1; i++) {
            System.out.print(DIRECTOR[i] + ", ");
        }
        System.out.print(DIRECTOR[DIRECTOR.length - 1] + " ## ");
    }

    public void getCAST() {
        System.out.print("[");
        for (int i = 0; i < CAST.length - 1; i++) {
            System.out.print(CAST[i] + ", ");
        }
        System.out.print(CAST[CAST.length - 1] + "] ## ");
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public Date getDATE_ADDED() {
        return DATE_ADDED;
    }

    public int getRELEASE_YEAR() {
        return RELEASE_YEAR;
    }

    public String getRATING() {
        return RATING;
    }

    public String getDURATION() {
        return DURATION;
    }

    public void getLISTED_IN() {
        System.out.print("[");
        for (int i = 0; i < LISTED_IN.length - 1; i++) {
            System.out.print(LISTED_IN[i] + ", ");
        }
        System.out.print(LISTED_IN[LISTED_IN.length - 1] + "] ##");
    }

    // OUTRAS FUNÇÕES

    public void Leitura(String entrada) {
        List<String> partes = new ArrayList<>();
        boolean dentroAspas = false;
        StringBuilder atual = new StringBuilder();

        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);

            if (c == '"') {
                dentroAspas = !dentroAspas; // alterna se está dentro de aspas
            } else if (c == ',' && !dentroAspas) {
                partes.add(atual.toString().trim());
                atual.setLength(0); // limpa o StringBuilder
            } else {
                atual.append(c); // adiciona o character no StringBuilder
            }
        }
        partes.add(atual.toString().trim());

        setID(partes.get(0));
        setTYPE(partes.get(1));
        setTITLE(partes.get(2));
        setDIRECTOR(partes.get(3).split(","));
        setCAST(partes.get(4).split(","));
        setCOUNTRY(partes.get(5));

        Date date_added = null;
        if (partes.get(6) != null && !partes.get(6).isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
                date_added = sdf.parse(partes.get(6));
            } catch (ParseException e) {
                System.out.println("Erro ao converter a data do " + getID());
                date_added = null;
            }
        }
        setDATE_ADDED(date_added);

        setRELEASE_YEAR(Integer.parseInt(partes.get(7)));
        setRATING(partes.get(8));
        setDURATION(partes.get(9));
        setLISTED_IN(partes.get(10).split(","));

    }

    private void ordenar(String[] Lista) {
        for (int i = 0; i < Lista.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < Lista.length; j++) {
                if (Lista[menor].compareTo(Lista[j]) > 0) {
                    menor = j;
                }
            }

            String temp = Lista[menor];
            Lista[menor] = Lista[i];
            Lista[i] = temp;
        }

    }

    public void imprimir() {
        System.out.print("=> " + getID() + " ## " + getTITLE() + " ## " + getTYPE() + " ## ");
        getDIRECTOR();
        getCAST();
        System.out.print(getCOUNTRY() + " ## ");

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        if (getDATE_ADDED() != null) {
            System.out.print(sdf.format(getDATE_ADDED()) + " ## ");
        } else {
            System.out.print("NaN ## ");
        }

        System.out.print(getRELEASE_YEAR() + " ## " + getRATING() + " ## " + getDURATION() + " ## ");
        getLISTED_IN();
        System.out.println();
    }

}

public class Questao10 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        long tempoInicial = System.currentTimeMillis();
        String entrada;
        ListaDupla lista_dupla = new ListaDupla();

        // atribuindo os valores digitados na lista
        try {
            while (!(entrada = sc.nextLine()).equals("FIM")) {
                BufferedReader br = new BufferedReader(new FileReader("/tmp/disneyplus.csv"));
                String linha = br.readLine(); // pula o cabeçalho
                boolean encontrado = false;
               

                linha = br.readLine(); // lê a primeira linha de dados
                while (linha != null && !encontrado) {
                    if (linha.startsWith(entrada + ",")) {
                        SHOW tmp = new SHOW(); //objeto show temporário utilizado para atribuir os dados para a lista
                        tmp.Leitura(linha);
                        lista_dupla.inserir(tmp);
                        encontrado = true;
                    } else {
                        linha = br.readLine(); // só continua lendo se ainda não encontrou
                    }
                }

                if (!encontrado) {
                    System.out.println("Show ID " + entrada + " não encontrado.");
                }

                br.close();
            }
        } catch (IOException e) {
            System.out.println("Erro ao acessar o arquivo: " + e.getMessage());
        }

        lista_dupla.quickSort();
        lista_dupla.mostrar();

        long tempoFinal = System.currentTimeMillis();
        long tempoExecucao = tempoFinal - tempoInicial;

        //Criando arquivo.txt
        try {
            java.io.PrintWriter arquivo = new java.io.PrintWriter("matricula_sequencial.txt", "UTF-8");
            arquivo.printf("844448\t%d \t%d \n", tempoExecucao, lista_dupla.getComparacoes());
            arquivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
