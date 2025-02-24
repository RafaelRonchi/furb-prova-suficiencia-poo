package models;

public class Passageiro {
    private String nome;
    private int idade;
    private static final float tarifa = 5.00F;

    public float getTarifa() {
        return tarifa;
    }

    public Passageiro(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
