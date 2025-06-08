package model;



public class Pessoa {
    private String nome;
    private String genero;
    private Data dataNascimento;
    private String email;
    private String endereco;

    public Pessoa(){}

    public Pessoa(String nome, String genero, Data dataNascimento, String email, String endereco)
    {
        setNome(nome); setGenero(genero); setDataNascimento(dataNascimento); setEmail(email); setEndereco(endereco); 
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getGenero() {
        return genero;
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public Data getDataNascimento() {
        return dataNascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEndereco() {
        return endereco;
    }


    public void exibirDados()
    {
        System.out.println("Nome: " + getNome());
        System.out.println("Genero: " + getGenero());
        System.out.println("Data de nascimento: " + dataNascimento.getDia() + "/" + dataNascimento.getMes() + "/" + dataNascimento.getAno());
        System.out.println("Email: " + getEmail());
        System.out.println("Endereco: " + getEndereco());
    } 


}
