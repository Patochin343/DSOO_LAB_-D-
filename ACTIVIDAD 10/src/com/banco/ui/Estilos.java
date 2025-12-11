package com.banco.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Estilos {
  // Paleta de Colores
  public static final Color COLOR_PRIMARIO = new Color(13, 71, 161); // Azul Oscuro Premium
  public static final Color COLOR_SECUNDARIO = new Color(25, 118, 210); // Azul Intermedio
  public static final Color COLOR_FONDO = new Color(245, 245, 245); // Gris muy claro
  public static final Color COLOR_TEXTO = new Color(33, 33, 33); // Negro suave
  public static final Color COLOR_BLANCO = Color.WHITE;
  public static final Color COLOR_ACCENTO = new Color(255, 193, 7); // Amber/Gold para botones de acción secundarios

  // Fuentes
  public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 24);
  public static final Font FUENTE_SUBTITULO = new Font("Segoe UI", Font.BOLD, 18);
  public static final Font FUENTE_TEXTO = new Font("Segoe UI", Font.PLAIN, 14);
  public static final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 14);

  public static void aplicarEstiloVentana(JFrame frame) {
    frame.getContentPane().setBackground(COLOR_FONDO);
    // Intentar poner icono si existiera (no lo tenemos, pero dejamos el background
    // listo)
  }

  public static void estilizarPanel(JPanel panel) {
    panel.setBackground(COLOR_FONDO);
  }

  public static JLabel crearTitulo(String texto) {
    JLabel label = new JLabel(texto, SwingConstants.CENTER);
    label.setFont(FUENTE_TITULO);
    label.setForeground(COLOR_PRIMARIO);
    return label;
  }

  public static JLabel crearLabel(String texto) {
    JLabel label = new JLabel(texto);
    label.setFont(FUENTE_TEXTO);
    label.setForeground(COLOR_TEXTO);
    return label;
  }

  public static JButton crearBotonPrimario(String texto) {
    JButton boton = new JButton(texto) {
      @Override
      protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isPressed()) {
          g2.setColor(COLOR_PRIMARIO.darker());
        } else if (getModel().isRollover()) {
          g2.setColor(COLOR_PRIMARIO.brighter());
        } else {
          g2.setColor(COLOR_PRIMARIO);
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2.dispose();
        super.paintComponent(g);
      }
    };
    boton.setFont(FUENTE_BOTON);
    boton.setForeground(COLOR_BLANCO);
    boton.setFocusPainted(false);
    boton.setBorderPainted(false);
    boton.setContentAreaFilled(false);
    boton.setBorder(new EmptyBorder(10, 20, 10, 20));
    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    return boton;
  }

  public static JTextField crearCampoTexto() {
    JTextField campo = new JTextField();
    campo.setFont(FUENTE_TEXTO);
    campo.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(COLOR_SECUNDARIO, 1),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    return campo;
  }

  public static JPasswordField crearCampoContrasena() {
    JPasswordField campo = new JPasswordField();
    campo.setFont(FUENTE_TEXTO);
    campo.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(COLOR_SECUNDARIO, 1),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    return campo;
  }
}
