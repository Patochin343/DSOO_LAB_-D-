package com.banco.dao;

import com.banco.model.Cuenta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAO {

  public boolean crear(Cuenta c, int idCliente) {
    Connection conexion = null;
    PreparedStatement psCuenta = null;
    PreparedStatement psRel = null;
    ResultSet resultado = null;

    String consultaCuenta = "INSERT INTO cuentas (numero_cuenta, saldo, tipo_cuenta) VALUES (?, ?, ?)";
    String consultaRel = "INSERT INTO cliente_cuenta (id_cliente, id_cuenta) VALUES (?, ?)";

    try {
      conexion = ConexionBD.getConexion();
      conexion.setAutoCommit(false);

      psCuenta = conexion.prepareStatement(consultaCuenta, Statement.RETURN_GENERATED_KEYS);
      psCuenta.setString(1, c.getNumeroCuenta());
      psCuenta.setDouble(2, c.getSaldo());
      psCuenta.setString(3, c.getTipoCuenta());
      psCuenta.executeUpdate();

      resultado = psCuenta.getGeneratedKeys();
      int idCuenta = -1;
      if (resultado.next()) {
        idCuenta = resultado.getInt(1);
      }

      if (idCuenta == -1) {
        conexion.rollback();
        return false;
      }

      psRel = conexion.prepareStatement(consultaRel);
      psRel.setInt(1, idCliente);
      psRel.setInt(2, idCuenta);
      psRel.executeUpdate();

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
        if (resultado != null)
          resultado.close();
        if (psCuenta != null)
          psCuenta.close();
        if (psRel != null)
          psRel.close();
        if (conexion != null)
          conexion.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public List<Cuenta> listarPorCliente(int idCliente) {
    List<Cuenta> cuentas = new ArrayList<>();
    String consulta = "SELECT c.* FROM cuentas c " +
        "JOIN cliente_cuenta cc ON c.id = cc.id_cuenta " +
        "WHERE cc.id_cliente = ?";

    try (Connection conexion = ConexionBD.getConexion();
        PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

      sentencia.setInt(1, idCliente);
      try (ResultSet resultado = sentencia.executeQuery()) {
        while (resultado.next()) {
          cuentas.add(new Cuenta(
              resultado.getInt("id"),
              resultado.getString("numero_cuenta"),
              resultado.getDouble("saldo"),
              resultado.getString("tipo_cuenta")));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return cuentas;
  }

  public Cuenta getCuenta(int idCuenta) {
    String consulta = "SELECT * FROM cuentas WHERE id = ?";
    try (Connection conexion = ConexionBD.getConexion();
        PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

      sentencia.setInt(1, idCuenta);
      try (ResultSet resultado = sentencia.executeQuery()) {
        if (resultado.next()) {
          return new Cuenta(
              resultado.getInt("id"),
              resultado.getString("numero_cuenta"),
              resultado.getDouble("saldo"),
              resultado.getString("tipo_cuenta"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void actualizarSaldo(int idCuenta, double nuevoSaldo, Connection conexion) throws SQLException {
    String consulta = "UPDATE cuentas SET saldo = ? WHERE id = ?";
    try (PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
      sentencia.setDouble(1, nuevoSaldo);
      sentencia.setInt(2, idCuenta);
      sentencia.executeUpdate();
    }
  }

  public Cuenta getCuentaPorNumero(String numeroCuenta) {
    String consulta = "SELECT * FROM cuentas WHERE numero_cuenta = ?";
    try (Connection conexion = ConexionBD.getConexion();
        PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

      sentencia.setString(1, numeroCuenta);
      try (ResultSet resultado = sentencia.executeQuery()) {
        if (resultado.next()) {
          return new Cuenta(
              resultado.getInt("id"),
              resultado.getString("numero_cuenta"),
              resultado.getDouble("saldo"),
              resultado.getString("tipo_cuenta"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
