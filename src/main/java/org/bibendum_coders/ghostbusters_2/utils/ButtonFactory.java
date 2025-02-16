package org.bibendum_coders.ghostbusters_2.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonFactory {

    // Método para crear botones estilizados
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Fuente elegante y tamaño adecuado
        button.setFocusPainted(false); // Eliminar el borde de foco
        button.setBackground(new Color(0, 123, 255)); // Fondo azul
        button.setForeground(Color.WHITE); // Texto blanco
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde negro limpio
        button.setMargin(new Insets(5, 10, 5, 10)); // Espaciado interno uniforme

        // Efecto Hover: Cambiar el color cuando el ratón pasa por encima
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204)); // Azul más oscuro al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(0, 123, 255)); // Volver al azul original
            }
        });

        return button;
    }
}