package org.bibendum_coders.ghostbusters_2.views;

import javax.swing.*;
import java.awt.*;
import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;

public class MenuPrincipalView {
    private static MenuPrincipalView instance;
    private JFrame frame;
    private CazadorController cazadorController;

    private MenuPrincipalView(CazadorController cazadorController) {
        this.cazadorController = cazadorController;
        initialize();
    }

    public static MenuPrincipalView getInstance(CazadorController cazadorController) {
        if (instance == null || !instance.frame.isVisible()) {
            instance = new MenuPrincipalView(cazadorController);
        }
        return instance;
    }

    private void initialize() {
        frame = new JFrame("Menú Principal - Cazador de Fantasmas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        frame.setLayout(null); 

        ImageIcon icon = new ImageIcon("src/main/java/org/bibendum_coders/ghostbusters_2/resources/images/ghost.jpg");
        JLabel fondo = new JLabel(icon);
        fondo.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        frame.setSize(icon.getIconWidth(), icon.getIconHeight());
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setOpaque(false); 
        panelBotones.setBounds(100, 100, 250, 250);
    
        JButton capturarButton = createStyledButton("Capturar Fantasma");
        capturarButton.addActionListener(e -> {
            frame.setVisible(false);
            new CapturaFantasmaView(cazadorController);
        });
        JButton editarButton = createStyledButton("Editar Fantasmas");
        editarButton.addActionListener(e -> {
            frame.setVisible(false);
            new EditarFantasmasView(
                cazadorController,
                cazadorController.getCazadorModel().getFantasmas()
            );
        });
        JButton salirButton = createStyledButton("Salir");
        salirButton.addActionListener(e -> System.exit(0));
    
        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(capturarButton);
        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(editarButton);
        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(salirButton);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
    
        layeredPane.add(fondo, 0);
        layeredPane.add(panelBotones, JLayeredPane.PALETTE_LAYER);
    
        frame.setContentPane(layeredPane);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(250, 60));
        button.setMaximumSize(new Dimension(250, 60));

        return button;
    }
    public void showMenu() {
        if (frame != null) {
            frame.setVisible(true);
        }
    }
}