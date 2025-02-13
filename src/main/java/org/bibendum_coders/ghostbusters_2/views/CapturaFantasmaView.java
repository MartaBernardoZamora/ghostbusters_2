package org.bibendum_coders.ghostbusters_2.views;

import javax.swing.*;
import java.awt.*;
import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;
import org.bibendum_coders.ghostbusters_2.models.Clase;

public class CapturaFantasmaView {
    private JFrame frame;
    private CazadorController cazadorController;

    public CapturaFantasmaView(CazadorController cazadorController) {
        this.cazadorController = cazadorController;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Capturar Fantasma");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nombreField = new JTextField();
        String[] clases = new String[Clase.values().length];
        for (int i = 0; i < Clase.values().length; i++) {
            clases[i] = Clase.values()[i].getClaseString();
        }
        JComboBox<String> claseComboBox = new JComboBox<>(clases);
        JTextField nivelField = new JTextField();
        JTextField habilidadField = new JTextField();

        inputPanel.add(new JLabel("Nombre del Fantasma:"));
        inputPanel.add(nombreField);
        inputPanel.add(new JLabel("Clase del Fantasma:"));
        inputPanel.add(claseComboBox);
        inputPanel.add(new JLabel("Nivel de Peligro:"));
        inputPanel.add(nivelField);
        inputPanel.add(new JLabel("Habilidad Especial:"));
        inputPanel.add(habilidadField);

        JButton capturarButton = new JButton("Capturar Fantasma");
        capturarButton.addActionListener(e -> {
            try {
                String nombre = nombreField.getText();
                int claseIndex = claseComboBox.getSelectedIndex(); 
                String nivel = nivelField.getText();
                String habilidad = habilidadField.getText();
                if (nombre.isEmpty()) {
                    throw new IllegalArgumentException("El nombre del fantasma no puede estar vacío.");
                }
                if (claseIndex == -1) {
                    throw new IllegalArgumentException("Debes seleccionar una clase válida.");
                }//trabajar en esta parte!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


                if (nivel.isEmpty()) {
                    throw new IllegalArgumentException("El nivel de peligro no puede estar vacío.");
                }
                if (habilidad.isEmpty()) {
                    throw new IllegalArgumentException("La habilidad especial no puede estar vacía.");
                }


              
                cazadorController.capturarFantasma(nombre, claseIndex + 1, nivel, habilidad);
                JOptionPane.showMessageDialog(frame, "Fantasma \"" + nombre + "\" capturado exitosamente.");
                frame.dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        inputPanel.add(capturarButton);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
