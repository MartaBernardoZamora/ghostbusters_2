package org.bibendum_coders.ghostbusters_2.views;

import java.util.Scanner;

import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;

public class MenuView {
    private Scanner scanner;
    private CazadorController cazadorController;

    public MenuView(Scanner scanner, CazadorController cazadorController) {
        this.scanner = scanner;
        this.cazadorController = cazadorController;
	}

    public void showMenuView() {
        System.out.println("""
           Opciones:
            1. Capturar un nuevo fantasma
            2. Ver lista de fantasmas capturados
            3. Liberar un fantasma
            4. Filtrar fantasmas por clase
            5. Ver fantasmas capturados en un mes
            6. Salir

            Por favor, selecciona una opción (1-6):
            """
        );
        int userOption = scanner.nextInt();
        cazadorController.manejarMenu(userOption);
    }
}
