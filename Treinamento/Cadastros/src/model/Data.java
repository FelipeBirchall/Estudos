package model;

public class Data {

    private int dia, mes, ano;

    public Data(int dia, int mes, int ano){
        setDia(dia); setMes(mes); setAno(ano);
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getDia() {
        return dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getMes() {
        return mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    public int getAno() {
        return ano;
    }
}
