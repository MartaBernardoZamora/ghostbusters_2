package org.bibendum_coders.ghostbusters_2.views;

import java.util.List;
import java.util.Scanner;

import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;
import org.bibendum_coders.ghostbusters_2.models.FantasmaModel;

public class FantasmasView {
    private List<FantasmaModel> fantasmas;
    private Scanner scanner;
    private CazadorController cazadorController;

    public FantasmasView(List<FantasmaModel> fantasmas, Scanner scanner, CazadorController cazadorController) {
        this.fantasmas = fantasmas;
        this.scanner = scanner;
        this.cazadorController = cazadorController;
    }
    public void showFantasmasView() {
        System.out.println("Tu lista de fantasmas capturados es: ");
        if(fantasmas.size()==0) {
            System.out.println("No hay capturas");
        }else{
            System.out.println("==================================================================================");
            System.out.printf("%-5s %-25s %-35s %-10s %-20s%n", "ID","Nombre", "Nivel", "Peligro", "Habilidad");
            System.out.println("----------------------------------------------------------------------------------");
            for(FantasmaModel fantasma : fantasmas) {
                System.out.printf("%-5s %-25s %-35s %-10s %-20s%n",
                    fantasmas.indexOf(fantasma)+1,
                    fantasma.getNombre(),
                    fantasma.getClase().getClaseString(),
                    fantasma.getNivelPeligro(),
                    fantasma.getHabilidad()
                );
            }
            System.out.println("==================================================================================\n");
        }
    }
    
    public void showeEliminarView() {
        System.out.println("Selecciona el fantasma que deseas liberar. Para volver al menú, envía 0: ");
        int fantasmaIndex = scanner.nextInt();
        if(fantasmaIndex == 0) {
            cazadorController.printMenuView();
            return;
        };
        cazadorController.liberarFantasma(fantasmaIndex-1);
    }
}
