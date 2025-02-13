package org.bibendum_coders.ghostbusters_2.views;

import javax.swing.*;
import java.awt.*;
import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;

public class MenuPrincipalView {
    private JFrame frame;
    private CazadorController cazadorController;

    public MenuPrincipalView(CazadorController cazadorController) {
        this.cazadorController = cazadorController;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Menú Principal - Cazador de Fantasmas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1, 10, 10));
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        JButton capturarButton = new JButton("Capturar Fantasma");
        capturarButton.setFont(new Font("Arial", Font.BOLD, 16));
        capturarButton.addActionListener(e -> cazadorController.manejarMenu(1)); 
        frame.add(capturarButton);

        JButton editarButton = new JButton("Editar Fantasmas");
        editarButton.setFont(new Font("Arial", Font.BOLD, 16));
        editarButton.addActionListener(e -> cazadorController.manejarMenu(2)); 
        frame.add(editarButton);

        JButton salirButton = new JButton("Salir");
        salirButton.setFont(new Font("Arial", Font.BOLD, 16));
        salirButton.addActionListener(e -> cazadorController.manejarMenu(6)); 
        frame.add(salirButton);

        frame.setVisible(true);
    }
}
