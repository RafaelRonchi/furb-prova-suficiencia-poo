package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Viagem {
    private String placaOnibus;
    private String nomeMotorista;
    private Date dataViagem;
    private List<Passageiro> passageiros;

    public void addPassageiro(Passageiro passageiro) {
        passageiros.add(passageiro);
    }
    public List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public float getValorTotal() {
        float valorTotal = 0;
        for (Passageiro passageiro : getPassageiros()) {
            valorTotal += passageiro.getTarifa();
        }
        return valorTotal;

    }

    public Viagem(String placaOnibus, String nomeMotorista, Date dataViagem) {
        this.placaOnibus = placaOnibus;
        this.nomeMotorista = nomeMotorista;
        this.dataViagem = dataViagem;
        this.passageiros = new ArrayList<>();

    }

    public String getPlacaOnibus() {
        return placaOnibus;
    }

    public void setPlacaOnibus(String placaOnibus) {
        this.placaOnibus = placaOnibus;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public Date getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(Date dataViagem) {
        this.dataViagem = dataViagem;
    }
}
