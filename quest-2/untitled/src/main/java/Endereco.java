import java.io.Serializable;

public class Endereco implements Serializable {

    private String rua;
    private String numero;
    private String complemento;
    private String CEP;
    private String cidade;
    private String estado;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Endereco(String rua, String numero, String complemento, String CEP, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.CEP = CEP;
        this.cidade = cidade;
        this.estado = estado;
    }
}
