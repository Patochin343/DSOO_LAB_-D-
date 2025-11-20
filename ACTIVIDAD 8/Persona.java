package Actividad6;

public abstract class Persona {
  private String nombre;
  private String apellido;
  private String dni;
  private String direccion;
  private String nroTelefono;
  private String correo;
  private String idPersona;
  private String contrasena;
  private static int count=0;

  public Persona(String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo, String contrasena) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.dni = dni;
    this.direccion = direccion;
    this.nroTelefono = nroTelefono;
    this.correo = correo;
    count++;
    this.idPersona = "P-"+count;
    this.contrasena=contrasena;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public String getDni() {
    return dni;
  }

  public String getContrasena() {
    return contrasena;
  }

  public String getNroTelefono() {
    return nroTelefono;
  }

  public String getCorreo() {
    return correo;
  }

  public String mostrarPersona() {
    return "-------------------\n" +
        "Nombre: "+nombre+"\n" +
        "Apellido: "+apellido+"\n" +
        "DNI: "+dni+"\n" +
        "Direccion: "+direccion+"\n" +
        "Número de teléfono: "+nroTelefono+"\n" +
        "ID: "+idPersona+"\n" +
        "Correo: "+correo+"\n";
  }
}
