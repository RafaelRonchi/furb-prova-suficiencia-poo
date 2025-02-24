package models;

import java.util.Date;

public class Municipal extends Viagem{
    public Municipal(String placaOnibus, String nomeMotorista, Date dataViagem) {
        super(placaOnibus, nomeMotorista, dataViagem);
    }

    @Override
    public void addPassageiro(Passageiro passageiro) {
        if (getPassageiros().size() < 65) {
            super.addPassageiro(passageiro);
        } else {
            System.out.println("Limite máximo de passageiros atingido. Passageiro não adicionado.");
        }
    }

}
