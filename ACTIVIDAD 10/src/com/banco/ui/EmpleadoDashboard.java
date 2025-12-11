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
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    Estilos.aplicarEstiloVentana(this);

    JTabbedPane tabs = new JTabbedPane();
    tabs.setFont(Estilos.FUENTE_SUBTITULO);
    tabs.addTab("Registrar Cliente", crearPanelCliente());
    tabs.addTab("Crear Cuenta", crearPanelCuenta());

    add(tabs);
  }

  private JPanel crearPanelCliente() {
    JPanel panel = new JPanel(new BorderLayout(20, 20)); // Margin
    Estilos.estilizarPanel(panel);
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    panel.add(Estilos.crearTitulo("Registrar Nuevo Cliente"), BorderLayout.NORTH);

    JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
    Estilos.estilizarPanel(formPanel);

    JTextField campoNombre = Estilos.crearCampoTexto();
    JTextField campoApellido = Estilos.crearCampoTexto();
    JTextField campoDni = Estilos.crearCampoTexto();
    JTextField campoCorreo = Estilos.crearCampoTexto();
    JPasswordField campoContra = Estilos.crearCampoContrasena();
    JButton botonGuardar = Estilos.crearBotonPrimario("Registrar Cliente");

    formPanel.add(Estilos.crearLabel("Nombre:"));
    formPanel.add(campoNombre);
    formPanel.add(Estilos.crearLabel("Apellido:"));
    formPanel.add(campoApellido);
    formPanel.add(Estilos.crearLabel("DNI:"));
    formPanel.add(campoDni);
    formPanel.add(Estilos.crearLabel("Correo:"));
    formPanel.add(campoCorreo);
    formPanel.add(Estilos.crearLabel("Contraseña:"));
    formPanel.add(campoContra);
    formPanel.add(new JLabel(""));

    // Centrar el boton
    JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    Estilos.estilizarPanel(botonPanel);
    botonPanel.add(botonGuardar);

    formPanel.add(botonPanel); // Add wrapper to grid? No, put it outside

    JPanel centerWrapper = new JPanel(new BorderLayout());
    Estilos.estilizarPanel(centerWrapper);
    centerWrapper.add(formPanel, BorderLayout.NORTH);
    centerWrapper.add(botonPanel, BorderLayout.CENTER);

    panel.add(centerWrapper, BorderLayout.CENTER);

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
    JPanel panel = new JPanel(new BorderLayout(20, 20));
    Estilos.estilizarPanel(panel);
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    panel.add(Estilos.crearTitulo("Apertura de Cuenta"), BorderLayout.NORTH);

    JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
    Estilos.estilizarPanel(formPanel);

    JTextField campoDniCliente = Estilos.crearCampoTexto();
    JTextField campoNumCuenta = Estilos.crearCampoTexto();
    JTextField campoSaldo = Estilos.crearCampoTexto();
    JComboBox<String> comboTipo = new JComboBox<>(new String[] { "AHORRO", "CORRIENTE" });
    comboTipo.setFont(Estilos.FUENTE_TEXTO);
    comboTipo.setBackground(Color.WHITE);

    JButton botonCrear = Estilos.crearBotonPrimario("Crear Cuenta");

    formPanel.add(Estilos.crearLabel("DNI Cliente:"));
    formPanel.add(campoDniCliente);
    formPanel.add(Estilos.crearLabel("No. Cuenta:"));
    formPanel.add(campoNumCuenta);
    formPanel.add(Estilos.crearLabel("Saldo Inicial:"));
    formPanel.add(campoSaldo);
    formPanel.add(Estilos.crearLabel("Tipo:"));
    formPanel.add(comboTipo);

    JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    Estilos.estilizarPanel(botonPanel);
    botonPanel.add(botonCrear);

    JPanel centerWrapper = new JPanel(new BorderLayout());
    Estilos.estilizarPanel(centerWrapper);
    centerWrapper.add(formPanel, BorderLayout.NORTH);
    centerWrapper.add(botonPanel, BorderLayout.CENTER);

    panel.add(centerWrapper, BorderLayout.CENTER);

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
