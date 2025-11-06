public class Persona {

    // Atributos 
    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    // Constructor para inicializar los datos
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

    // Getters y Setters 
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
