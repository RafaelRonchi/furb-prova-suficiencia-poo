import java.io.Serializable;
import java.util.Objects;

public class Atleta implements Serializable, Comparable<Atleta> {
    private String nome;
    private String cpf;
    private float altura;
    private float peso;
    private Endereco endereco;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    @Override
    public int compareTo(Atleta outro) {
        return this.cpf.compareTo(outro.cpf);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Atleta atleta = (Atleta) obj;
        return Objects.equals(cpf, atleta.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

   public Atleta(String nome, String cpf, float altura, float peso, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.altura = altura;
        this.peso = peso;
        this.endereco = endereco;
    }
}
