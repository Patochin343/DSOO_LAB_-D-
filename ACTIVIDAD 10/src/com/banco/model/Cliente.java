package com.banco.model;

public class Cliente extends Persona {
  public Cliente(int id, String nombre, String apellido, String dni, String correo, String contrasena) {
    super(id, nombre, apellido, dni, correo, contrasena, "CLIENTE");
  }

  public Cliente(String nombre, String apellido, String dni, String correo, String contrasena) {
    super(nombre, apellido, dni, correo, contrasena, "CLIENTE");
  }
}
