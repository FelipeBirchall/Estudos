import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;


class NoArvoreSecundaria {
    String chave;
    SHOW show;
    NoArvoreSecundaria esq, dir;
    public NoArvoreSecundaria(SHOW show) {
        this.chave = show.getTITLE();
        this.show = show;
        this.esq = this.dir = null;
    }
}

class ArvoreSecundaria {
    NoArvoreSecundaria raiz;
    public ArvoreSecundaria() {
        raiz = null;
    }
    public void inserir(SHOW show) {
        raiz = inserir(raiz, show);
    }
    private NoArvoreSecundaria inserir(NoArvoreSecundaria no, SHOW show) {
        if (no == null) {
            no = new NoArvoreSecundaria(show);
        } else if (show.getTITLE().compareTo(no.chave) < 0) {
            no.esq = inserir(no.esq, show);
        } else if (show.getTITLE().compareTo(no.chave) > 0) {
            no.dir = inserir(no.dir, show);
        }
        return no;
    }
    public boolean pesquisa(String titulo, boolean printRaiz) {
        if (printRaiz) System.out.print("raiz ");
        return pesquisa(raiz, titulo);
    }
    private boolean pesquisa(NoArvoreSecundaria no, String titulo) {
        if (no == null) return false;
        if (titulo.equals(no.chave)) return true;
        else if (titulo.compareTo(no.chave) < 0) {
            System.out.print("esq ");
            return pesquisa(no.esq, titulo);
        } else {
            System.out.print("dir ");
            return pesquisa(no.dir, titulo);
        }
    }
}

class NoArvorePrincipal {
    int chave;
    NoArvorePrincipal esq, dir;
    ArvoreSecundaria arvoreSecundaria;
    public NoArvorePrincipal(int chave) {
        this.chave = chave;
        this.esq = this.dir = null;
        this.arvoreSecundaria = new ArvoreSecundaria();
    }
}

class Arvore {
    NoArvorePrincipal raiz;
    public Arvore() {
        int[] chaves = {7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14};
        for (int chave : chaves) inserirAno(chave);
    }

    private void inserirAno(int chave) {
        raiz = inserirAno(chave, raiz);
    }

    private NoArvorePrincipal inserirAno(int chave, NoArvorePrincipal no) {
        if (no == null) return new NoArvorePrincipal(chave);
        if (chave < no.chave) no.esq = inserirAno(chave, no.esq);
        else if (chave > no.chave) no.dir = inserirAno(chave, no.dir);
        return no;
    }

    public void inserir(SHOW s) {
        int mod = s.getRELEASE_YEAR() % 15;
        inserir(s, mod, raiz);
    }

    private void inserir(SHOW s, int mod, NoArvorePrincipal no) {
        if (no == null) return;
        if (mod == no.chave) no.arvoreSecundaria.inserir(s);
        else if (mod < no.chave) inserir(s, mod, no.esq);
        else inserir(s, mod, no.dir);
    }

    public boolean pesquisar(String titulo) {
        return pesquisa(raiz, titulo, true);
    }

    private boolean pesquisa(NoArvorePrincipal no, String titulo, boolean printRaiz) {
        if (no == null) return false;
        boolean achou = no.arvoreSecundaria.pesquisa(titulo, printRaiz);
        if (achou) return true;
        System.out.print(" ESQ ");
        if (pesquisa(no.esq, titulo, false)) return true;
        System.out.print(" DIR ");
        if (pesquisa(no.dir, titulo, false)) return true;
        return false;
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
        if (TYPE == null || TYPE.isEmpty()) {
            TYPE = "NaN";
        }
        this.TYPE = TYPE;
    }

    private void setTITLE(String TITLE) {
        if (TITLE == null || TITLE.isEmpty()) {
            TITLE = "NaN";
        }
        this.TITLE = TITLE;
    }

    private void setDIRECTOR(String[] DIRECTOR) {
        if (DIRECTOR == null || DIRECTOR.length == 0 || DIRECTOR[0].isEmpty()) {
            this.DIRECTOR = new String[]{"NaN"};
        } else {
            this.DIRECTOR = new String[DIRECTOR.length];
            for (int i = 0; i < DIRECTOR.length; i++) {
                this.DIRECTOR[i] = DIRECTOR[i].trim();
            }
        }
    }

    private void setCAST(String[] CAST) {
        if (CAST == null || CAST.length == 0 || CAST[0].isEmpty()) {
            CAST = new String[]{"NaN"};
        }
        for (int i = 0; i < CAST.length; i++) {
            CAST[i] = CAST[i].trim();
        }
        ordenar(CAST);
        this.CAST = new String[CAST.length];
        for (int i = 0; i < CAST.length; i++) {
            this.CAST[i] = CAST[i].trim();
        }
    }

    private void setCOUNTRY(String COUNTRY) {
        if (COUNTRY == null || COUNTRY.isEmpty()) {
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
        if (RATING == null || RATING.isEmpty()) {
            RATING = "NaN";
        }
        this.RATING = RATING;
    }

    private void setDURATION(String DURATION) {
        if (DURATION == null || DURATION.isEmpty()) {
            DURATION = "NaN";
        }
        this.DURATION = DURATION;
    }

    private void setLISTED_IN(String[] LISTED_IN) {
        if (LISTED_IN == null || LISTED_IN.length == 0 || LISTED_IN[0].isEmpty()) {
            LISTED_IN = new String[]{"NaN"};
        }
        for (int i = 0; i < LISTED_IN.length; i++) {
            LISTED_IN[i] = LISTED_IN[i].trim();
        }
        ordenar(LISTED_IN);
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
        if (!partes.get(6).isEmpty()) {
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

        try {
            while (!(entrada = sc.nextLine()).equals("FIM")) {
                BufferedReader br = new BufferedReader(new FileReader("/tmp/disneyplus.csv"));
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
        sc.close();
    }
}