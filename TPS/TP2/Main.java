import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
 
class SHOW{
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

    //CONSTRUTORES

    public SHOW(){}


    // FUNÇÕES SET

    public void setID(String SHOW_ID) {
        this.SHOW_ID = SHOW_ID;
    }

    private void setTYPE(String TYPE)
    {
        if(TYPE == "")
       {
         TYPE = "NaN";
       }
        this.TYPE = TYPE;
    }

    private void setTITLE(String TITLE)
    {
        if(TITLE == "")
        {
          TITLE = "NaN";
        }
        this.TITLE = TITLE;
    }

    private void setDIRECTOR(String[] DIRECTOR)
    {
        if(DIRECTOR[0] == "")
        {
          DIRECTOR[0] = "NaN";
        }
        this.DIRECTOR = new String[DIRECTOR.length];
        for(int i = 0; i < DIRECTOR.length; i++)
        {
            this.DIRECTOR[i] = DIRECTOR[i].trim();
        }
    }

    private void setCAST(String[] CAST)
    {
        if(CAST[0] == "")
        {
          CAST[0] = "NaN";
        }
        this.CAST = new String[CAST.length];
        for(int i = 0; i < CAST.length; i++)
        {
            this.CAST[i] = CAST[i].trim();
        }
    }

    private void setCOUNTRY(String COUNTRY)
    {
        if(COUNTRY == "")
        {
          COUNTRY = "NaN";
        }
        this.COUNTRY = COUNTRY;
    }

    private void setDATA_ADDED(String DATA_ADDED) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
            this.DATE_ADDED = sdf.parse(DATA_ADDED);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data: " + DATA_ADDED);
            this.DATE_ADDED = null;
        }
    }
    
    private void setRELEASE_YEAR(String RELEASE_YEAR)
    {
        this.RELEASE_YEAR = Integer.parseInt(RELEASE_YEAR);
    }

    private void setRATING(String RATING)
    {
        if(RATING == "")
        {
          RATING = "NaN";
        }
        this.RATING = RATING;
    }

    private void setDURATION(String DURATION)
    {
        if(DURATION == "")
        {
          DURATION = "NaN";
        }
        this.DURATION = DURATION;
    }

    private void setLISTED_IN(String[] LISTED_IN)
    {
        if(LISTED_IN[0] == "")
        {
          LISTED_IN[0] = "NaN";
        }
        this.LISTED_IN = new String[LISTED_IN.length];
        for(int i = 0; i < LISTED_IN.length; i++)
        {
            this.LISTED_IN[i] = LISTED_IN[i];
        }
    }

    // FUNÇÕES GET

    public String getID()
    {
        return SHOW_ID;
    }

    public String getTYPE()
    {
        return TYPE;
    }

    public String getTITLE()
    {
        return TITLE;
    }

    public void getDIRECTOR()
    {
        System.out.print("[");
        for(int i = 0; i < DIRECTOR.length-1; i++)
        {
            System.out.print(DIRECTOR[i] + ", ");
        }
        System.out.print(DIRECTOR[DIRECTOR.length-1] + "] ## ");
    }
    
    public void getCAST()
    {
        System.out.print("[");
        for(int i = 0; i < CAST.length-1; i++)
        {
            System.out.print(CAST[i] + ", ");
        }
        System.out.print(CAST[CAST.length-1] + "] ## ");
    }

    public String getCOUNTRY()
    {
        return COUNTRY;
    }

    public Date getDATA_ADDED()
    {
        return DATE_ADDED;
    }

    public int getRELEASE_YEAR()
    {
        return RELEASE_YEAR;
    }

    public String getRATING()
    {
        return RATING;
    }

    public String getDURATION()
    {
        return DURATION;
    }

    public void getLISTED_IN()
    {
        System.out.print("[");
        for(int i = 0; i < LISTED_IN.length-1; i++)
        {
            System.out.print(LISTED_IN[i] + ", ");
        }
        System.out.print(LISTED_IN[LISTED_IN.length-1]+ "]");
    }
    


    //OUTRAS FUNÇÕES

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
        setDATA_ADDED(partes.get(6));
        setRELEASE_YEAR(partes.get(7));
        setRATING(partes.get(8));
        setDURATION(partes.get(9));
        setLISTED_IN(partes.get(10).split(","));
        
        
    }

    public void imprimir()
    {
        System.out.print("=> " + getID() + " ## " + getTYPE() + " ## " + getTITLE() + " ## ");
        getDIRECTOR();
        getCAST();
        System.out.print(getCOUNTRY() + " ## ");
        
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        System.out.print(sdf.format(getDATA_ADDED()) + " ## ");

        System.out.print(getRELEASE_YEAR() + " ## " + getRATING() + " ## " + getDURATION() + " ## ");
        getLISTED_IN();
        System.out.println();
    }
    


}


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String entrada;

       try {
        while (!(entrada = sc.nextLine()).equals("FIM")) {
            BufferedReader br = new BufferedReader(new FileReader("tmp/disneyplus.csv"));
            String linha = br.readLine(); // pula o cabeçalho
            boolean encontrado = false;

            while ((linha = br.readLine()) != null && !encontrado) {
                if (linha.startsWith(entrada + ",")) {
                    SHOW show = new SHOW();
                    show.Leitura(linha);
                    show.imprimir();
                    encontrado = true;
                }
            }

            br.close();
        }
    } catch (IOException e) {
        System.out.println("Erro ao acessar o arquivo: " + e.getMessage());
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

