package com.banco.ui;

import com.banco.dao.ClienteDAO;
import com.banco.dao.EmpleadoDAO;
import com.banco.model.Administrador;
import com.banco.model.Cliente;
import com.banco.model.Empleado;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.banco.ui.Estilos;

public class AdminDashboard extends JFrame {
  private Administrador admin;
  private ClienteDAO clienteDAO;
  private EmpleadoDAO empleadoDAO;

  public AdminDashboard(Administrador admin) {
    this.admin = admin;
    this.clienteDAO = new ClienteDAO();
    this.empleadoDAO = new EmpleadoDAO();

    setTitle("Panel Administrador - " + admin.getNombre());
    setSize(1000, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    Estilos.aplicarEstiloVentana(this);

    JTabbedPane tabs = new JTabbedPane();
    tabs.setFont(Estilos.FUENTE_SUBTITULO);
    tabs.addTab("Gestionar Empleados", crearPanelGestionEmpleados());
    tabs.addTab("Gestionar Clientes", crearPanelGestionClientes());

    add(tabs);
  }

  private JPanel crearPanelGestionEmpleados() {
    JPanel panel = new JPanel(new BorderLayout(20, 20));
    Estilos.estilizarPanel(panel);
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Titulo
    panel.add(Estilos.crearTitulo("Gestión de Empleados"), BorderLayout.NORTH);

    // Tabla
    String[] vars = { "ID", "Nombre", "Apellido", "DNI", "Correo" };
    DefaultTableModel tableModel = new DefaultTableModel(vars, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    JTable table = new JTable(tableModel);
    table.setFont(Estilos.FUENTE_TEXTO);
    table.setRowHeight(25);
    table.getTableHeader().setFont(Estilos.FUENTE_BOTON);
    table.getTableHeader().setBackground(Estilos.COLOR_SECUNDARIO);
    table.getTableHeader().setForeground(Color.WHITE);
    panel.add(new JScrollPane(table), BorderLayout.CENTER);

    // Formulario
    JPanel formPanel = new JPanel(new GridLayout(3, 4, 15, 15)); // Grid mas ancho
    Estilos.estilizarPanel(formPanel);

    JTextField txtNombre = Estilos.crearCampoTexto();
    JTextField txtApellido = Estilos.crearCampoTexto();
    JTextField txtDni = Estilos.crearCampoTexto();
    JTextField txtCorreo = Estilos.crearCampoTexto();
    JPasswordField txtContra = Estilos.crearCampoContrasena();
    JTextField txtId = new JTextField();
    txtId.setEditable(false);
    txtId.setVisible(false);

    formPanel.add(Estilos.crearLabel("Nombre:"));
    formPanel.add(txtNombre);
    formPanel.add(Estilos.crearLabel("Apellido:"));
    formPanel.add(txtApellido);
    formPanel.add(Estilos.crearLabel("DNI:"));
    formPanel.add(txtDni);
    formPanel.add(Estilos.crearLabel("Correo:"));
    formPanel.add(txtCorreo);
    formPanel.add(Estilos.crearLabel("Nueva Contraseña:"));
    formPanel.add(txtContra);

    // Botones
    JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    Estilos.estilizarPanel(btnPanel);

    JButton btnCrear = Estilos.crearBotonPrimario("Crear");
    JButton btnActualizar = Estilos.crearBotonPrimario("Actualizar");
    JButton btnEliminar = Estilos.crearBotonPrimario("Eliminar");
    btnEliminar.setBackground(new Color(211, 47, 47)); // Rojo para eliminar
    JButton btnLimpiar = Estilos.crearBotonPrimario("Limpiar");
    btnLimpiar.setBackground(Color.GRAY);

    btnPanel.add(btnCrear);
    btnPanel.add(btnActualizar);
    btnPanel.add(btnEliminar);
    btnPanel.add(btnLimpiar);

    JPanel surPanel = new JPanel(new BorderLayout());
    Estilos.estilizarPanel(surPanel);
    surPanel.add(formPanel, BorderLayout.CENTER);
    surPanel.add(btnPanel, BorderLayout.SOUTH);

    panel.add(surPanel, BorderLayout.SOUTH);

    // Lógica (sin cambios funcionales importantes, solo replicando)
    Runnable cargarDatos = () -> {
      tableModel.setRowCount(0);
      List<Empleado> lista = empleadoDAO.listar();
      for (Empleado e : lista) {
        tableModel.addRow(new Object[] { e.getId(), e.getNombre(), e.getApellido(), e.getDni(), e.getCorreo() });
      }
    };

    cargarDatos.run();

    table.getSelectionModel().addListSelectionListener(e -> {
      int row = table.getSelectedRow();
      if (row != -1) {
        txtId.setText(tableModel.getValueAt(row, 0).toString());
        txtNombre.setText(tableModel.getValueAt(row, 1).toString());
        txtApellido.setText(tableModel.getValueAt(row, 2).toString());
        txtDni.setText(tableModel.getValueAt(row, 3).toString());
        txtCorreo.setText(tableModel.getValueAt(row, 4).toString());
      }
    });

    btnLimpiar.addActionListener(e -> {
      txtId.setText("");
      txtNombre.setText("");
      txtApellido.setText("");
      txtDni.setText("");
      txtCorreo.setText("");
      txtContra.setText("");
      table.clearSelection();
    });

    btnCrear.addActionListener(e -> {
      if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty() ||
          txtDni.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos (Nombre, Apellido, DNI, Correo) son obligatorios.");
        return;
      }
      Empleado emp = new Empleado(txtNombre.getText(), txtApellido.getText(), txtDni.getText(), txtCorreo.getText(),
          new String(txtContra.getPassword()));
      if (empleadoDAO.registrar(emp)) {
        JOptionPane.showMessageDialog(this, "Empleado creado");
        cargarDatos.run();
        btnLimpiar.doClick();
      } else {
        JOptionPane.showMessageDialog(this, "Error al crear");
      }
    });

    btnActualizar.addActionListener(e -> {
      String idStr = txtId.getText();
      if (idStr.isEmpty())
        return;
      if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty() ||
          txtDni.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
        return;
      }
      int id = Integer.parseInt(idStr);
      String pass = new String(txtContra.getPassword());
      if (pass.isEmpty())
        pass = "banco123";
      Empleado emp = new Empleado(id, txtNombre.getText(), txtApellido.getText(), txtDni.getText(), txtCorreo.getText(),
          pass);
      if (empleadoDAO.actualizar(emp)) {
        JOptionPane.showMessageDialog(this, "Empleado actualizado");
        cargarDatos.run();
        btnLimpiar.doClick();
      } else {
        JOptionPane.showMessageDialog(this, "Error al actualizar");
      }
    });

    btnEliminar.addActionListener(e -> {
      String idStr = txtId.getText();
      if (idStr.isEmpty())
        return;
      if (JOptionPane.showConfirmDialog(this, "¿Seguro?") == JOptionPane.YES_OPTION) {
        if (empleadoDAO.eliminar(Integer.parseInt(idStr))) {
          JOptionPane.showMessageDialog(this, "Empleado eliminado");
          cargarDatos.run();
          btnLimpiar.doClick();
        } else {
          JOptionPane.showMessageDialog(this, "Error al eliminar");
        }
      }
    });

    return panel;
  }

