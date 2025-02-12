package org.bibendum_coders.ghostbusters_2.controllers;

import java.util.List;

import org.bibendum_coders.ghostbusters_2.models.CazadorModel;
import org.bibendum_coders.ghostbusters_2.models.Clase;
import org.bibendum_coders.ghostbusters_2.models.FantasmaModel;

public class CazadorController {
    private CazadorModel cazadorModel;
    
    public CazadorController() {
        this.cazadorModel = new CazadorModel();
    }
    public void capturarFantasma(String nombre, int clase, String nivel, String habilidad) {//aqui se mantiene de meomento int (vosotros teníais string) y no el enum porque es lo que devuelve el jugador
        Clase claseEnum = Clase.values()[clase - 1];
        cazadorModel.getFantasmas().add(new FantasmaModel(nombre, claseEnum, nivel, habilidad)); 
        //printMenuView(); aquí se llama al metodo de la vista
    }
    public void liberarFantasma(int intFantasma) {
        cazadorModel.getFantasmas().remove(intFantasma-1);//esto le preguntamos a Arancha, es la opción  que devuelve el usuario y es habitual y se sobrentiende que se le resta 1 para coincidir con la posición de array
        //printMenuView(); aquí se llama al metodo de la vista
    }
    public void manejarMenu(int userOption) {
        if(userOption == 1) {
            /*LLAMADA A LA VISTA 
            CatchView catchView = new CatchView(scanner, this);
            catchView.showCatchView();*/
        }
        else if(userOption == 2) {
            
            @SuppressWarnings("unused")
            List<FantasmaModel> fantasmas = cazadorModel.getFantasmas();
            /*LLAMADA A LA VISTA 
            BichuListView bichuListView = new BichuListView(fantasmas);
            bichuListView.showBichuListView();
            LLAMADA A VISTA DE ELIMINAR
            ReleaseBichuView releaseBichuView = new ReleaseBichuView(scanner, this);
            releaseBichuView.showReleaseBichuListView();*/
        }
        else if(userOption == 6) {
            /*ExitView exitView = new ExitView(scanner, this);
            exitView.showExitView();*/

        }
        /*
        public void printMenuView() {
            MenuView menuView = new MenuView(scanner, this);
            menuView.showMenuView();
        }
         */

    }


}
