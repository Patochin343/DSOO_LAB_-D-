import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

    // Atributos 
    private String idCliente;
    private String correo;
    private String estado; // "activo", "inactivo"

    // Esta lista representa la relación con la clase Cuenta
    private List<Cuenta> cuentas;

    // Constructor
    public Cliente(String dni, String nombre, String apellido, String direccion, String telefono,
                   String idCliente, String correo, String estado) {
        
        // Llama al constructor de la clase base (Persona)
        super(dni, nombre, apellido, direccion, telefono);
        
        // VALIDACIÓN 
        // Verificamos que el correo no sea nulo Y que contenga el símbolo "@"
        if (correo == null || !correo.contains("@")) {
            // Lanza una excepción si la validación falla
            throw new IllegalArgumentException("El correo es inválido. Debe contener un '@'.");
        }
        // FIN DE LA VALIDACIÓN

        this.idCliente = idCliente;
        this.correo = correo; // Se asigna solo si la validación pasó
        this.estado = estado;
        this.cuentas = new ArrayList<>(); // Inicializa la lista de cuentas
    }

    public void registrarCliente() {
        System.out.println("Cliente " + getNombre() + " registrado con ID: " + this.idCliente);
    }

    public void actualizarDatos() {
        System.out.println("Actualizando datos del cliente " + this.idCliente);
        // Lógica de actualización...
    }

    /**
     * Implementación del método consultarCuentas.
     * Devuelve la lista de cuentas asociadas al cliente.
     *
     */
    public List<Cuenta> consultarCuentas() {
        System.out.println("Consultando cuentas del cliente " + this.idCliente);
        return this.cuentas;
    }

    // Método para añadir cuentas a la lista del cliente
    public void agregarCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos(); // Muestra datos de Persona
        System.out.println("ID Cliente: " + this.idCliente); 
        System.out.println("Correo: " + this.correo); 
        System.out.println("Estado: " + this.estado); 
    }
}
