package com.banco.dao;

import com.banco.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

  public boolean registrar(Cliente c) {
    String consulta = "INSERT INTO personas (nombre, apellido, dni, correo, contrasena, rol) VALUES (?, ?, ?, ?, ?, 'CLIENTE')";
    try (Connection conexion = ConexionBD.getConexion();
        PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

      sentencia.setString(1, c.getNombre());
      sentencia.setString(2, c.getApellido());
      sentencia.setString(3, c.getDni());
      sentencia.setString(4, c.getCorreo());
      sentencia.setString(5, c.getContrasena());

      int filas = sentencia.executeUpdate();
      return filas > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public List<Cliente> listar() {
    List<Cliente> clientes = new ArrayList<>();
    String consulta = "SELECT * FROM personas WHERE rol = 'CLIENTE'";
    try (Connection conexion = ConexionBD.getConexion();
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(consulta)) {

      while (resultado.next()) {
        clientes.add(new Cliente(
            resultado.getInt("id"),
            resultado.getString("nombre"),
            resultado.getString("apellido"),
            resultado.getString("dni"),
            resultado.getString("correo"),
            resultado.getString("contrasena")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return clientes;
  }

  public boolean actualizar(Cliente c) {
    String consulta = "UPDATE personas SET nombre=?, apellido=?, dni=?, correo=?, contrasena=? WHERE id=? AND rol='CLIENTE'";
    try (Connection conexion = ConexionBD.getConexion();
        PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

      sentencia.setString(1, c.getNombre());
      sentencia.setString(2, c.getApellido());
      sentencia.setString(3, c.getDni());
      sentencia.setString(4, c.getCorreo());
      sentencia.setString(5, c.getContrasena());
      sentencia.setInt(6, c.getId());

      return sentencia.executeUpdate() > 0;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }
  }

  public boolean eliminar(int id) {
    String consulta = "DELETE FROM personas WHERE id=? AND rol='CLIENTE'";
    try (Connection conexion = ConexionBD.getConexion();
        PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
      sentencia.setInt(1, id);
      return sentencia.executeUpdate() > 0;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }
  }
}
