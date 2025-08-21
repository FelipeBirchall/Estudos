package Q3;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class No {
    SHOW show;
    No esq, dir;
    int altura;

    public No(SHOW s) {
        this.show = s;
        this.esq = this.dir = null;
        this.altura = 1;
    }
}

class Arvore {
    No raiz;
    int comparacoes = 0;

    public Arvore() {
        this.raiz = null;
    }

    public int getComparacoes() {
        return comparacoes;
    }

    private int altura(No no) {
        return (no == null) ? 0 : no.altura;
    }

    private int fatorBalanceamento(No no) {
        if (no == null) return 0;
        return altura(no.esq) - altura(no.dir);
    }

    private No rotacaoDireita(No y) {
        No x = y.esq;
        No T2 = x.dir;

        x.dir = y;
        y.esq = T2;

        y.altura = Math.max(altura(y.esq), altura(y.dir)) + 1;
        x.altura = Math.max(altura(x.esq), altura(x.dir)) + 1;

        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.dir;
        No T2 = y.esq;

        y.esq = x;
        x.dir = T2;

        x.altura = Math.max(altura(x.esq), altura(x.dir)) + 1;
        y.altura = Math.max(altura(y.esq), altura(y.dir)) + 1;

        return y;
    }

    public void inserir(SHOW s) {
        raiz = inserir(s, raiz);
    }

    private No inserir(SHOW s, No no) {
        if (no == null) {
            return new No(s);
        }

        if (s.getTITLE().compareTo(no.show.getTITLE()) < 0) {
            no.esq = inserir(s, no.esq);
        } else if (s.getTITLE().compareTo(no.show.getTITLE()) > 0) {
            no.dir = inserir(s, no.dir);
        } else {
            return no;
        }

        // Atualizar altura do nó atual
        no.altura = 1 + Math.max(altura(no.esq), altura(no.dir));

        // Verificar fator de balanceamento
        int balanceamento = fatorBalanceamento(no);

        // Rotação Esquerda-Esquerda
        if (balanceamento > 1 && s.getTITLE().compareTo(no.esq.show.getTITLE()) < 0) {
            return rotacaoDireita(no);
        }

        // Rotação Direita-Direita
        if (balanceamento < -1 && s.getTITLE().compareTo(no.dir.show.getTITLE()) > 0) {
            return rotacaoEsquerda(no);
        }

        // Rotação Esquerda-Direita
        if (balanceamento > 1 && s.getTITLE().compareTo(no.esq.show.getTITLE()) > 0) {
            no.esq = rotacaoEsquerda(no.esq);
            return rotacaoDireita(no);
        }

        // Rotação Direita-Esquerda
        if (balanceamento < -1 && s.getTITLE().compareTo(no.dir.show.getTITLE()) < 0) {
            no.dir = rotacaoDireita(no.dir);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public boolean pesquisar(String s) {
        System.out.print("raiz");
        return pesquisar(s, raiz);
    }

    private boolean pesquisar(String s, No i) {
        boolean resp;
        if (i == null) {
            comparacoes++;
            resp = false;
        } else if (s.equals(i.show.getTITLE())) {
            comparacoes++;
            resp = true;
        } else if (s.compareTo(i.show.getTITLE()) < 0) {
            System.out.print(" esq");
            comparacoes++;
            resp = pesquisar(s, i.esq);
        } else {
            System.out.print(" dir");
            comparacoes++;
            resp = pesquisar(s, i.dir);
        }
        return resp;
    }
}

class SHOW {
    private String SHOW_ID;
    private String TYPE;
    private String TITLE;
    private String[] DIRECTOR;
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
        this.DATE_ADDED = DATE_ADDED;
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

    public void Leitura(String entrada) {
        List<String> partes = new ArrayList<>();
        boolean dentroAspas = false;
        StringBuilder atual = new StringBuilder();

        for (int i = 0; i < entrada.length(); i++) {
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

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String entrada;
        Arvore arvore = new Arvore();
        long tempoInicial = System.currentTimeMillis();

        try {
            while (!(entrada = sc.nextLine()).equals("FIM")) {
                BufferedReader br = new BufferedReader(new FileReader("tmp/disneyplus.csv"));
                String linha = br.readLine();
                boolean encontrado = false;

                linha = br.readLine();
                while (linha != null && !encontrado) {
                    if (linha.startsWith(entrada + ",")) {
                        SHOW tmp = new SHOW();
                        tmp.Leitura(linha);
                        arvore.inserir(tmp);
                        encontrado = true;
                    } else {
                        linha = br.readLine();
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

        String titulo = sc.nextLine();
        while (!titulo.equals("FIM")) {
            if (arvore.pesquisar(titulo)) {
                System.out.println(" SIM");
            } else {
                System.out.println(" NAO");
            }
            titulo = sc.nextLine();
        }

        long tempoFinal = System.currentTimeMillis();
        long tempoExecucao = tempoFinal - tempoInicial;

        try {
            PrintWriter arquivo = new PrintWriter("matricula_avl.txt", "UTF-8");
            arquivo.printf("844448\t%d\t%d\n", tempoExecucao, arvore.getComparacoes());
            arquivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}