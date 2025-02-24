package models;

public class Estudante extends Passageiro{
    private String escola;

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public Estudante(String nome, int idade, String escola) {
        super(nome, idade);
        this.escola = escola;
    }

    @Override
    public float getTarifa() {
        return super.getTarifa() / 2;
    }
}
