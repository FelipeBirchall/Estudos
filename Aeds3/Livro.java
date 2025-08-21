import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;


public class Livro {
    private int idLivro;
    private String titulo;
    private String autor;
    private float preco;


    public Livro(){};

    public Livro(int idLivro, String titulo, String autor, float preco) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }

    public int getIdLivro() {
        return idLivro;
    }
    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }

    public byte[] toByteArray() throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(idLivro);
        dos.writeUTF(titulo);
        dos.writeUTF(autor);
        dos.writeFloat(preco);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] data) throws Exception{
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bais);
        this.idLivro = dis.readInt();
        this.titulo = dis.readUTF();
        this.autor = dis.readUTF();
        this.preco = dis.readFloat();
    }

    public String toString(){
        return "\nID: " + idLivro + "\nTitulo: " + titulo + "\nAutor: " + autor + "\nPreco: " + preco;

    }

}
