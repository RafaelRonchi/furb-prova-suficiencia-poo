package models;

import java.util.Date;

public class Intermunicipal extends Viagem{
    public Intermunicipal(String placaOnibus, String nomeMotorista, Date dataViagem) {
        super(placaOnibus, nomeMotorista, dataViagem);
    }

    @Override
    public void addPassageiro(Passageiro passageiro) {
        if (getPassageiros().size() < 45) {
            super.addPassageiro(passageiro);
        } else {
            System.out.println("Limite máximo de passageiros atingido. Passageiro não adicionado.");
        }
    }


    public float getValorTotal() {
        float valorTotal = 0;
        for (Passageiro passageiro : getPassageiros()) {
            valorTotal += passageiro.getTarifa() + 3.15f;
        }
        return valorTotal;
    }
}
