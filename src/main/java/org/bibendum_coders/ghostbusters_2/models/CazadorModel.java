package org.bibendum_coders.ghostbusters_2.models;

import java.util.ArrayList;
import java.util.List;

public class CazadorModel {
    private List<FantasmaModel> fantasmas; //posibilidad de mirar que tipo de lista va mejor
    private int contadorId;
    public CazadorModel() {
        this.fantasmas = new ArrayList<>();
        this.contadorId = 1;
    }
    
    public List<FantasmaModel> getFantasmas() {
        return fantasmas;
    }
    public int getContadorId() {
        return contadorId;
    }
    public void addFantasma(FantasmaModel fantasmaModel) {
        fantasmas.add(fantasmaModel);
    }

    public void deleteFantasma(int fantasmaNumber) {
        fantasmas.remove(fantasmaNumber-1);
    }

}
