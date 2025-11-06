import java.util.*;

public class Gestor {
    private Map<String, Cliente> clientes;
    private Map<String, Empleado> empleados;
    private Map<String, Cuenta> cuentas;
    private List<Titularidad> titularidades;
    private List<Transaccion> transaccionesGlobales;
    
    public Gestor() {
        this.clientes = new HashMap<>();
        this.empleados = new HashMap<>();
        this.cuentas = new HashMap<>();
        this.titularidades = new ArrayList<>();
        this.transaccionesGlobales = new ArrayList<>();
    }

    // --- REGISTRO DE EMPLEADOS ---
    public void registrarEmpleado(String dni, String nombre, String apellido, String direccion, 
                                 String telefono, String idEmpleado, String cargo) {
        if (empleados.containsKey(idEmpleado)) {
            throw new IllegalArgumentException("El empleado con ID " + idEmpleado + " ya existe");
        }
        Empleado empleado = new Empleado(dni, nombre, apellido, direccion, telefono, idEmpleado, cargo);
        empleados.put(idEmpleado, empleado);
        System.out.println("Empleado " + nombre + " registrado con ID: " + idEmpleado);
    }
    
    public Empleado buscarEmpleado(String idEmpleado) {
        return empleados.get(idEmpleado);
    }
    
    // --- REGISTRO DE CLIENTES ---
    public void registrarCliente(String idEmpleadoRegistrador, String dni, String nombre, String apellido, 
                                String direccion, String telefono, String idCliente, String correo, String estado) {
        Empleado empleado = empleados.get(idEmpleadoRegistrador);
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + idEmpleadoRegistrador);
        }
        
        if (clientes.containsKey(idCliente)) {
            throw new IllegalArgumentException("El cliente con ID " + idCliente + " ya existe");
        }
        
        Cliente cliente = new Cliente(dni, nombre, apellido, direccion, telefono, idCliente, correo, estado);
        clientes.put(idCliente, cliente);
        
        // --- CAMBIO ---
        // Se elimina la llamada a empleado.registrarCliente(cliente)
        System.out.println("Cliente " + nombre + " registrado exitosamente por empleado " + empleado.getNombre());
    }
    
    public Cliente buscarCliente(String idCliente) {
        return clientes.get(idCliente);
    }
    
    // --- GESTIÓN DE CUENTAS Y TITULARES ---
    public void crearCuenta(String numeroCuenta, String tipoCuenta, double saldoInicial) {
        if (cuentas.containsKey(numeroCuenta)) {
            throw new IllegalArgumentException("La cuenta " + numeroCuenta + " ya existe");
        }
        Cuenta cuenta = new Cuenta(numeroCuenta, tipoCuenta, saldoInicial, new Date());
        cuentas.put(numeroCuenta, cuenta);
        System.out.println("Cuenta " + numeroCuenta + " creada exitosamente");
    }
    
    public void asignarTitularCuenta(String idCliente, String numeroCuenta, String tipoTitular) {
        Cliente cliente = clientes.get(idCliente);
        Cuenta cuenta = cuentas.get(numeroCuenta);
        
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado: " + idCliente);
        }
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta no encontrada: " + numeroCuenta);
        }
        
        Titularidad titularidad = new Titularidad(new Date(), tipoTitular);
        titularidad.asignarTitular(cliente, cuenta);
        titularidades.add(titularidad);
        
        // --- CAMBIO ---
        // El Gestor imprime la confirmación, ya que Titularidad ya no lo hace.
        System.out.println("Titularidad asignada: Cliente " + idCliente + " a cuenta " + numeroCuenta);
    }
    
    public List<Cuenta> obtenerCuentasDeCliente(String idCliente) {
        List<Cuenta> cuentasCliente = new ArrayList<>();
        // Esta es la única fuente de verdad
        for (Titularidad titularidad : titularidades) {
            Cliente titular = titularidad.getCliente();
            if (titular != null && idCliente.equals(titular.getIdCliente())) {
                cuentasCliente.add(titularidad.getCuenta());
            }
        }
        return cuentasCliente;
    }
    
    // --- TRANSACCIONES CON MANEJO DE EXCEPCIONES ---
    
    public void ejecutarDeposito(String idEmpleado, String idTransaccion, double monto, String numeroCuenta) {
        Empleado empleado = empleados.get(idEmpleado);
        Cuenta cuenta = cuentas.get(numeroCuenta);
        
        if (empleado == null) { /* ... validación ... */ }
        if (cuenta == null) { /* ... validación ... */ }

        // --- CAMBIO ---
        // Se usa try-catch para manejar errores de lógica (ej. monto negativo)
        try {
            Deposito deposito = new Deposito(idTransaccion, new Date(), monto);
            empleado.procesarTransaccion(deposito, cuenta); // Puede lanzar excepción
            
            // Solo se registra globalmente si tiene éxito
            transaccionesGlobales.add(deposito);
            System.out.println("Depósito " + idTransaccion + " ejecutado por " + empleado.getNombre() + ". Nuevo saldo: " + cuenta.consultarSaldo());
        
        } catch (IllegalArgumentException e) {
            System.err.println("Error en Depósito " + idTransaccion + ": " + e.getMessage());
        }
    }
    

    public void ejecutarRetiro(String idEmpleado, String idTransaccion, double monto, String numeroCuenta) {
        Empleado empleado = empleados.get(idEmpleado);
        Cuenta cuenta = cuentas.get(numeroCuenta);
        
        if (empleado == null) { /* ... validación ... */ }
        if (cuenta == null) { /* ... validación ... */ }
        
        // --- CAMBIO ---
        // Se usa try-catch para manejar errores (saldo insuficiente, monto inválido)
        try {
            Retiro retiro = new Retiro(idTransaccion, new Date(), monto);
            empleado.procesarTransaccion(retiro, cuenta); // Puede lanzar excepción
            
            // Solo se registra globalmente si tiene éxito
            transaccionesGlobales.add(retiro);
            System.out.println("Retiro " + idTransaccion + " ejecutado por " + empleado.getNombre() + ". Nuevo saldo: " + cuenta.consultarSaldo());
        
        } catch (IllegalStateException | IllegalArgumentException e) {
            // Captura ambos tipos de excepción (Saldo insuficiente o Monto inválido)
            System.err.println("Error en Retiro " + idTransaccion + ": " + e.getMessage());
        }
    }
    
    // --- REPORTES Y GETTERS (sin cambios significativos) ---
    
    public List<Transaccion> obtenerHistorialCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null) {
            return new ArrayList<>(cuenta.getTransacciones());
        }
        return new ArrayList<>();
    }

    public void generarReporteGeneral() {
        // ... (sin cambios) ...
    }
        
    public Map<String, Cliente> getClientes() {
        return new HashMap<>(clientes);
    }
    
    public Map<String, Empleado> getEmpleados() {
        return new HashMap<>(empleados);
    }
    
    public Map<String, Cuenta> getCuentas() {
        return new HashMap<>(cuentas);
    }
}