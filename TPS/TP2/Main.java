import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

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

    public SHOW clone()
    {
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
        if(DATE_ADDED == null)
        {
            this.DATE_ADDED = null;
        }
        else{
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
        System.out.print("[");
        for (int i = 0; i < DIRECTOR.length - 1; i++) {
            System.out.print(DIRECTOR[i] + ", ");
        }
        System.out.print(DIRECTOR[DIRECTOR.length - 1] + "] ## ");
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
        System.out.print(LISTED_IN[LISTED_IN.length - 1] + "]");
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
        if(partes.get(6) != null && !partes.get(6).isEmpty())
        {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
                date_added = sdf.parse(partes.get(6));
            } catch (ParseException e) {
                System.out.println("Erro ao converter a data do " + getID() );
                date_added = null;
            }
        }
        setDATE_ADDED(date_added);
        
        setRELEASE_YEAR(Integer.parseInt(partes.get(7)));
        setRATING(partes.get(8));
        setDURATION(partes.get(9));
        setLISTED_IN(partes.get(10).split(","));

    }

    public void imprimir() {
        System.out.print("=> " + getID() + " ## " + getTYPE() + " ## " + getTITLE() + " ## ");
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
        SHOW[] shows = new SHOW[1370];
        int index = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("tmp/disneyplus.csv"));
            String linha = br.readLine(); // pula o cabeçalho
            while ((linha = br.readLine()) != null) {
                shows[index] = new SHOW();
                shows[index].Leitura(linha);
                index++;
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Erro ao acessar o arquivo: " + e.getMessage());
        }
        String entrada = sc.nextLine();
        while(!entrada.equals("FIM"))
        {
            for(int i = 0; i < index; i++)
            {
                if(entrada.equals(shows[i].getID()))
                {
                    shows[i].imprimir();
                    i = index;
                }
            }
            entrada = sc.nextLine();
        }

        sc.close();

         

        /*
        String entrada = sc.nextLine();
          
        SHOW[] shows = new SHOW[400];
        
        int index = 0;
        while(!entrada.equals("FIM"))
        {
        shows[index] = new SHOW();
        shows[index].Leitura(entrada);
        index++;
        entrada = sc.nextLine();
        }
        for(int i = 0; i < index; i++)
        {
        shows[i].imprimir();
        }
        
        sc.close(); 
        */
    }
}
