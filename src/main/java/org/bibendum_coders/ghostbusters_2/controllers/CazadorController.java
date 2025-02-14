package org.bibendum_coders.ghostbusters_2.controllers;

import java.util.List;


import org.bibendum_coders.ghostbusters_2.models.CazadorModel;
import org.bibendum_coders.ghostbusters_2.models.Clase;
import org.bibendum_coders.ghostbusters_2.models.FantasmaModel;
import org.bibendum_coders.ghostbusters_2.views.MenuPrincipalView;
import org.bibendum_coders.ghostbusters_2.views.CapturaFantasmaView;
import org.bibendum_coders.ghostbusters_2.views.EditarFantasmasView;

public class CazadorController {
    private CazadorModel cazadorModel;
    private int contadorId;
   
    public CazadorController() {
        this.cazadorModel = new CazadorModel();
        this.contadorId = 1;
       
    }
    public void capturarFantasma(String nombre, int clase, String nivel, String habilidad) {//aqui se mantiene de meomento int (vosotros teníais string) y no el enum porque es lo que devuelve el jugador
        Clase claseEnum = Clase.values()[clase - 1];
        cazadorModel.getFantasmas().add(new FantasmaModel(contadorId++, nombre, claseEnum, nivel, habilidad)); 
        
    }
    public void liberarFantasma(int intFantasma) {
        
        cazadorModel.getFantasmas().removeIf(fantasma -> fantasma.getId() == intFantasma);
    }
    public void manejarMenu(int userOption) {
        
        if(userOption == 1) {
            new CapturaFantasmaView(this);
        }
        else if(userOption == 2) {
            List<FantasmaModel> fantasmas = cazadorModel.getFantasmas();
            new EditarFantasmasView(this, fantasmas); 
        }
        else if(userOption == 6) {
            System.exit(0);

        }

    }
          
    
    public void printMenuView() {
        new MenuPrincipalView(this);
        
    }
    public CazadorModel getCazadorModel() {
        return cazadorModel;
    }
    public int getContadorId() {
        return contadorId;
    }

}
