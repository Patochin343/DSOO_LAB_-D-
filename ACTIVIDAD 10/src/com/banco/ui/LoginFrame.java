package com.banco.ui;

import com.banco.dao.PersonasDAO;
import com.banco.model.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {
  private JTextField campoDni;
  private JPasswordField campoContra;
  private PersonasDAO personasDAO;

  public LoginFrame() {
    personasDAO = new PersonasDAO();
    setTitle("Banco - Acceso Seguro");
    setSize(450, 550); // MÃ¡s alto para mejor espaciado
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    Estilos.aplicarEstiloVentana(this);

    // Panel Principal con Cards para centrar
    JPanel mainPanel = new JPanel(new GridBagLayout());
    Estilos.estilizarPanel(mainPanel);

    // Panel de Contenido (la "tarjeta" blanca)
    JPanel cardPanel = new JPanel();
    cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
    cardPanel.setBackground(Color.WHITE);
    cardPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
        new EmptyBorder(40, 40, 40, 40)));

    // Logo / Titulo
    JLabel titulo = Estilos.crearTitulo("Bienvenido");
    titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel subtitulo = new JLabel("Inicia sesión en tu Banco");
    subtitulo.setFont(Estilos.FUENTE_TEXTO);
    subtitulo.setForeground(Color.GRAY);
    subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Campos
    JLabel labelDni = Estilos.crearLabel("DNI / Usuario");
    labelDni.setAlignmentX(Component.LEFT_ALIGNMENT);

    campoDni = Estilos.crearCampoTexto();
    campoDni.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

    JLabel labelContra = Estilos.crearLabel("Contraseña");
    labelContra.setAlignmentX(Component.LEFT_ALIGNMENT);

    campoContra = Estilos.crearCampoContrasena();
    campoContra.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

    // Boton
    JButton botonLogin = Estilos.crearBotonPrimario("INGRESAR");
    botonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
    botonLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
    botonLogin.addActionListener(e -> ingresar());

    // Espaciadores
    cardPanel.add(titulo);
    cardPanel.add(Box.createVerticalStrut(10));
    cardPanel.add(subtitulo);
    cardPanel.add(Box.createVerticalStrut(40));

    cardPanel.add(labelDni);
    cardPanel.add(Box.createVerticalStrut(5));
    cardPanel.add(campoDni);
    cardPanel.add(Box.createVerticalStrut(20));

    cardPanel.add(labelContra);
    cardPanel.add(Box.createVerticalStrut(5));
    cardPanel.add(campoContra);
    cardPanel.add(Box.createVerticalStrut(40));

    cardPanel.add(botonLogin);

    mainPanel.add(cardPanel);
    add(mainPanel);
  }

  private void ingresar() {
    String dni = campoDni.getText();
    String contra = new String(campoContra.getPassword());

    Persona persona = personasDAO.login(dni, contra);
    if (persona != null) {
      // JOptionPane.showMessageDialog(this, "Bienvenido " + persona.getNombre()); //
      // Menos intrusivo
      this.dispose();

      if (persona instanceof Administrador) {
        new AdminDashboard((Administrador) persona).setVisible(true);
      } else if (persona instanceof Empleado) {
        new EmpleadoDashboard((Empleado) persona).setVisible(true);
      } else if (persona instanceof Cliente) {
        new ClienteDashboard((Cliente) persona).setVisible(true);
      }
    } else {
      JOptionPane.showMessageDialog(this, "Credenciales inválidas, por favor intente nuevamente.", "Error de Acceso",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
