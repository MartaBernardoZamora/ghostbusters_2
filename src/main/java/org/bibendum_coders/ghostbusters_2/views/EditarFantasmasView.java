package org.bibendum_coders.ghostbusters_2.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;
import org.bibendum_coders.ghostbusters_2.models.FantasmaModel;

public class EditarFantasmasView extends JFrame {
    private CazadorController cazadorController;
    private List<FantasmaModel> fantasmas;
    private JPanel contentPanel;

    public EditarFantasmasView(CazadorController cazadorController, List<FantasmaModel> fantasmas) {
        this.fantasmas = fantasmas;
        this.cazadorController = cazadorController;
        initialize();
    }

    private void initialize() {
        setTitle("Editar Fantasmas");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 500);
        setLayout(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

   ImageIcon icon = new ImageIcon("src/main/java/org/bibendum_coders/ghostbusters_2/resources/images/Editar.jpg");
   JLabel fondo = new JLabel(icon);
   fondo.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
   setSize(icon.getIconWidth(), icon.getIconHeight());
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setOpaque(false);
        panel.setBounds(100, 200, 800, 250);

        JPanel headerPanel = new JPanel(new GridLayout(1, 6));
        headerPanel.add(createHeaderLabel("ID"));
        headerPanel.add(createHeaderLabel("Nombre"));
        headerPanel.add(createHeaderLabel("Clase"));
        headerPanel.add(createHeaderLabel("Nivel"));
        headerPanel.add(createHeaderLabel("Habilidad"));
        headerPanel.add(createHeaderLabel("Fecha de captura"));
        headerPanel.add(createHeaderLabel("Afinidad ectoplasmica"));
        headerPanel.add(createHeaderLabel("Acción"));
        panel.add(headerPanel, BorderLayout.NORTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        buildFantasmaList();

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton volverButton = createStyledButton("Volver");
        volverButton.addActionListener(e -> {
            dispose();
            MenuPrincipalView.getInstance(cazadorController).showMenu();
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(volverButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

        layeredPane.add(fondo, 0);
        layeredPane.add(panel, JLayeredPane.PALETTE_LAYER);

        setContentPane(layeredPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buildFantasmaList() {
        contentPanel.removeAll();

        if (fantasmas.isEmpty()) {
            JLabel mensaje = new JLabel("No hay fantasmas capturados.");
            mensaje.setFont(new Font("Arial", Font.BOLD, 16));
            mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(mensaje);
        } else {
            for (int i = 0; i < fantasmas.size(); i++) {
                FantasmaModel fantasma = fantasmas.get(i);

                JPanel rowPanel = new JPanel(new GridLayout(1, 6));
                if (i < fantasmas.size() - 1) {
                    rowPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
                }

                DateTimeFormatter formatear = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fecha = fantasma.getFechaCaptura().format(formatear);
                rowPanel.add(createCenteredPanel(String.valueOf(fantasma.getId()), true));
                rowPanel.add(createCenteredPanel(fantasma.getNombre(), true));
                rowPanel.add(createCenteredPanel(fantasma.getClase().name(), true));
                rowPanel.add(createCenteredPanel(String.valueOf(fantasma.getNivelPeligro()), true));
                rowPanel.add(createCenteredPanel(fantasma.getHabilidad(), true));
                rowPanel.add(createCenteredPanel(fecha, true));
                rowPanel.add(createCenteredPanel(String.valueOf(fantasma.getAfinity())+"/10", true));

                JPanel actionPanel = createCenteredPanel("", false);
                JButton liberarButton = createActionButton("Liberar", fantasma.getId());
                actionPanel.add(liberarButton);
                rowPanel.add(actionPanel);

                contentPanel.add(rowPanel);
            }
        }

        contentPanel.revalidate();
        contentPanel.repaint();
        revalidate();
        repaint();
    }

    private JLabel createHeaderLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }

    private JPanel createCenteredPanel(String text, boolean withRightBorder) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        if (!text.isEmpty()) {
            JLabel label = new JLabel(text, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 12));
            panel.add(label);
        }

        if (withRightBorder) {
            panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));
        }
        return panel;
    }

    private JButton createActionButton(String text, int idFantasma) {
        JButton button = createStyledButton(text);
        button.setPreferredSize(new Dimension(80, 25));
        button.addActionListener(e -> {
            cazadorController.liberarFantasma(idFantasma);

            mostrarMensajeConfirmacion(
                "Fantasma con ID " + idFantasma + " liberado exitosamente.",
                "Confirmación"
            );

            fantasmas = cazadorController.getCazadorModel().getFantasmas();
            buildFantasmaList();
        });
        return button;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setMargin(new Insets(5, 10, 5, 10));

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

    @Override
    public void dispose() {
        super.dispose();
        MenuPrincipalView.getInstance(cazadorController).showMenu();
    }
}
