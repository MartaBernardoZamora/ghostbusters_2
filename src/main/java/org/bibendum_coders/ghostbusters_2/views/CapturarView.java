package org.bibendum_coders.ghostbusters_2.views;

import java.util.Scanner;

import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;
import org.bibendum_coders.ghostbusters_2.models.Clase;

public class CapturarView {
    private Scanner scanner;
    private CazadorController cazadorController;
    public CapturarView(Scanner scanner, CazadorController cazadorController) {
        this.scanner = scanner;
        this.cazadorController = cazadorController;
    }
    
    public void showCapturarView() {
        System.out.println("¡Atrapa un Bichu!");
        System.out.println("Nombre: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        System.out.println("Elige el número de la clase: ");
        for(Clase claseString : Clase.values()) {
            int i = claseString.ordinal()+1;
            System.out.println(i + ". " + claseString.getClaseString());
        }
        int clase = scanner.nextInt();
        System.out.println("Peligro (alto, medio, bajo): ");
        scanner.nextLine();
        String nivel = scanner.nextLine();
        System.out.println("Habilidad: ");
        String habilidad = scanner.nextLine();
        cazadorController.capturarFantasma(nombre, clase, nivel, habilidad);
    }

}
