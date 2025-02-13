package org.bibendum_coders.ghostbusters_2;

import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;


public final class App {
    private App() {
    }
    public static void main(String[] args) {
        CazadorController cazadorController = new CazadorController();
        cazadorController.printMenuView();
    }
}
