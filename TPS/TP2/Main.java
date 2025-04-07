import java.text.SimpleDateFormat;
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

    private void setID(String SHOW_ID)
    {
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
        this.TITLE = TITLE;
    }

    private void setDIRECTOR(String[] DIRECTOR)
    {
        this.DIRECTOR = new String[DIRECTOR.length];
        for(int i = 0; i < DIRECTOR.length; i++)
        {
            this.DIRECTOR[i] = DIRECTOR[i].trim();
        }
    }

    private void setCAST(String[] CAST)
    {
        this.CAST = new String[CAST.length];
        for(int i = 0; i < CAST.length; i++)
        {
            this.CAST[i] = CAST[i].trim();
        }
    }

    private void setCOUNTRY(String COUNTRY)
    {
        this.COUNTRY = COUNTRY;
    }

    private void setDATA_ADDED(String DATA_ADDED) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM d , yyyy", Locale.ENGLISH);
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
        this.RATING = RATING;
    }

    private void setDURATION(String DURATION)
    {
        this.DURATION = DURATION;
    }

    private void setLISTED_IN(String[] LISTED_IN)
    {
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
        for(int i = 0; i < DIRECTOR.length-1; i++)
        {
            System.out.print(DIRECTOR[i] + ", ");
        }
        System.out.print(DIRECTOR[DIRECTOR.length-1]);
    }

    public void getCAST()
    {
        System.out.println();
        for(int i = 0; i < CAST.length-1; i++)
        {
            System.out.print(CAST[i] + ", ");
        }
        System.out.print(CAST[CAST.length-1]);
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
        for(int i = 0; i < LISTED_IN.length-1; i++)
        {
            System.out.print(LISTED_IN[i] + ", ");
        }
        System.out.print(LISTED_IN[LISTED_IN.length-1]);
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
        System.out.println(getID());
        
        System.out.println(getTYPE());

        System.out.println(getTITLE());

        getDIRECTOR();

        getCAST();

        System.out.println("\n" + getCOUNTRY());

        System.out.println(getDATA_ADDED());

        System.out.println(getRELEASE_YEAR());

        System.out.println(getRATING());

        System.out.println(getDURATION());

        getLISTED_IN();
    }
    


}


public class Main {

    static public void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

       /*ArrayList<SHOW> shows = new ArrayList<>(); */ 

        String entrada = sc.nextLine();

        SHOW show = new SHOW();

        show.Leitura(entrada);

        show.imprimir();

        sc.close();

    }
    
}
