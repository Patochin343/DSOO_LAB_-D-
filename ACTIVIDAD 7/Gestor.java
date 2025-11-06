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
    

    public void registrarCliente(String idEmpleadoRegistrador, String dni, String nombre, String apellido, 
                                String direccion, String telefono, String idCliente, String correo, String estado) {
        // Verificar que el empleado exista
        Empleado empleado = empleados.get(idEmpleadoRegistrador);
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + idEmpleadoRegistrador);
        }
        
        // Verificar que el cliente no exista
        if (clientes.containsKey(idCliente)) {
            throw new IllegalArgumentException("El cliente con ID " + idCliente + " ya existe");
        }
        
        // Crear y registrar el cliente
        Cliente cliente = new Cliente(dni, nombre, apellido, direccion, telefono, idCliente, correo, estado);
        clientes.put(idCliente, cliente);
        
        // El empleado registra al cliente
        empleado.registrarCliente(cliente);
        System.out.println("Cliente " + nombre + " registrado exitosamente por empleado " + empleado.getNombre());
    }
    
    public Cliente buscarCliente(String idCliente) {
        return clientes.get(idCliente);
    }
    
    public List<Cliente> obtenerClientesActivos() {
        List<Cliente> clientesActivos = new ArrayList<>();
        for (Cliente cliente : clientes.values()) {
            if ("activo".equals(cliente.getEstado())) {
                clientesActivos.add(cliente);
            }
        }
        return clientesActivos;
    }
    
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
        System.out.println("Titularidad asignada: Cliente " + idCliente + " a cuenta " + numeroCuenta);
    }
    
    public List<Cuenta> obtenerCuentasDeCliente(String idCliente) {
        List<Cuenta> cuentasCliente = new ArrayList<>();
        
        for (Titularidad titularidad : titularidades) {
            Cliente titular = titularidad.getCliente();
            if (titular != null && idCliente.equals(titular.getIdCliente())) {
                cuentasCliente.add(titularidad.getCuenta());
            }
        }
        return cuentasCliente;
    }
    
    public void ejecutarDeposito(String idEmpleado, String idTransaccion, double monto, String numeroCuenta) {
        Empleado empleado = empleados.get(idEmpleado);
        Cuenta cuenta = cuentas.get(numeroCuenta);
        
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + idEmpleado);
        }
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta no encontrada: " + numeroCuenta);
        }
        
        Deposito deposito = new Deposito(idTransaccion, new Date(), monto);
        empleado.procesarTransaccion(deposito, cuenta);
        transaccionesGlobales.add(deposito);
        
        System.out.println("Depósito " + idTransaccion + " ejecutado por empleado " + empleado.getNombre());
    }
    

    public void ejecutarRetiro(String idEmpleado, String idTransaccion, double monto, String numeroCuenta) {
        Empleado empleado = empleados.get(idEmpleado);
        Cuenta cuenta = cuentas.get(numeroCuenta);
        
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + idEmpleado);
        }
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta no encontrada: " + numeroCuenta);
        }
        
        Retiro retiro = new Retiro(idTransaccion, new Date(), monto);
        empleado.procesarTransaccion(retiro, cuenta);
        transaccionesGlobales.add(retiro);
        
        System.out.println("Retiro " + idTransaccion + " ejecutado por empleado " + empleado.getNombre());
    }
    
    public List<Transaccion> obtenerHistorialCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null) {
            return new ArrayList<>(cuenta.getTransacciones());
        }
        return new ArrayList<>();
    }

    public List<Transaccion> obtenerTransaccionesGlobales() {
        return new ArrayList<>(transaccionesGlobales);
    }
    
    public void generarReporteGeneral() {
        System.out.println("Total clientes: " + clientes.size());
        System.out.println("Total empleados: " + empleados.size());
        System.out.println("Total cuentas: " + cuentas.size());
        System.out.println("Total transacciones: " + transaccionesGlobales.size());
        
        double saldoTotal = 0.0;
        for (Cuenta cuenta : cuentas.values()) {
            saldoTotal += cuenta.getSaldo();
        }
        System.out.println("Saldo total en el sistema: " + saldoTotal);
    }
        
    public boolean existeCliente(String idCliente) {
        return clientes.containsKey(idCliente);
    }
    
    public boolean existeEmpleado(String idEmpleado) {
        return empleados.containsKey(idEmpleado);
    }
    
    public boolean existeCuenta(String numeroCuenta) {
        return cuentas.containsKey(numeroCuenta);
    }
    
    // Getters para acceso a las colecciones (solo lectura)
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