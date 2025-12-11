package com.banco.model;

public abstract class Persona {
  protected int id;
  protected String nombre;
  protected String apellido;
  protected String dni;
  protected String correo;
  protected String contrasena;
  protected String rol;

  public Persona(int id, String nombre, String apellido, String dni, String correo, String contrasena, String rol) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.dni = dni;
    this.correo = correo;
    this.contrasena = contrasena;
    this.rol = rol;
  }

  public Persona(String nombre, String apellido, String dni, String correo, String contrasena, String rol) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.dni = dni;
    this.correo = correo;
    this.contrasena = contrasena;
    this.rol = rol;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getContrasena() {
    return contrasena;
  }

  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }

  public String getRol() {
    return rol;
  }

  public void setRol(String rol) {
    this.rol = rol;
  }
}
