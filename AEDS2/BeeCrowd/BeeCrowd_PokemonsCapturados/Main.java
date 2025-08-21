package BeeCrowd_PokemonsCapturados;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] Pokemons = {"Pikachu", "Bulbasaur", "Charmander", "Squirtle", "Jigglypuff",
    "Meowth", "Psyduck", "Snorlax", "Gengar", "Eevee",
    "Vaporeon", "Jolteon", "Flareon", "Machop", "Geodude",
    "Abra", "Kadabra", "Alakazam", "Magikarp", "Gyarados",
    "Lapras", "Ditto", "Porygon", "Aerodactyl", "Articuno",
    "Zapdos", "Moltres", "Mewtwo", "Mew", "Dragonite"};

        int numCapturados = sc.nextInt();
        sc.nextLine();

        String[] Capturados = new String[numCapturados];

        for(int i = 0; i < numCapturados; i++)
        {
            Capturados[i] = sc.nextLine();
        }

        System.out.println("Falta(m) " + naoCapturados(Pokemons, Capturados, numCapturados) + " pokemon(s).");
    
    }

    static int naoCapturados(String[] Pokemons, String[] Capturados, int numCapturados)
    {
        int totalPokemons = Pokemons.length;
        int cont = 0;
        boolean capturado;
        for(int i = 0; i < numCapturados; i++)
        {
            capturado = false;
            for(int j = 0; j < Pokemons.length; j++)
            {
                if(Capturados[i].compareTo(Pokemons[j]) == 0)
                {
                    capturado = true;
                    j = Pokemons.length;
                }
            }
            if(capturado == true){cont++;}
        }
        return totalPokemons - cont;
    }
    
}
