package com.banco.dao;

import com.banco.model.Transaccion;
import com.banco.model.Cuenta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAO {

  private CuentaDAO cuentaDAO = new CuentaDAO();

  public boolean realizarTransaccion(int idCuenta, String tipo, double monto) {
    Connection conexion = null;
    PreparedStatement sentencia = null;

    try {
      conexion = ConexionBD.getConexion();
      conexion.setAutoCommit(false);

      Cuenta cuenta = cuentaDAO.getCuenta(idCuenta);
      if (cuenta == null)
        throw new SQLException("Cuenta no encontrada");

      double nuevoSaldo = cuenta.getSaldo();
      if ("RETIRO".equals(tipo)) {
        if (cuenta.getSaldo() < monto) {
          throw new SQLException("Fondos insuficientes");
        }
        nuevoSaldo -= monto;
      } else if ("DEPOSITO".equals(tipo)) {
        nuevoSaldo += monto;
      } else {
        throw new SQLException("Tipo de transacción inválido");
      }

      String consulta = "INSERT INTO transacciones (id_cuenta, tipo_transaccion, monto) VALUES (?, ?, ?)";
      sentencia = conexion.prepareStatement(consulta);
      sentencia.setInt(1, idCuenta);
      sentencia.setString(2, tipo);

      double montoRegistro = "RETIRO".equals(tipo) ? -monto : monto;
      sentencia.setDouble(3, montoRegistro);

      sentencia.executeUpdate();

      cuentaDAO.actualizarSaldo(idCuenta, nuevoSaldo, conexion);

      conexion.commit();
      return true;

    } catch (SQLException e) {
      e.printStackTrace();
      try {
        if (conexion != null)
          conexion.rollback();
      } catch (SQLException ex) {
      }
      return false;
    } finally {
      try {
        if (sentencia != null)
          sentencia.close();
        if (conexion != null)
          conexion.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public List<Transaccion> listar(int idCuenta) {
    List<Transaccion> lista = new ArrayList<>();
    String consulta = "SELECT * FROM transacciones WHERE id_cuenta = ? ORDER BY fecha DESC";

    try (Connection conexion = ConexionBD.getConexion();
        PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

      sentencia.setInt(1, idCuenta);
      try (ResultSet resultado = sentencia.executeQuery()) {
        while (resultado.next()) {
          lista.add(new Transaccion(
              resultado.getInt("id"),
              resultado.getInt("id_cuenta"),
              resultado.getString("tipo_transaccion"),
              resultado.getDouble("monto"),
              resultado.getTimestamp("fecha")));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return lista;
  }

  public boolean realizarTransferencia(int idOrigen, String numCuentaDestino, double monto) {
    Connection conexion = null;
    PreparedStatement sentencia = null;

    try {
      conexion = ConexionBD.getConexion();
      conexion.setAutoCommit(false);

      Cuenta origen = cuentaDAO.getCuenta(idOrigen);
      if (origen == null)
        throw new SQLException("Cuenta origen no encontrada");
      if (origen.getSaldo() < monto)
        throw new SQLException("Fondos insuficientes");

      Cuenta destino = cuentaDAO.getCuentaPorNumero(numCuentaDestino);
      if (destino == null)
        throw new SQLException("Cuenta destino no encontrada");

      cuentaDAO.actualizarSaldo(idOrigen, origen.getSaldo() - monto, conexion);
      cuentaDAO.actualizarSaldo(destino.getId(), destino.getSaldo() + monto, conexion);

      String consultaTransaccion = "INSERT INTO transacciones (id_cuenta, tipo_transaccion, monto) VALUES (?, ?, ?)";
      sentencia = conexion.prepareStatement(consultaTransaccion);

      sentencia.setInt(1, idOrigen);
      sentencia.setString(2, "TRANSFERENCIA");
      sentencia.setDouble(3, -monto);
      sentencia.executeUpdate();

      sentencia.setInt(1, destino.getId());
      sentencia.setString(2, "TRANSFERENCIA");
      sentencia.setDouble(3, monto);
      sentencia.executeUpdate();

      conexion.commit();
      return true;

    } catch (SQLException e) {
      e.printStackTrace();
      try {
        if (conexion != null)
          conexion.rollback();
      } catch (SQLException ex) {
      }
      return false;
    } finally {
      try {
        if (sentencia != null)
          sentencia.close();
        if (conexion != null)
          conexion.setAutoCommit(true);
        if (conexion != null)
          conexion.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
