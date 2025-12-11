package com.banco.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
  private static final String URL_BD = "jdbc:mysql://localhost:3306/banco_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
  private static final String USUARIO = "root";
  private static final String CONTRASENA = "rootpassword";

  public static Connection getConexion() throws SQLException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      return DriverManager.getConnection(URL_BD, USUARIO, CONTRASENA);
    } catch (ClassNotFoundException e) {
      throw new SQLException("MySQL Driver no encontrado", e);
    }
  }
}
