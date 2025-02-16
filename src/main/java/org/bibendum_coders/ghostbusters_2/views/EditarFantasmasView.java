package org.bibendum_coders.ghostbusters_2.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;
import org.bibendum_coders.ghostbusters_2.models.FantasmaModel;

public class EditarFantasmasView extends JFrame {
    private CazadorController cazadorController;
    private List<FantasmaModel> fantasmas;
    private JPanel contentPanel; // Referencia al panel de contenido

    public EditarFantasmasView(CazadorController cazadorController, List<FantasmaModel> fantasmas) {
        this.fantasmas = fantasmas;
        this.cazadorController = cazadorController;
        initialize();
    }

    private void initialize() {
        setTitle("Editar Fantasmas");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Desactivar el comportamiento predeterminado
        setSize(800, 500);
        //setLayout(new BorderLayout());
        setLayout(null);

        // Agregar un WindowListener para manejar el cierre de la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); // Llamar al método dispose() sobrescrito
            }
        });
   // Imagen de fondo
   ImageIcon icon = new ImageIcon("src/main/java/org/bibendum_coders/ghostbusters_2/resources/images/ghost.jpg");
   JLabel fondo = new JLabel(icon);
   fondo.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
   setSize(icon.getIconWidth(), icon.getIconHeight());
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); 
        panel.setBounds(100, 100, 600, 250);
        // Encabezados de la tabla
        JPanel headerPanel = new JPanel(new GridLayout(1, 6));
        headerPanel.add(createHeaderLabel("ID"));
        headerPanel.add(createHeaderLabel("Nombre"));
        headerPanel.add(createHeaderLabel("Clase"));
        headerPanel.add(createHeaderLabel("Nivel"));
        headerPanel.add(createHeaderLabel("Habilidad"));
        headerPanel.add(createHeaderLabel("Acción"));
        panel.add(headerPanel, BorderLayout.NORTH);

        // Contenido de la tabla
        contentPanel = new JPanel(); // Almacenar referencia al panel de contenido
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Construir dinámicamente la lista de fantasmas
        buildFantasmaList();

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botón "Volver"
        JButton volverButton = createStyledButton("Volver");
        volverButton.addActionListener(e -> {
            dispose(); // Cerrar esta ventana
            MenuPrincipalView.getInstance(cazadorController).showMenu(); // Reabrir el menú principal
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(volverButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

       // add(panel, BorderLayout.CENTER);//
       JLayeredPane layeredPane = new JLayeredPane();
       layeredPane.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
   
       layeredPane.add(fondo, 0);
       layeredPane.add(panel, JLayeredPane.PALETTE_LAYER);// Añadir el panel de botones al JLayeredPane con una prioridad predefinida
   
       setContentPane(layeredPane);
        setVisible(true);
    }

    // Método para construir dinámicamente la lista de fantasmas
    private void buildFantasmaList() {
        contentPanel.removeAll(); // Limpiar el panel antes de reconstruirlo

        if (fantasmas.isEmpty()) {
            JLabel mensaje = new JLabel("No hay fantasmas capturados.");
            mensaje.setFont(new Font("Arial", Font.BOLD, 16));
            mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(mensaje);
        } else {
            for (int i = 0; i < fantasmas.size(); i++) {
                FantasmaModel fantasma = fantasmas.get(i);

                JPanel rowPanel = new JPanel(new GridLayout(1, 6)); // Una fila por fantasma
                if (i < fantasmas.size() - 1) { // Agregar borde inferior excepto en la última fila
                    rowPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
                }

                // Añadir cada celda con contenido centrado y bordes verticales
                rowPanel.add(createCenteredPanel(String.valueOf(fantasma.getId()), true)); // ID
                rowPanel.add(createCenteredPanel(fantasma.getNombre(), true)); // Nombre
                rowPanel.add(createCenteredPanel(fantasma.getClase().name(), true)); // Clase
                rowPanel.add(createCenteredPanel(fantasma.getNivelPeligro(), true)); // Nivel
                rowPanel.add(createCenteredPanel(fantasma.getHabilidad(), true)); // Habilidad

                // Panel para el botón "Liberar" (sin borde derecho)
                JPanel actionPanel = createCenteredPanel("", false); // Sin texto inicial
                JButton liberarButton = createActionButton("Liberar", fantasma.getId());
                actionPanel.add(liberarButton); // Añadir el botón al panel
                rowPanel.add(actionPanel); // Añadir el panel a la fila

                contentPanel.add(rowPanel);
            }
        }

        contentPanel.revalidate(); // Actualizar el panel
        contentPanel.repaint(); // Repintar el panel
        revalidate(); // Actualizar la ventana
        repaint(); // Repintar la ventana
    }

    private JLabel createHeaderLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }

    private JPanel createCenteredPanel(String text, boolean withRightBorder) {
        JPanel panel = new JPanel(new GridBagLayout()); // Centrar el contenido
        panel.setOpaque(false); // Hacer el panel transparente para evitar cuadros extraños

        if (!text.isEmpty()) {
            JLabel label = new JLabel(text, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 12));
            panel.add(label); // Añadir el texto al panel
        }

        // Agregar bordes: izquierdo y derecho (opcional)
        if (withRightBorder) {
            panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY)); // Borde derecho
        }
        return panel;
    }

    private JButton createActionButton(String text, int idFantasma) {
        JButton button = createStyledButton(text);
        button.setPreferredSize(new Dimension(80, 25)); // Tamaño fijo para el botón
        button.addActionListener(e -> {
            cazadorController.liberarFantasma(idFantasma); // Liberar el fantasma por su ID
            
            // Mostrar mensaje de confirmación con estilo limpio
            mostrarMensajeConfirmacion(
                "Fantasma con ID " + idFantasma + " liberado exitosamente.",
                "Confirmación"
            );
            
            // Actualizar la lista de fantasmas
            fantasmas = cazadorController.getCazadorModel().getFantasmas(); // Obtener la lista actualizada
            buildFantasmaList(); // Reconstruir la lista
        });
        return button;
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

    @Override
    public void dispose() {
        super.dispose(); // Cerrar esta ventana
        MenuPrincipalView.getInstance(cazadorController).showMenu(); // Reabrir el menú principal
    }
}