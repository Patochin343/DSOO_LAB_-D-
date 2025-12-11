package com.banco.model;

public class Administrador extends Persona {
  public Administrador(int id, String nombre, String apellido, String dni, String correo, String contrasena) {
    super(id, nombre, apellido, dni, correo, contrasena, "ADMIN");
  }

  public Administrador(String nombre, String apellido, String dni, String correo, String contrasena) {
    super(nombre, apellido, dni, correo, contrasena, "ADMIN");
  }
}
