package com.banco.dao;

import com.banco.model.*;
import java.sql.*;

public class PersonasDAO {

  public Persona login(String dni, String contrasena) {
    String consulta = "SELECT * FROM personas WHERE dni = ? AND contrasena = ?";
    try (Connection conexion = ConexionBD.getConexion();
        PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

      sentencia.setString(1, dni);
      sentencia.setString(2, contrasena);

      try (ResultSet resultado = sentencia.executeQuery()) {
        if (resultado.next()) {
          String rol = resultado.getString("rol");
          int id = resultado.getInt("id");
          String nombre = resultado.getString("nombre");
          String apellido = resultado.getString("apellido");
          String correo = resultado.getString("correo");

          switch (rol) {
            case "ADMIN":
              return new Administrador(id, nombre, apellido, dni, correo, contrasena);
            case "EMPLEADO":
              return new Empleado(id, nombre, apellido, dni, correo, contrasena);
            case "CLIENTE":
              return new Cliente(id, nombre, apellido, dni, correo, contrasena);
            default:
              return null;
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean existeDni(String dni) {
    String consulta = "SELECT id FROM personas WHERE dni = ?";
    try (Connection conexion = ConexionBD.getConexion();
        PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
      sentencia.setString(1, dni);
      try (ResultSet resultado = sentencia.executeQuery()) {
        return resultado.next();
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
