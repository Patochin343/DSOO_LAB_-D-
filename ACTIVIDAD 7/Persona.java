public class Persona {

    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    public Persona(String dni, String nombre, String apellido, String direccion, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public void mostrarDatos() {
        System.out.println("--- Datos Personales ---");
        System.out.println("DNI: " + this.dni);
        System.out.println("Nombre: " + this.nombre + " " + this.apellido);
        System.out.println("Dirección: " + this.direccion);
        System.out.println("Teléfono: " + this.telefono);
    }

    // --- GETTERS Y SETTERS MEJORADOS ---
    
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}