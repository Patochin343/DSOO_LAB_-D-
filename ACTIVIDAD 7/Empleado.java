
public class Empleado extends Persona {
    private String idEmpleado;
    private String cargo;

    public Empleado(String dni, String nombre, String apellido, String direccion, String telefono,
                    String idEmpleado, String cargo) {
        super(dni, nombre, apellido, direccion, telefono);
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
    }

    // Procesar una transacción
    public void procesarTransaccion(Transaccion t, Cuenta cuenta) {
        // La transacción (depósito o retiro) puede lanzar una excepción
        t.procesar(cuenta);
    }

    // --- CAMBIO ---
    // Se elimina 'registrarCliente(Cliente c)'.
    // Gestor se encarga de la lógica y notificación.

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public String getCargo() {
        return cargo;
    }
}