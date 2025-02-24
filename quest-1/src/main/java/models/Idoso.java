package models;

public class Idoso extends Passageiro {
    private String RG;

    public Idoso(String nome, int idade, String RG) throws IllegalArgumentException {
        super(nome, idade);
        this.RG = RG;
    }

    @Override
    public float getTarifa() {
        return 0;
    }
}
