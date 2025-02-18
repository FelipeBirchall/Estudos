package Arquivo_Java;

import java.util.*;
import java.io.RandomAccessFile;

public class Main {

    static public void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        try(RandomAccessFile file = new RandomAccessFile("exemplo.txt", "rw")){

            

        for (int i = 0; i < n; i++) {
            double valor = scanner.nextDouble();
            file.writeDouble(valor);
        }  
        file.close();
        
        file.seek(0);
            long posicao = file.length();

            while (posicao > 0) {
                posicao -= 8; // Tamanho de um double em bytes
                file.seek(posicao);
                double valor = file.readDouble();
                System.out.println(valor);
        }
        }
        catch(Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }
        
        scanner.close();

   }
}
