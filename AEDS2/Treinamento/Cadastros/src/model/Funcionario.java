package model;


public class Funcionario extends Pessoa{
    private String cargo;
    private float salario;

    public Funcionario(String nome, String genero, Data dataNascimento, String email, String endereco, String cargo, float salario){
        super(nome, genero, dataNascimento, email, endereco);
        setCargo(cargo); setSalario(salario);

    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getCargo() {
        return cargo;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
    public float getSalario() {
        return salario;
    }
    

    
    public void exibirDados()
    {
        super.exibirDados();
        System.out.println("Cargo: " + getCargo());
        System.out.println("Salario: " + getSalario());
    }


}
