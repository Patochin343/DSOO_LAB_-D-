package com.banco.model;

public class Empleado extends Persona {
  public Empleado(int id, String nombre, String apellido, String dni, String correo, String contrasena) {
    super(id, nombre, apellido, dni, correo, contrasena, "EMPLEADO");
  }

  public Empleado(String nombre, String apellido, String dni, String correo, String contrasena) {
    super(nombre, apellido, dni, correo, contrasena, "EMPLEADO");
  }
}
