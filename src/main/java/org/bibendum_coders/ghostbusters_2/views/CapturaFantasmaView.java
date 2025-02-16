
package org.bibendum_coders.ghostbusters_2.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;
import org.bibendum_coders.ghostbusters_2.models.Clase;
import org.bibendum_coders.ghostbusters_2.models.Peligro;

public class CapturaFantasmaView extends JFrame {
    private CazadorController cazadorController;

    public CapturaFantasmaView(CazadorController cazadorController) {
        this.cazadorController = cazadorController;
        initialize();
    }

    private void initialize() {
        setTitle("Capturar Fantasma");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 500);
        setLayout(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        ImageIcon icon = new ImageIcon("src/main/java/org/bibendum_coders/ghostbusters_2/resources/images/Captura.jpg");
        JLabel fondo = new JLabel(icon);
        fondo.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        setSize(icon.getIconWidth(), icon.getIconHeight());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBounds(200, 200, 600, 250);

        JTextField nombreField = new JTextField();

        String[] clases = new String[Clase.values().length];
        for (int i = 0; i < Clase.values().length; i++) {
            clases[i] = Clase.values()[i].getClaseString();
        }
        JComboBox<String> claseComboBox = new JComboBox<>(clases);

        String[] peligros = new String[Peligro.values().length];
        for (int i = 0; i < Peligro.values().length; i++) {
            peligros[i] = Peligro.values()[i].getPeligroString();
        }
        JComboBox<String> nivelComboBox = new JComboBox<>(peligros);

        JTextField habilidadField = new JTextField();

        inputPanel.add(new JLabel("Nombre del Fantasma:"));
        inputPanel.add(nombreField);
        inputPanel.add(new JLabel("Clase del Fantasma:"));
        inputPanel.add(claseComboBox);
        inputPanel.add(new JLabel("Nivel de Peligro:"));
        inputPanel.add(nivelComboBox);
        inputPanel.add(new JLabel("Habilidad Especial:"));
        inputPanel.add(habilidadField);

        JButton capturarButton = createStyledButton("Capturar Fantasma");
        capturarButton.addActionListener(e -> {
            try {
                String nombre = nombreField.getText().trim();
                int claseIndex = claseComboBox.getSelectedIndex();
                int peligroIndex = nivelComboBox.getSelectedIndex();

                String habilidad = habilidadField.getText().trim();

                if (nombre.isEmpty()) {
                    throw new IllegalArgumentException("El nombre del fantasma no puede estar vacío.");
                }
                if (claseIndex == -1) {
                    throw new IllegalArgumentException("Debes seleccionar una clase válida.");
                }
                if (peligroIndex == -1) {
                    throw new IllegalArgumentException("El nivel de peligro no puede estar vacío.");
                }
                if (habilidad.isEmpty()) {
                    throw new IllegalArgumentException("La habilidad especial no puede estar vacía.");
                }

                boolean capturado = cazadorController.capturarFantasma(nombre, claseIndex + 1, peligroIndex + 1, habilidad);
                String mensaje = capturado ?
                    "Fantasma \"" + nombre + "\" capturado exitosamente." :
                    "¡Vaya! El fantasma \"" + nombre + "\" ha huído porque \n" +
                    "la afinidad no es alta y el arma se ha recalentado.\n" +
                    "Vuelve a intentarlo.";
                mostrarMensajeConfirmacion(
                    mensaje,
                    "Confirmación"
                );

                dispose();
            } catch (IllegalArgumentException ex) {
                mostrarMensajeError(ex.getMessage());
            }
        });

        inputPanel.add(capturarButton);

        JButton volverButton = createStyledButton("Volver");
        volverButton.addActionListener(e -> {
            dispose();
            MenuPrincipalView.getInstance(cazadorController).showMenu();
        });
        inputPanel.add(volverButton);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

        layeredPane.add(fondo, 0);
        layeredPane.add(inputPanel, JLayeredPane.PALETTE_LAYER);

        setContentPane(layeredPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setMargin(new Insets(5, 10, 5, 10));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 123, 255));
            }
        });

        return button;
    }

    private void mostrarMensajeConfirmacion(String mensaje, String titulo) {
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);

        JOptionPane.showMessageDialog(
            this,
            mensaje,
            titulo,
            JOptionPane.INFORMATION_MESSAGE
        );
    }


    private void mostrarMensajeError(String mensaje) {
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);

        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    @Override
    public void dispose() {
        super.dispose();
        MenuPrincipalView.getInstance(cazadorController).showMenu();
    }
}
