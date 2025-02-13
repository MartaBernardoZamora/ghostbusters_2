package org.bibendum_coders.ghostbusters_2.controllers;

import java.util.List;
import java.util.Scanner;

import org.bibendum_coders.ghostbusters_2.models.CazadorModel;
import org.bibendum_coders.ghostbusters_2.models.Clase;
import org.bibendum_coders.ghostbusters_2.models.FantasmaModel;
import org.bibendum_coders.ghostbusters_2.views.MenuPrincipalView;
import org.bibendum_coders.ghostbusters_2.views.CapturaFantasmaView;
import org.bibendum_coders.ghostbusters_2.views.FantasmasView;

public class CazadorController {
    private CazadorModel cazadorModel;
    private int contadorId;
    private Scanner scanner;
    public CazadorController() {
        this.cazadorModel = new CazadorModel();
        this.contadorId = 1;
        this.scanner = new Scanner(System.in);
    }
    public void capturarFantasma(String nombre, int clase, String nivel, String habilidad) {//aqui se mantiene de meomento int (vosotros teníais string) y no el enum porque es lo que devuelve el jugador
        Clase claseEnum = Clase.values()[clase - 1];
        cazadorModel.getFantasmas().add(new FantasmaModel(contadorId++, nombre, claseEnum, nivel, habilidad)); 
        printMenuView();
    }
    public void liberarFantasma(int intFantasma) {
        cazadorModel.getFantasmas().remove(intFantasma);//esto le preguntamos a Arancha, es la opción  que devuelve el usuario y es habitual y se sobrentiende que se le resta 1 para coincidir con la posición de array
        printMenuView();
    }
    public void manejarMenu(int userOption) {
        System.out.println("Opcion elegida: " + userOption);//para probar flujo
        if(userOption == 1) {
            new CapturaFantasmaView(this); 
            
        }
        else if(userOption == 2) {
            List<FantasmaModel> fantasmas = cazadorModel.getFantasmas();
            FantasmasView fantasmasView = new FantasmasView(fantasmas, scanner, this);
            fantasmasView.showFantasmasView();
            fantasmasView.showeEliminarView();
        }
        else if(userOption == 6) {
            /*ExitView exitView = new ExitView(scanner, this);
            exitView.showExitView();*/

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
