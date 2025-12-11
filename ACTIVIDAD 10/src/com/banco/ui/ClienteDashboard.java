package com.banco.ui;

import com.banco.dao.CuentaDAO;
import com.banco.dao.TransaccionDAO;
import com.banco.model.Cliente;
import com.banco.model.Cuenta;
import com.banco.model.Transaccion;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteDashboard extends JFrame {
  private Cliente cliente;
  private CuentaDAO cuentaDAO;
  private TransaccionDAO transaccionDAO;
  private JTable tablaCuentas;
  private DefaultTableModel modeloTabla;

  public ClienteDashboard(Cliente cliente) {
    this.cliente = cliente;
    this.cuentaDAO = new CuentaDAO();
    this.transaccionDAO = new TransaccionDAO();

    setTitle("Banca Personal - " + cliente.getNombre());
    setSize(900, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    Estilos.aplicarEstiloVentana(this);

    setLayout(new BorderLayout(20, 20));
    ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Header
    JPanel headerPanel = new JPanel(new BorderLayout());
    Estilos.estilizarPanel(headerPanel);
    headerPanel.add(Estilos.crearTitulo("Mis Cuentas"), BorderLayout.WEST);
    headerPanel.add(Estilos.crearLabel("Bienvenido, " + cliente.getNombre()), BorderLayout.EAST);
    add(headerPanel, BorderLayout.NORTH);

    // Tabla
    String[] columnas = { "ID", "No. Cuenta", "Tipo", "Saldo" };
    modeloTabla = new DefaultTableModel(columnas, 0) {
      @Override
      public boolean isCellEditable(int fila, int columna) {
        return false;
      }
    };
    tablaCuentas = new JTable(modeloTabla);
    tablaCuentas.setFont(Estilos.FUENTE_TEXTO);
    tablaCuentas.setRowHeight(30);
    tablaCuentas.getTableHeader().setFont(Estilos.FUENTE_BOTON);
    tablaCuentas.getTableHeader().setBackground(Estilos.COLOR_SECUNDARIO);
    tablaCuentas.getTableHeader().setForeground(Color.WHITE);

    JScrollPane scrollPane = new JScrollPane(tablaCuentas);
    scrollPane.getViewport().setBackground(Color.WHITE);
    add(scrollPane, BorderLayout.CENTER);

    loadCuentas();

    // Botones
    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
    Estilos.estilizarPanel(panelBotones);

    JButton botonActualizar = Estilos.crearBotonPrimario("Actualizar");
    JButton botonDepositar = Estilos.crearBotonPrimario("Depositar");
    JButton botonRetirar = Estilos.crearBotonPrimario("Retirar");
    JButton botonTransferir = Estilos.crearBotonPrimario("Transferir");
    JButton botonHistorial = Estilos.crearBotonPrimario("Ver Movimientos");
    botonHistorial.setBackground(Estilos.COLOR_SECUNDARIO);

    panelBotones.add(botonActualizar);
    panelBotones.add(botonDepositar);
    panelBotones.add(botonRetirar);
    panelBotones.add(botonTransferir);
    panelBotones.add(botonHistorial);
    add(panelBotones, BorderLayout.SOUTH);

    botonActualizar.addActionListener(e -> loadCuentas());

    botonDepositar.addActionListener(e -> realizarTransaccion("DEPOSITO"));
    botonRetirar.addActionListener(e -> realizarTransaccion("RETIRO"));
    botonTransferir.addActionListener(e -> realizarTransferenciaUI());
    botonHistorial.addActionListener(e -> verHistorial());
  }

  private void loadCuentas() {
    modeloTabla.setRowCount(0);
    List<Cuenta> cuentas = cuentaDAO.listarPorCliente(cliente.getId());
    for (Cuenta c : cuentas) {
      modeloTabla.addRow(new Object[] { c.getId(), c.getNumeroCuenta(), c.getTipoCuenta(), c.getSaldo() });
    }
  }

  private void realizarTransaccion(String tipo) {
    int fila = tablaCuentas.getSelectedRow();
    if (fila == -1) {
      JOptionPane.showMessageDialog(this, "Seleccione una cuenta");
      return;
    }

    int idCuenta = (int) modeloTabla.getValueAt(fila, 0);

    String entrada = JOptionPane.showInputDialog(this, "Ingrese monto para " + tipo + ":");
    if (entrada != null && !entrada.isEmpty()) {
      try {
        double monto = Double.parseDouble(entrada);
        if (monto <= 0) {
          JOptionPane.showMessageDialog(this, "Monto debe ser positivo");
          return;
        }

        if (transaccionDAO.realizarTransaccion(idCuenta, tipo, monto)) {
          JOptionPane.showMessageDialog(this, "Transacción exitosa");
          loadCuentas();
        } else {
          JOptionPane.showMessageDialog(this, "Error en transacción (verifique saldo)");
        }
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Monto inválido");
      }
    }
  }

  private void realizarTransferenciaUI() {
    int fila = tablaCuentas.getSelectedRow();
    if (fila == -1) {
      JOptionPane.showMessageDialog(this, "Seleccione una cuenta origen");
      return;
    }
    int idCuenta = (int) modeloTabla.getValueAt(fila, 0);
    String numCuentaOrigen = (String) modeloTabla.getValueAt(fila, 1);

    String cuentaDestino = JOptionPane.showInputDialog(this, "Ingrese el número de cuenta destino:");
    if (cuentaDestino == null || cuentaDestino.isEmpty())
      return;

    if (cuentaDestino.equals(numCuentaOrigen)) {
      JOptionPane.showMessageDialog(this, "No puede transferir a la misma cuenta de origen.");
      return;
    }

    String montoEntrada = JOptionPane.showInputDialog(this, "Ingrese monto a transferir:");
    if (montoEntrada != null && !montoEntrada.isEmpty()) {
      try {
        double monto = Double.parseDouble(montoEntrada);
        if (monto <= 0) {
          JOptionPane.showMessageDialog(this, "Monto debe ser positivo");
          return;
        }

        if (transaccionDAO.realizarTransferencia(idCuenta, cuentaDestino, monto)) {
          JOptionPane.showMessageDialog(this, "Transferencia realizada con éxito!");
          loadCuentas();
        } else {
          JOptionPane.showMessageDialog(this, "Error: Verifique saldo o cuenta destino inexistente.");
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Monto inválido");
      }
    }
  }

  private void verHistorial() {
    int fila = tablaCuentas.getSelectedRow();
    if (fila == -1) {
      JOptionPane.showMessageDialog(this, "Seleccione una cuenta");
      return;
    }

    int idCuenta = (int) modeloTabla.getValueAt(fila, 0);
    String numCuenta = (String) modeloTabla.getValueAt(fila, 1);

    JDialog dialogo = new JDialog(this, "Historial - " + numCuenta, true);
    dialogo.setSize(600, 400);
    dialogo.setLocationRelativeTo(this);
    Estilos.aplicarEstiloVentana((JFrame) SwingUtilities.getWindowAncestor(this)); // Hacky, mejor no tocar el dialog
                                                                                   // mucho
    dialogo.getContentPane().setBackground(Estilos.COLOR_FONDO);

    String[] columnas = { "Fecha", "Tipo", "Monto" };
    DefaultTableModel modeloHist = new DefaultTableModel(columnas, 0);
    JTable tablaHist = new JTable(modeloHist);
    tablaHist.setFont(Estilos.FUENTE_TEXTO);
    tablaHist.setRowHeight(25);
    tablaHist.getTableHeader().setFont(Estilos.FUENTE_BOTON);
    tablaHist.getTableHeader().setBackground(Estilos.COLOR_SECUNDARIO);
    tablaHist.getTableHeader().setForeground(Color.WHITE);

    List<Transaccion> transacciones = transaccionDAO.listar(idCuenta);
    for (Transaccion t : transacciones) {
      String montoStr = (t.getMonto() > 0 ? "+" : "") + t.getMonto();
      modeloHist.addRow(new Object[] { t.getFecha(), t.getTipoTransaccion(), montoStr });
    }

    dialogo.add(new JScrollPane(tablaHist));
    dialogo.setVisible(true);
  }
}
