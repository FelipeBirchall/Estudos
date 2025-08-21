import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.DataInputStream;



public class Main {
    public static void main(String[] args) {
        Livro livro1 = new Livro(1, "Eu, Robo", "Isaac Asimov", 29.90f);
        Livro livro2 = new Livro(2, "Eu sou a Lenda", "Richard Matheson", 39.90f);

        byte ba[];

        try{
            FileOutputStream arq = new FileOutputStream("dados/livros.db");
            DataOutputStream dos = new DataOutputStream(arq);

            ba = livro1.toByteArray();
            dos.writeInt(ba.length);
            dos.write(ba);

            ba = livro2.toByteArray();
            dos.writeInt(ba.length);
            dos.write(ba);
            
            dos.close();
            arq.close();

            FileInputStream arq2 = new FileInputStream("dados/livros.db");
            DataInputStream dis = new DataInputStream(arq2);

            Livro livro3 = new Livro();
            Livro livro4 = new Livro();

            int tam = dis.readInt();
            ba = new byte[tam];
            dis.read(ba);
            livro3.fromByteArray(ba);

            tam = dis.readInt();
            ba = new byte[tam];
            dis.read(ba);
            livro4.fromByteArray(ba); 

            System.out.println(livro3);
            System.out.println(livro4);


        } catch(Exception e){
            e.printStackTrace();
        }



    }
}