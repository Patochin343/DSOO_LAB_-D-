package com.banco.ui;

import com.banco.dao.ClienteDAO;
import com.banco.dao.PersonasDAO;
import com.banco.dao.CuentaDAO;
import com.banco.model.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EmpleadoDashboard extends JFrame {
  private Empleado empleado;
  private ClienteDAO clienteDAO;
  private CuentaDAO cuentaDAO;
  private PersonasDAO personasDAO;

  public EmpleadoDashboard(Empleado empleado) {
    this.empleado = empleado;
    this.clienteDAO = new ClienteDAO();
    this.cuentaDAO = new CuentaDAO();
    this.personasDAO = new PersonasDAO();

    setTitle("Dashboard Empleado - " + empleado.getNombre());
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JTabbedPane tabs = new JTabbedPane();
    tabs.addTab("Registrar Cliente", crearPanelCliente());
    tabs.addTab("Crear Cuenta", crearPanelCuenta());

    add(tabs);
  }

  private JPanel crearPanelCliente() {
    JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

    JTextField campoNombre = new JTextField();
    JTextField campoApellido = new JTextField();
    JTextField campoDni = new JTextField();
    JTextField campoCorreo = new JTextField();
    JPasswordField campoContra = new JPasswordField();
    JButton botonGuardar = new JButton("Registrar Cliente");

    panel.add(new JLabel("Nombre:"));
    panel.add(campoNombre);
    panel.add(new JLabel("Apellido:"));
    panel.add(campoApellido);
    panel.add(new JLabel("DNI:"));
    panel.add(campoDni);
    panel.add(new JLabel("Correo:"));
    panel.add(campoCorreo);
    panel.add(new JLabel("Contraseña:"));
    panel.add(campoContra);
    panel.add(new JLabel(""));
    panel.add(botonGuardar);

    botonGuardar.addActionListener(e -> {
      Cliente cliente = new Cliente(campoNombre.getText(), campoApellido.getText(), campoDni.getText(),
          campoCorreo.getText(), new String(campoContra.getPassword()));
      if (clienteDAO.registrar(cliente)) {
        JOptionPane.showMessageDialog(this, "Cliente registrado con éxito");
        campoNombre.setText("");
        campoApellido.setText("");
        campoDni.setText("");
        campoCorreo.setText("");
        campoContra.setText("");
      } else {
        JOptionPane.showMessageDialog(this, "Error al registrar");
      }
    });

    return panel;
  }

  private JPanel crearPanelCuenta() {
    JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

    JTextField campoDniCliente = new JTextField();
    JTextField campoNumCuenta = new JTextField();
    JTextField campoSaldo = new JTextField();
    JComboBox<String> comboTipo = new JComboBox<>(new String[] { "AHORRO", "CORRIENTE" });
    JButton botonCrear = new JButton("Crear Cuenta");

    panel.add(new JLabel("DNI Cliente:"));
    panel.add(campoDniCliente);
    panel.add(new JLabel("No. Cuenta:"));
    panel.add(campoNumCuenta);
    panel.add(new JLabel("Saldo Inicial:"));
    panel.add(campoSaldo);
    panel.add(new JLabel("Tipo:"));
    panel.add(comboTipo);
    panel.add(new JLabel(""));
    panel.add(botonCrear);

    botonCrear.addActionListener(e -> {
      String dni = campoDniCliente.getText();

      int idCliente = -1;
      // Inefficient but functional for now
      for (Cliente cliente : clienteDAO.listar()) {
        if (cliente.getDni().equals(dni)) {
          idCliente = cliente.getId();
          break;
        }
      }

      if (idCliente != -1) {
        try {
          double saldo = Double.parseDouble(campoSaldo.getText());
          Cuenta cuenta = new Cuenta(campoNumCuenta.getText(), saldo, (String) comboTipo.getSelectedItem());
          if (cuentaDAO.crear(cuenta, idCliente)) {
            JOptionPane.showMessageDialog(this, "Cuenta creada exitosamente");
            campoDniCliente.setText("");
            campoNumCuenta.setText("");
            campoSaldo.setText("");
          } else {
            JOptionPane.showMessageDialog(this, "Error al crear cuenta");
          }
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(this, "Saldo inválido");
        }
      } else {
        JOptionPane.showMessageDialog(this, "Cliente no encontrado con ese DNI");
      }
    });

    return panel;
  }
}
