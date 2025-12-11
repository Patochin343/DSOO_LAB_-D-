package com.banco.dao;

import com.banco.model.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

  public boolean registrar(Empleado e) {
    String consulta = "INSERT INTO personas (nombre, apellido, dni, correo, contrasena, rol) VALUES (?, ?, ?, ?, ?, 'EMPLEADO')";
    try (Connection conexion = ConexionBD.getConexion();
        PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

      sentencia.setString(1, e.getNombre());
      sentencia.setString(2, e.getApellido());
      sentencia.setString(3, e.getDni());
      sentencia.setString(4, e.getCorreo());
      sentencia.setString(5, e.getContrasena());

      int filas = sentencia.executeUpdate();
      return filas > 0;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }
  }

  public List<Empleado> listar() {
    List<Empleado> empleados = new ArrayList<>();
    String consulta = "SELECT * FROM personas WHERE rol = 'EMPLEADO'";
    try (Connection conexion = ConexionBD.getConexion();
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(consulta)) {

      while (resultado.next()) {
        empleados.add(new Empleado(
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
    return empleados;
  }

  public boolean actualizar(Empleado e) {
    String consulta = "UPDATE personas SET nombre=?, apellido=?, dni=?, correo=?, contrasena=? WHERE id=? AND rol='EMPLEADO'";
    try (Connection conexion = ConexionBD.getConexion();
        PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

      sentencia.setString(1, e.getNombre());
      sentencia.setString(2, e.getApellido());
      sentencia.setString(3, e.getDni());
      sentencia.setString(4, e.getCorreo());
      sentencia.setString(5, e.getContrasena());
      sentencia.setInt(6, e.getId());

      return sentencia.executeUpdate() > 0;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }
  }

  public boolean eliminar(int id) {
    String consulta = "DELETE FROM personas WHERE id=? AND rol='EMPLEADO'";
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
