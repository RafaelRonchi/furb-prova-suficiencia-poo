package models;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private List<Viagem> viagens;

    public Empresa() {
        this.viagens = new ArrayList<>();
    }

    public void addViagem(Viagem viagem) {
        this.viagens.add(viagem);
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public List<Passageiro> getPassageirosMaisIdosos(){
        List<Passageiro> passageirosMaisIdosos = new ArrayList<>();
        int maiorIdade = -1;
        for (Viagem viagem : viagens) {
            for (Passageiro passageiro : viagem.getPassageiros()) {
                if (passageiro.getIdade() > maiorIdade){
                    passageirosMaisIdosos.clear();
                    passageirosMaisIdosos.add(passageiro);
                    maiorIdade = passageiro.getIdade();
                } else if (passageiro.getIdade() == maiorIdade){
                    passageirosMaisIdosos.add(passageiro);
                }
            }
        }
        return  passageirosMaisIdosos;
    }

}
