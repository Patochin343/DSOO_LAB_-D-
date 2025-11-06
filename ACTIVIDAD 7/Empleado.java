import java.util.Date;

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
        t.procesar(cuenta);
    }

    // Registrar cliente
    public void registrarCliente(Cliente c) {
        System.out.println("Empleado " + getNombre() + " registró al cliente " + c.getNombre());
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public String getCargo() {
        return cargo;
    }
}
