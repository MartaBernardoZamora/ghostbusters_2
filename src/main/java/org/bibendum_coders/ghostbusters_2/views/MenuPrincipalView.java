package org.bibendum_coders.ghostbusters_2.views;

import javax.swing.*;
import java.awt.*;
import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;

public class MenuPrincipalView {
    private static MenuPrincipalView instance; // Instancia única del menú principal
    private JFrame frame;
    private CazadorController cazadorController;

    // Constructor privado para evitar la creación de múltiples instancias
    private MenuPrincipalView(CazadorController cazadorController) {
        this.cazadorController = cazadorController;
        initialize();
    }

    // Método para obtener la instancia única del menú principal
    public static MenuPrincipalView getInstance(CazadorController cazadorController) {
        if (instance == null || !instance.frame.isVisible()) {
            instance = new MenuPrincipalView(cazadorController);
        }
        return instance;
    }

    private void initialize() {
        frame = new JFrame("Menú Principal - Cazador de Fantasmas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        //frame.getContentPane().setBackground(Color.LIGHT_GRAY); // Fondo claro para el menú
       
        JLabel fondo = new JLabel(new ImageIcon("src/main/java/org/bibendum_coders/ghostbusters_2/resources/images/ghost.jpg"));
        fondo.setBounds(0, 0, 800, 500);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        // Botón "Capturar Fantasma"
        JButton capturarButton = createStyledButton("Capturar Fantasma");
        capturarButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar horizontalmente
        capturarButton.setMaximumSize(new Dimension(200, 40));
        capturarButton.addActionListener(e -> {
            frame.setVisible(false); // Ocultar el menú principal
            new CapturaFantasmaView(cazadorController);
        });
        panelBotones.add(capturarButton);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 10))); 

        // Botón "Editar Fantasmas"
        JButton editarButton = createStyledButton("Editar Fantasmas");
        editarButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar horizontalmente
        editarButton.setMaximumSize(new Dimension(200, 40));
        editarButton.addActionListener(e -> {
            frame.setVisible(false); // Ocultar el menú principal
            new EditarFantasmasView(
                cazadorController,
                cazadorController.getCazadorModel().getFantasmas()
            );
        });
        panelBotones.add(editarButton);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 10))); 


        // Botón "Salir"
        JButton salirButton = createStyledButton("Salir"); 
        salirButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar horizontalmente
        salirButton.setMaximumSize(new Dimension(200, 40));
        salirButton.addActionListener(e -> System.exit(0));// Salir del programa

        panelBotones.add(salirButton);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 10))); 

        frame.add(fondo);
        frame.add(panelBotones);
        
        frame.setVisible(true);
    }

    // Método para crear botones con estilo mejorado (fondo azul, fuente elegante y hover)
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Fuente elegante y tamaño adecuado
        button.setFocusPainted(false); // Eliminar el borde de foco
        button.setBackground(new Color(0, 123, 255)); // Fondo azul
        button.setForeground(Color.WHITE); // Texto blanco
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde negro limpio
        
        
        //button.setMargin(new Insets(10, 10, 10, 10)); // Espaciado interno uniforme
        

        // Efecto Hover: Cambiar el color cuando el ratón pasa por encima
        /*button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204)); // Azul más oscuro al pasar el ratón
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 123, 255)); // Volver al azul original
            }
        });*/

        return button;
    }

    public void showMenu() {
        if (frame != null) {
            frame.setVisible(true); // Mostrar el menú principal si ya existe
        }
    }
}