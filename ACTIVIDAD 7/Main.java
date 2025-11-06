import java.util.List;
import java.util.Scanner;

public class Main {

    // Hacemos el Gestor y el Scanner estáticos para que sean accesibles 
    // desde todos los métodos de esta clase.
    private static Gestor gestor = new Gestor();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        // 1. Cargar datos predefinidos
        cargarDatosIniciales();
        
        // 2. Iniciar el menú interactivo
        mostrarMenuPrincipal();
        
        // Cerrar el scanner al finalizar
        scanner.close();
        System.out.println("Programa finalizado.");
    }

    /**
     * Carga un conjunto de datos de prueba en el gestor.
     */
    private static void cargarDatosIniciales() {
        System.out.println("Cargando datos iniciales predefinidos...");
        try {
            // Empleados predefinidos
            gestor.registrarEmpleado("12345678A", "Ana", "García", "Calle Sol 1", "555-1111", "E001", "Cajero");
            gestor.registrarEmpleado("87654321B", "Luis", "Martínez", "Calle Luna 2", "555-2222", "E002", "Gerente");

            // Clientes predefinidos (registrados por E001)
            gestor.registrarCliente("E001", "11112222C", "Juan", "Pérez", "Av. Principal 123", "555-3333", "C001", "juan.perez@email.com", "activo");
            gestor.registrarCliente("E001", "33334444D", "Maria", "Lopez", "Plaza Mayor 4", "555-4444", "C002", "maria.lopez@email.com", "activo");

            // Cuentas predefinidas
            gestor.crearCuenta("CTA-1001", "Ahorros", 500.00); // Para Juan
            gestor.crearCuenta("CTA-1002", "Corriente", 1000.00); // Para Maria

            // Asignaciones predefinidas
            gestor.asignarTitularCuenta("C001", "CTA-1001", "Principal");
            gestor.asignarTitularCuenta("C002", "CTA-1002", "Principal");
            
            System.out.println("Datos iniciales cargados con éxito.");
            System.out.println("=========================================");

        } catch (Exception e) {
            System.err.println("Error fatal al cargar datos iniciales: " + e.getMessage());
            // En un caso real, quizás querríamos detener la ejecución si esto falla
        }
    }

    /**
     * Muestra el menú principal y gestiona la selección del usuario.
     */
    private static void mostrarMenuPrincipal() {
        int opcion = -1;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL DEL BANCO ---");
            System.out.println("1. Registrar Nuevo Cliente");
            System.out.println("2. Crear Nueva Cuenta");
            System.out.println("3. Asignar Titular a Cuenta");
            System.out.println("4. Realizar Depósito");
            System.out.println("5. Realizar Retiro");
            System.out.println("6. Consultar Datos de Cliente");
            System.out.println("7. Ver Movimientos de Cuenta");
            System.out.println("8. Ver Reporte General");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                // Leemos la línea completa y la convertimos a número
                // Esto evita problemas si el usuario introduce texto
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        gestionarRegistroCliente();
                        break;
                    case 2:
                        gestionarCrearCuenta();
                        break;
                    case 3:
                        gestionarAsignarTitular();
                        break;
                    case 4:
                        gestionarDeposito();
                        break;
                    case 5:
                        gestionarRetiro();
                        break;
                    case 6:
                        consultarDatosCliente();
                        break;
                    case 7:
                        verMovimientosCuenta();
                        break;
                    case 8:
                        // Este método ya estaba en Gestor
                        System.out.println("\n--- REPORTE GENERAL ---");
                        gestor.generarReporteGeneral();
                        break;
                    case 0:
                        break; // Sale del bucle
                    default:
                        System.err.println("Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: Por favor, ingrese solo un número.");
                opcion = -1; // Resetea la opción para que el bucle continúe
            }

        } while (opcion != 0);
    }

    // --- MÉTODOS AUXILIARES PARA CADA OPCIÓN DEL MENÚ ---

    private static void gestionarRegistroCliente() {
        try {
            System.out.println("\n--- Registro de Nuevo Cliente ---");
            System.out.print("ID del Empleado que registra (ej. E001): ");
            String idEmpleado = scanner.nextLine();
            
            // Validar que el empleado exista antes de continuar
            if (gestor.buscarEmpleado(idEmpleado) == null) {
                System.err.println("Error: El empleado " + idEmpleado + " no existe.");
                return;
            }

            System.out.print("DNI: ");
            String dni = scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("ID Cliente (nuevo, ej. C003): ");
            String idCliente = scanner.nextLine();
            System.out.print("Correo (ej. usuario@dominio.com): ");
            String correo = scanner.nextLine();

            // Usamos el gestor para registrar, capturando errores (ej. ID duplicado)
            gestor.registrarCliente(idEmpleado, dni, nombre, apellido, direccion, telefono, idCliente, correo, "activo");

        } catch (IllegalArgumentException e) {
            System.err.println("Error al registrar cliente: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    private static void gestionarCrearCuenta() {
        try {
            System.out.println("\n--- Creación de Nueva Cuenta ---");
            System.out.print("Número de Cuenta (nuevo, ej. CTA-1003): ");
            String numCuenta = scanner.nextLine();
            System.out.print("Tipo de Cuenta (ej. Ahorros, Corriente): ");
            String tipo = scanner.nextLine();
            System.out.print("Saldo Inicial: ");
            double saldo = Double.parseDouble(scanner.nextLine());

            gestor.crearCuenta(numCuenta, tipo, saldo);
            
            System.out.println("¡Cuenta creada! Ahora debe asignarle un titular (Opción 3).");

        } catch (NumberFormatException e) {
            System.err.println("Error: El saldo debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear cuenta: " + e.getMessage());
        }
    }
    
    private static void gestionarAsignarTitular() {
        try {
            System.out.println("\n--- Asignar Titular a Cuenta ---");
            System.out.print("ID del Cliente (ej. C001): ");
            String idCliente = scanner.nextLine();
            System.out.print("Número de Cuenta (ej. CTA-1001): ");
            String numCuenta = scanner.nextLine();
            System.out.print("Tipo de Titular (Principal, Secundario): ");
            String tipo = scanner.nextLine();
            
            gestor.asignarTitularCuenta(idCliente, numCuenta, tipo);

        } catch (IllegalArgumentException e) {
            System.err.println("Error al asignar titular: " + e.getMessage());
        }
    }
    
    private static void gestionarDeposito() {
        try {
            System.out.println("\n--- Realizar Depósito ---");
            System.out.print("ID del Empleado (ej. E001): ");
            String idEmpleado = scanner.nextLine();
            System.out.print("Número de Cuenta (ej. CTA-1001): ");
            String numCuenta = scanner.nextLine();
            System.out.print("ID de Transacción (nuevo, ej. T003): ");
            String idTrans = scanner.nextLine();
            System.out.print("Monto a depositar: ");
            double monto = Double.parseDouble(scanner.nextLine());

            // El gestor se encarga de la lógica y de imprimir el éxito o error
            gestor.ejecutarDeposito(idEmpleado, idTrans, monto, numCuenta);

        } catch (NumberFormatException e) {
            System.err.println("Error: El monto debe ser un número válido.");
        } catch (Exception e) {
            // El gestor ya imprime los errores de lógica (ej. monto negativo)
            // Esto es por si fallara algo más (ej. cuenta no existe)
            System.err.println("Error en la operación: " + e.getMessage());
        }
    }

    private static void gestionarRetiro() {
        try {
            System.out.println("\n--- Realizar Retiro ---");
            System.out.print("ID del Empleado (ej. E001): ");
            String idEmpleado = scanner.nextLine();
            System.out.print("Número de Cuenta (ej. CTA-1001): ");
            String numCuenta = scanner.nextLine();
            System.out.print("ID de Transacción (nuevo, ej. T004): ");
            String idTrans = scanner.nextLine();
            System.out.print("Monto a retirar: ");
            double monto = Double.parseDouble(scanner.nextLine());

            // El gestor se encarga de la lógica y de imprimir el éxito o error
            gestor.ejecutarRetiro(idEmpleado, idTrans, monto, numCuenta);

        } catch (NumberFormatException e) {
            System.err.println("Error: El monto debe ser un número válido.");
        } catch (Exception e) {
            // El gestor ya imprime los errores de lógica (ej. saldo insuficiente)
            System.err.println("Error en la operación: " + e.getMessage());
        }
    }

    private static void consultarDatosCliente() {
        System.out.println("\n--- Consultar Datos de Cliente ---");
        System.out.print("Ingrese ID del Cliente (ej. C001): ");
        String idCliente = scanner.nextLine();

        Cliente cliente = gestor.buscarCliente(idCliente);
        if (cliente == null) {
            System.err.println("No se encontró un cliente con el ID: " + idCliente);
            return;
        }

        // 1. Mostrar datos personales
        cliente.mostrarDatos();

        // 2. Mostrar sus cuentas
        System.out.println("\nCuentas asociadas:");
        List<Cuenta> cuentas = gestor.obtenerCuentasDeCliente(idCliente);
        
        if (cuentas.isEmpty()) {
            System.out.println("El cliente no tiene cuentas asignadas.");
        } else {
            for (Cuenta c : cuentas) {
                System.out.println("- " + c.getNumeroCuenta() + " (" + c.getTipoCuenta() + ") - Saldo: " + c.consultarSaldo());
            }
        }
    }
    
    private static void verMovimientosCuenta() {
        System.out.println("\n--- Historial de Movimientos de Cuenta ---");
        System.out.print("Ingrese Número de Cuenta (ej. CTA-1001): ");
        String numCuenta = scanner.nextLine();
        
        // Obtenemos los movimientos desde el gestor
        List<Transaccion> movimientos = gestor.obtenerHistorialCuenta(numCuenta);
        
        if (movimientos.isEmpty()) {
            // Esto puede significar que la cuenta no existe O que no tiene movimientos
            if (gestor.getCuentas().containsKey(numCuenta)) {
                System.out.println("La cuenta " + numCuenta + " no tiene movimientos registrados.");
            } else {
                System.err.println("No se encontró una cuenta con el número: " + numCuenta);
            }
            return;
        }
        
        // Imprimimos los movimientos
        for (Transaccion t : movimientos) {
            // Usamos el método toString() de Deposito o Retiro
            System.out.println("- " + t.toString()); 
        }
    }
}