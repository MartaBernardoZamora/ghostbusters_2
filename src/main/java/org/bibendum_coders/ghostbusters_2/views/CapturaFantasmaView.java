package org.bibendum_coders.ghostbusters_2.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;
import org.bibendum_coders.ghostbusters_2.models.Clase;

public class CapturaFantasmaView extends JFrame {
    private CazadorController cazadorController;

    public CapturaFantasmaView(CazadorController cazadorController) {
        this.cazadorController = cazadorController;
        initialize();
    }

    private void initialize() {
        setTitle("Capturar Fantasma");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Desactivar el comportamiento predeterminado
        setSize(800, 500);
        setLayout(null);

        // Agregar un WindowListener para manejar el cierre de la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); // Llamar al método dispose() sobrescrito
            }
        });

        ImageIcon icon = new ImageIcon("src/main/java/org/bibendum_coders/ghostbusters_2/resources/images/ghost.jpg");
        JLabel fondo = new JLabel(icon);
        fondo.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        setSize(icon.getIconWidth(), icon.getIconHeight());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 5, 5)); // Ajustamos el layout para incluir el botón "Volver"
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBounds(250, 200, 600, 250);
        //inputPanel.setBackground(Color.GRAY);

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

        // Botón "Capturar Fantasma"
        JButton capturarButton = createStyledButton("Capturar Fantasma");
        capturarButton.addActionListener(e -> {
            try {
                String nombre = nombreField.getText().trim();
                int claseIndex = claseComboBox.getSelectedIndex();
                String nivel = nivelField.getText().trim();
                String habilidad = habilidadField.getText().trim();

                if (nombre.isEmpty()) {
                    throw new IllegalArgumentException("El nombre del fantasma no puede estar vacío.");
                }
                if (claseIndex == -1) {
                    throw new IllegalArgumentException("Debes seleccionar una clase válida.");
                }
                if (nivel.isEmpty()) {
                    throw new IllegalArgumentException("El nivel de peligro no puede estar vacío.");
                }
                if (habilidad.isEmpty()) {
                    throw new IllegalArgumentException("La habilidad especial no puede estar vacía.");
                }

                boolean capturado = cazadorController.capturarFantasma(nombre, claseIndex + 1, nivel, habilidad);
                String mensaje = capturado ? 
                    "Fantasma \"" + nombre + "\" capturado exitosamente." : 
                    "¡Vaya! El fantasma \"" + nombre + "\" ha huído porque \n" +
                    "la afinidad no es alta y el arma se ha recalentado.\n" +
                    "Vuelve a intentarlo.";
                // Mostrar mensaje de confirmación con estilo limpio
                mostrarMensajeConfirmacion(
                    mensaje, 
                    "Confirmación"
                );

                dispose(); // Cerrar esta ventana
            } catch (IllegalArgumentException ex) {
                // Mostrar mensaje de error con estilo limpio
                mostrarMensajeError(ex.getMessage());
            }
        });

        inputPanel.add(capturarButton);

        // Botón "Volver"
        JButton volverButton = createStyledButton("Volver");
        volverButton.addActionListener(e -> {
            dispose(); // Cerrar esta ventana
            MenuPrincipalView.getInstance(cazadorController).showMenu(); // Reabrir el menú principal
        });
        inputPanel.add(volverButton);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

        layeredPane.add(fondo, 0);
        layeredPane.add(inputPanel, JLayeredPane.PALETTE_LAYER);// Añadir el panel de botones al JLayeredPane con una prioridad predefinida

        setContentPane(layeredPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para crear botones con estilo mejorado (fondo azul, fuente elegante y hover)
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Fuente elegante y tamaño adecuado
        button.setFocusPainted(false); // Eliminar el borde de foco
        button.setBackground(new Color(0, 123, 255)); // Fondo azul
        button.setForeground(Color.WHITE); // Texto blanco
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde negro limpio
        button.setMargin(new Insets(5, 10, 5, 10)); // Espaciado interno uniforme

        // Efecto Hover: Cambiar el color cuando el ratón pasa por encima
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204)); // Azul más oscuro al pasar el ratón
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 123, 255)); // Volver al azul original
            }
        });

        return button;
    }

    // Método para mostrar un mensaje de confirmación con botones estilizados
    private void mostrarMensajeConfirmacion(String mensaje, String titulo) {
        UIManager.put("Button.focus", new Color(0, 0, 0, 0)); // Eliminar el borde de foco (transparente)
        UIManager.put("OptionPane.background", Color.WHITE); // Fondo blanco para el mensaje
        UIManager.put("Panel.background", Color.WHITE); // Fondo blanco para el panel contenedor

        JOptionPane.showMessageDialog(
            this,
            mensaje,
            titulo,
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Método para mostrar un mensaje de error con botones estilizados
    private void mostrarMensajeError(String mensaje) {
        UIManager.put("Button.focus", new Color(0, 0, 0, 0)); // Eliminar el borde de foco (transparente)
        UIManager.put("OptionPane.background", Color.WHITE); // Fondo blanco para el mensaje
        UIManager.put("Panel.background", Color.WHITE); // Fondo blanco para el panel contenedor

        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    @Override
    public void dispose() {
        super.dispose(); // Cerrar esta ventana
        MenuPrincipalView.getInstance(cazadorController).showMenu(); // Reabrir el menú principal
    }
}
