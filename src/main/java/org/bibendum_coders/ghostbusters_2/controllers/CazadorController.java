package org.bibendum_coders.ghostbusters_2.controllers;

import java.util.List;
import java.util.function.Supplier;

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
    public void capturarFantasma(String nombre, int clase, String nivel, String habilidad) {
        Clase claseEnum = Clase.values()[clase - 1];
        FantasmaModel fantasmaModel = new FantasmaModel(contadorId++, nombre, claseEnum, nivel, habilidad);
        cazadorModel.getFantasmas().add(fantasmaModel); 
        
    }
    public void liberarFantasma(int intFantasma) {
        cazadorModel.getFantasmas().removeIf(fantasma -> fantasma.getId() == intFantasma);
    }
    public void manejarMenu(int userOption) {
        
        if(userOption == 1) {
            showCapturaFantasmaView();
        }
        else if(userOption == 2) {
            List<FantasmaModel> fantasmas = cazadorModel.getFantasmas();
            showEditarFantasmasView(fantasmas); 
        }
        else if(userOption == 6) {
            salir();
        }
    }
    public void showCapturaFantasmaView() {
        new CapturaFantasmaView(this);
    }
    public void showEditarFantasmasView(List<FantasmaModel> fantasmas) {
        new EditarFantasmasView(this, fantasmas);
    }
    public void salir() {
        System.exit(0);
    }
    public void printMenuView() {
        MenuPrincipalView.getInstance(this);
        
    }
    public CazadorModel getCazadorModel() {
        return cazadorModel;
    }
    public int getContadorId() {
        return contadorId;
    }

}
