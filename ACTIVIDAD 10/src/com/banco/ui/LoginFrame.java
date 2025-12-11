package com.banco.ui;

import com.banco.dao.PersonasDAO;
import com.banco.model.*;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
  private JTextField campoDni;
  private JPasswordField campoContra;
  private PersonasDAO personasDAO;

  public LoginFrame() {
    personasDAO = new PersonasDAO();
    setTitle("Sistema Bancario - Acceso");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    panel.add(new JLabel("DNI:"));
    campoDni = new JTextField();
    panel.add(campoDni);

    panel.add(new JLabel("Contraseña:"));
    campoContra = new JPasswordField();
    panel.add(campoContra);

    JButton botonLogin = new JButton("Ingresar");
    botonLogin.addActionListener(e -> ingresar());
    panel.add(new JLabel(""));
    panel.add(botonLogin);

    add(panel);
  }

  private void ingresar() {
    String dni = campoDni.getText();
    String contra = new String(campoContra.getPassword());

    Persona persona = personasDAO.login(dni, contra);
    if (persona != null) {
      JOptionPane.showMessageDialog(this, "Bienvenido " + persona.getNombre() + " (" + persona.getRol() + ")");
      this.dispose();

      if (persona instanceof Administrador) {
        new AdminDashboard((Administrador) persona).setVisible(true);
      } else if (persona instanceof Empleado) {
        new EmpleadoDashboard((Empleado) persona).setVisible(true);
      } else if (persona instanceof Cliente) {
        new ClienteDashboard((Cliente) persona).setVisible(true);
      }
    } else {
      JOptionPane.showMessageDialog(this, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
