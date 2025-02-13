package org.bibendum_coders.ghostbusters_2.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.bibendum_coders.ghostbusters_2.controllers.CazadorController;
import org.bibendum_coders.ghostbusters_2.models.FantasmaModel;

public class EditarFantasmasView {
    private JFrame frame;
    private CazadorController cazadorController;
        private List<FantasmaModel>  fantasmas;
    
        public EditarFantasmasView(CazadorController cazadorController, List<FantasmaModel> fantasmas) {
            this.fantasmas = fantasmas;
            this.cazadorController = cazadorController;
            initialize();
        }
    
        private void initialize() {
            frame = new JFrame("Editar Fantasmas");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 400);
            frame.setLayout(new BorderLayout());
    
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
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
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    
            if (cazadorController.getCazadorModel().getFantasmas().isEmpty()) {
                JLabel mensaje = new JLabel("No hay fantasmas capturados.");
                mensaje.setFont(new Font("Arial", Font.BOLD, 16));
                mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
                contentPanel.add(mensaje);
            } else {
                for (FantasmaModel fantasma : fantasmas) {
                JPanel rowPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.weightx = 1.0;

                gbc.gridx = 0;
                gbc.weightx = 0.1;
                rowPanel.add(createTableCell(String.valueOf(fantasma.getId())), gbc);

                gbc.gridx = 1;
                gbc.weightx = 0.2;
                rowPanel.add(createTableCell(fantasma.getNombre()), gbc);

                gbc.gridx = 2;
                gbc.weightx = 0.2;
                rowPanel.add(createTableCell(fantasma.getClase().name()), gbc);

                gbc.gridx = 3;
                gbc.weightx = 0.2;
                rowPanel.add(createTableCell(fantasma.getNivelPeligro()), gbc);

                gbc.gridx = 4;
                gbc.weightx = 0.2;
                rowPanel.add(createTableCell(fantasma.getHabilidad()), gbc);

                gbc.gridx = 5;
                gbc.weightx = 0.1;
                JButton liberarButton = createActionButton("Liberar", fantasma.getId());
                rowPanel.add(liberarButton, gbc);

                contentPanel.add(rowPanel);
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JLabel createHeaderLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }

    private JLabel createTableCell(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        return label;
    }

    private JButton createActionButton(String text, int idFantasma) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(80, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cazadorController.liberarFantasma(idFantasma); // Liberar el fantasma por su ID
                JOptionPane.showMessageDialog(frame, "Fantasma con ID " + idFantasma + " liberado exitosamente.");

                // Refrescar la vista
                frame.dispose();
                new EditarFantasmasView(cazadorController, fantasmas);
            }
        });
        return button;
    }
}