
public class Cliente extends Persona {

    private String idCliente;
    private String correo;
    private String estado; // "activo", "inactivo"

    // --- CAMBIO ---
    // Se elimina la 'List<Cuenta> cuentas'. 
    // Gestor usará la lista 'titularidades' como única fuente de verdad.

    public Cliente(String dni, String nombre, String apellido, String direccion, String telefono,
                   String idCliente, String correo, String estado) {
        
        super(dni, nombre, apellido, direccion, telefono);
        
        if (correo == null || !correo.contains("@")) {
            throw new IllegalArgumentException("El correo es inválido. Debe contener un '@'.");
        }

        this.idCliente = idCliente;
        this.correo = correo;
        this.estado = estado;
        // Se elimina la inicialización de la lista de cuentas
    }

    // --- CAMBIO ---
    // Se elimina el método 'registrarCliente()'. 
    // El Gestor se encarga de la lógica y la notificación.

    // --- CAMBIO ---
    // Se elimina el método 'consultarCuentas()'.
    // Esta lógica la provee 'Gestor.obtenerCuentasDeCliente()'.

    // --- CAMBIO ---
    // Se elimina el método 'agregarCuenta(Cuenta cuenta)'.
    // Titularidad ya no necesita agregarlo aquí.

    @Override
    public void mostrarDatos() {
        super.mostrarDatos(); // Muestra datos de Persona
        System.out.println("ID Cliente: " + this.idCliente); 
        System.out.println("Correo: " + this.correo); 
        System.out.println("Estado: " + this.estado); 
    }

    // --- Getters y Setters ---
    public String getIdCliente() {
        return idCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null || !correo.contains("@")) {
            throw new IllegalArgumentException("El correo es inválido. Debe contener un '@'.");
        }
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}