  private JPanel crearPanelGestionClientes() {
    JPanel panel = new JPanel(new BorderLayout(20, 20));
    Estilos.estilizarPanel(panel);
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    panel.add(Estilos.crearTitulo("Gestión de Clientes"), BorderLayout.NORTH);

    String[] vars = { "ID", "Nombre", "Apellido", "DNI", "Correo" };
    DefaultTableModel tableModel = new DefaultTableModel(vars, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    JTable table = new JTable(tableModel);
    table.setFont(Estilos.FUENTE_TEXTO);
    table.setRowHeight(25);
    table.getTableHeader().setFont(Estilos.FUENTE_BOTON);
    table.getTableHeader().setBackground(Estilos.COLOR_SECUNDARIO);
    table.getTableHeader().setForeground(Color.WHITE);
    panel.add(new JScrollPane(table), BorderLayout.CENTER);

    JPanel formPanel = new JPanel(new GridLayout(3, 4, 15, 15));
    Estilos.estilizarPanel(formPanel);

    JTextField txtNombre = Estilos.crearCampoTexto();
    JTextField txtApellido = Estilos.crearCampoTexto();
    JTextField txtDni = Estilos.crearCampoTexto();
    JTextField txtCorreo = Estilos.crearCampoTexto();
    JPasswordField txtContra = Estilos.crearCampoContrasena();
    JTextField txtId = new JTextField();
    txtId.setEditable(false);
    txtId.setVisible(false);

    formPanel.add(Estilos.crearLabel("Nombre:"));
    formPanel.add(txtNombre);
    formPanel.add(Estilos.crearLabel("Apellido:"));
    formPanel.add(txtApellido);
    formPanel.add(Estilos.crearLabel("DNI:"));
    formPanel.add(txtDni);
    formPanel.add(Estilos.crearLabel("Correo:"));
    formPanel.add(txtCorreo);
    formPanel.add(Estilos.crearLabel("Nueva Contraseña:"));
    formPanel.add(txtContra);

    JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    Estilos.estilizarPanel(btnPanel);

    JButton btnCrear = Estilos.crearBotonPrimario("Crear");
    JButton btnActualizar = Estilos.crearBotonPrimario("Actualizar");
    JButton btnEliminar = Estilos.crearBotonPrimario("Eliminar");
    btnEliminar.setBackground(new Color(211, 47, 47));
    JButton btnLimpiar = Estilos.crearBotonPrimario("Limpiar");
    btnLimpiar.setBackground(Color.GRAY);

    btnPanel.add(btnCrear);
    btnPanel.add(btnActualizar);
    btnPanel.add(btnEliminar);
    btnPanel.add(btnLimpiar);

    JPanel surPanel = new JPanel(new BorderLayout());
    Estilos.estilizarPanel(surPanel);
    surPanel.add(formPanel, BorderLayout.CENTER);
    surPanel.add(btnPanel, BorderLayout.SOUTH);
    panel.add(surPanel, BorderLayout.SOUTH);

    Runnable cargarDatos = () -> {
      tableModel.setRowCount(0);
      List<Cliente> lista = clienteDAO.listar();
      for (Cliente c : lista) {
        tableModel.addRow(new Object[] { c.getId(), c.getNombre(), c.getApellido(), c.getDni(), c.getCorreo() });
      }
    };

    cargarDatos.run();

    table.getSelectionModel().addListSelectionListener(e -> {
      int row = table.getSelectedRow();
      if (row != -1) {
        txtId.setText(tableModel.getValueAt(row, 0).toString());
        txtNombre.setText(tableModel.getValueAt(row, 1).toString());
        txtApellido.setText(tableModel.getValueAt(row, 2).toString());
        txtDni.setText(tableModel.getValueAt(row, 3).toString());
        txtCorreo.setText(tableModel.getValueAt(row, 4).toString());
      }
    });

    btnLimpiar.addActionListener(e -> {
      txtId.setText("");
      txtNombre.setText("");
      txtApellido.setText("");
      txtDni.setText("");
      txtCorreo.setText("");
      txtContra.setText("");
      table.clearSelection();
    });

    btnCrear.addActionListener(e -> {
      if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty() ||
          txtDni.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos (Nombre, Apellido, DNI, Correo) son obligatorios.");
        return;
      }
      Cliente c = new Cliente(txtNombre.getText(), txtApellido.getText(), txtDni.getText(), txtCorreo.getText(),
          new String(txtContra.getPassword()));
      if (clienteDAO.registrar(c)) {
        JOptionPane.showMessageDialog(this, "Cliente creado");
        cargarDatos.run();
        btnLimpiar.doClick();
      } else {
        JOptionPane.showMessageDialog(this, "Error al crear");
      }
    });

    btnActualizar.addActionListener(e -> {
      String idStr = txtId.getText();
      if (idStr.isEmpty())
        return;
      if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty() ||
          txtDni.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
        return;
      }
      int id = Integer.parseInt(idStr);
      String pass = new String(txtContra.getPassword());
      if (pass.isEmpty())
        pass = "cliente123";
      Cliente c = new Cliente(id, txtNombre.getText(), txtApellido.getText(), txtDni.getText(), txtCorreo.getText(),
          pass);
      if (clienteDAO.actualizar(c)) {
        JOptionPane.showMessageDialog(this, "Cliente actualizado");
        cargarDatos.run();
        btnLimpiar.doClick();
      } else {
        JOptionPane.showMessageDialog(this, "Error al actualizar");
      }
    });

    btnEliminar.addActionListener(e -> {
      String idStr = txtId.getText();
      if (idStr.isEmpty())
        return;
      if (JOptionPane.showConfirmDialog(this, "¿Seguro?") == JOptionPane.YES_OPTION) {
        if (clienteDAO.eliminar(Integer.parseInt(idStr))) {
          JOptionPane.showMessageDialog(this, "Cliente eliminado");
          cargarDatos.run();
          btnLimpiar.doClick();
        } else {
          JOptionPane.showMessageDialog(this, "Error al eliminar");
        }
      }
    });

    return panel;
  }
}
